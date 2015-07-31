package com.redrumming.thecreaturehub.playlist;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import com.google.api.services.youtube.model.Playlist;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.util.YouTubeUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RedRumming on 7/20/2015.
 */
public class PlaylistAsync extends AsyncTask<PlaylistContainer, Void, Void>{

    private Context context;
    private String apiKey = "ADD-API-KEY";

    public PlaylistAsync(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(PlaylistContainer... container) {

        PlaylistContainer playlistContainer = getPlaylists(container[0]);

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    private PlaylistContainer getPlaylists(PlaylistContainer playlistContainer){

        playlistContainer = retrievePlaylistData(playlistContainer);

        return playlistContainer;

    }

    private PlaylistContainer retrievePlaylistData(PlaylistContainer container){

        List<Playlist> searchResultList = new ArrayList<Playlist>();

        try{

            YouTube youtube = YouTubeUtil.get(context).getYouTube();

            YouTube.Playlists.List playlist = getSearchParams(youtube, container);

            playlist.setPageToken(container.getPageToken());

            PlaylistListResponse searchResponse = playlist.execute();
            List<Playlist> searchItems  = searchResponse.getItems();
            searchResultList.addAll(searchItems);

            container.setPageToken(searchResponse.getNextPageToken());
            container = createPlistItems(searchResultList, container);


        }catch(Exception e){

            Log.e(this.getClass().getName().toString(), "Error retrieve search results: " + e);
        }

        return container;
    }

    private YouTube.Playlists.List getSearchParams(YouTube youtube, PlaylistContainer container) throws IOException{

        String searchCategories = "id, snippet";
        YouTube.Playlists.List playlist = youtube.playlists().list(searchCategories);

        playlist.setKey(apiKey);
        playlist.setChannelId(container.getChannel().getChannelId());
        playlist.setPageToken(container.getPageToken());

        String searchFields = "items(id,snippet/title,snippet/thumbnails/high/url,snippet/publishedAt), nextPageToken";
        playlist.setFields(searchFields);

        long numberofvideos = 20;
        playlist.setMaxResults(numberofvideos);


        return playlist;
    }

    private PlaylistContainer createPlistItems (List<Playlist> searchResults, PlaylistContainer container){

        for(int i = 0; i < searchResults.size(); i++){

            com.redrumming.thecreaturehub.playlist.Playlist playlist = new com.redrumming.thecreaturehub.playlist.Playlist();

            String id = searchResults.get(i).getId();
            String title = searchResults.get(i).getSnippet().getTitle();
            String thumbnailURL = searchResults.get(i).getSnippet().getThumbnails().getHigh().getUrl();
            long publishedDate = searchResults.get(i).getSnippet().getPublishedAt().getValue();

            playlist.setId(id);
            playlist.setTitle(title);
            playlist.setThumbnailURL(thumbnailURL);
            playlist.setPublishedDate(publishedDate);

            String className = this.getClass().getName();
            Log.d(className, "Playlist Id: " +  id);
            Log.d(className, "Playlist Title: " + title);
            Log.d(className, "Playlist Thumbnail URL: " + thumbnailURL);
            Log.d(className, "Playlist Publisht Date: " + new Date(publishedDate).toString());

            container.getPlaylists().add(playlist);

        }

        Log.d(this.getClass().getName(), "Next Page Token: " + container.getPageToken());

        return container;
    }
}

package com.redrumming.thecreaturehub.contentItems.playlist;

import android.content.Context;
import android.util.Log;

import com.google.api.services.youtube.model.*;
import com.google.api.services.youtube.model.Playlist;
import com.redrumming.thecreaturehub.contentItems.ContentAsync;
import com.redrumming.thecreaturehub.contentItems.ContentAsyncListener;
import com.redrumming.thecreaturehub.contentItems.ContentContainer;
import com.redrumming.thecreaturehub.youtube.YouTubeServiceCalls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RedRumming on 7/20/2015.
 */
public class PlaylistAsync extends ContentAsync{

    public PlaylistAsync(Context context, ContentAsyncListener listener){
        super(context, listener);
    }

    @Override
    protected PlaylistContainer doInBackground(ContentContainer... container) {

        super.checkNetworkStatus();

        PlaylistContainer updateContainer = new PlaylistContainer();
        updateContainer.setChannelItem(container[0].getChannelItem());
        updateContainer.setPageToken(container[0].getPageToken());

        updateContainer = getPlaylists(updateContainer);

        return updateContainer;
    }

    @Override
    protected void onPostExecute(ContentContainer container) {
        super.onPostExecute(container);

        if(container.getItems() != null && container.getItems().size() > 0) {

            //logger(container);
        }
    }

    private PlaylistContainer getPlaylists(PlaylistContainer container){

        try{

            PlaylistListResponse searchResponse = new YouTubeServiceCalls(getContext()).getPlaylists(container.getChannelItem().getChannelId(), container.getPageToken());

            container.getItems().addAll(getPlaylistItems(searchResponse.getItems()));
            container.setPageToken(searchResponse.getNextPageToken());

        }catch(Exception e){

            Log.e(this.getClass().getCanonicalName(), "Error retrieve search results: " + e);
        }

        return container;
    }

    private List<PlaylistItem> getPlaylistItems (List<Playlist> searchResults){

        List<PlaylistItem> playlistItems = new ArrayList<PlaylistItem>();

        for(int i = 0; i < searchResults.size(); i++){

            Playlist playlist = searchResults.get(i);
            PlaylistItem playlistItem = new PlaylistItem();

            playlistItem.setId(playlist.getId());
            playlistItem.setTitle(playlist.getSnippet().getTitle());
            playlistItem.setThumbnailURL(playlist.getSnippet().getThumbnails().getMedium().getUrl());
            playlistItem.setPublishedAt(playlist.getSnippet().getPublishedAt().getValue());
            playlistItem.setVideoCount(playlist.getContentDetails().getItemCount());
            playlistItem.setViewable(playlist.getStatus().getPrivacyStatus().equalsIgnoreCase("public"));

            playlistItems.add(playlistItem);
        }

        return playlistItems;
    }

//    private void logger(ContentContainer container){
//
//        String className = this.getClass().getName();
//
//        for(int i = 0; i < container.getItems().size(); i++){
//
//            PlaylistItem playlist = (PlaylistItem) container.getItems().get(i);
//
//            Log.d(className, "Playlist Id: " + playlist.getId());
//            Log.d(className, "Playlist Title: " + playlist.getTitle());
//            Log.d(className, "Playlist Thumbnail URL: " + playlist.getThumbnailURL());
//            Log.d(className, "Playlist Published At: " + new Date(playlist.getPublishedAt()));
//            Log.d(className, "Playlist Public: " + playlist.isViewable());
//            Log.d(className, "Playlist Item Count: " + playlist.getVideoCount());
//        }
//
//        Log.d(className, "Next Page Token: " + container.getPageToken());
//    }
}
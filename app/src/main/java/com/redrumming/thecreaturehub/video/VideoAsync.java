package com.redrumming.thecreaturehub.video;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.Channel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RedRumming on 7/20/2015.
 */
public class VideoAsync extends AsyncTask<VideoContainer, Void, Void>{

    private Context context;
    private String apiKey = "ADD-API-KEY";

    public VideoAsync(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(VideoContainer... container) {

        VideoContainer newContainer = getVideos(container[0]);

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

    private VideoContainer getVideos(VideoContainer container){

        List<SearchResult> results = retrieveYouTubeData(container);

        container = modifyContainer(results, container);

        return container;

    }

    private VideoContainer modifyContainer(List<SearchResult> results,VideoContainer container){

        for(int i = 0; i < results.size(); i++){

            String videoId = results.get(i).getId().getVideoId();
            String videoTitle = results.get(i).getSnippet().getTitle();
            String thumbnailURL = results.get(i).getSnippet().getThumbnails().getHigh().getUrl();
            long pushlishedDate = results.get(i).getSnippet().getPublishedAt().getValue();

            Video video = new Video();
            video.setVideoId(videoId);
            video.setVideoTitle(videoTitle);
            video.setThumbnailURL(thumbnailURL);
            video.setPublishedDate(pushlishedDate);

            String className = this.getClass().getName();
            Log.d(className, "Video Number: " + i);
            Log.d(className, "Video Id: " + videoId);
            Log.d(className, "Video Title: " + videoTitle);
            Log.d(className, "Video Thumbnail URL: " + thumbnailURL);
            Log.d(className, "Video Publish Date: " + new Date(pushlishedDate).toString());

            container.getVideos().add(video);
        }

        return container;
    }

    /**
     * Retrieves values from YouTubes OAuth
     *
     * @param container
     * @return searchResultList
     */
    private List<SearchResult> retrieveYouTubeData(VideoContainer container){

        List<SearchResult> searchResultList = new ArrayList<>();

        try{

            YouTube youtube = initializeYouTube();

            YouTube.Search.List search =  getSearchParams(youtube, container);


            search.setPageToken(container.getPageToken());

            SearchListResponse searchResponse = search.execute();
            searchResultList.addAll(searchResponse.getItems());


        }catch(Exception e){

            Log.e(this.getClass().getName(), "Error Retrieving Search Results: " + e);
        }

        return searchResultList;
    }

    private YouTube initializeYouTube(){

        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        YouTube youtube = new YouTube.Builder(httpTransport, jsonFactory, new HttpRequestInitializer() {

            @Override
            public void initialize(HttpRequest arg0) throws IOException {
                //No initialization actions required.
            }

        }).setApplicationName(context.getResources().getString(R.string.app_name))
                .build();

        return youtube;
    }

    private YouTube.Search.List getSearchParams(YouTube youtube, VideoContainer container) throws IOException{

        String searchCategories = "id, snippet";
        YouTube.Search.List search = youtube.search().list(searchCategories);

        search.setKey(apiKey);
        search.setChannelId(container.getChannel().getChannelId());


        String searchType = "video";
        search.setType(searchType);

        String searchFields = "items(id/kind,id/videoId,snippet/title,snippet/" +
                "thumbnails/high/url,snippet/publishedAt), nextPageToken";
        search.setFields(searchFields);

        long numberofvideos = 50;
        search.setMaxResults(numberofvideos);

        String searchOrder = "date";
        search.setOrder(searchOrder);

        return search;
    }
}

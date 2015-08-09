package com.redrumming.thecreaturehub.video;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.redrumming.thecreaturehub.util.YouTubeUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RedRumming on 7/20/2015.
 */
public class VideoAsync extends AsyncTask<VideoContainer, Void, VideoContainer>{

    private Context context;
    private VideoAsyncListener listener;
    private String apiKey = "ADD-API-KEY";

    public VideoAsync(Context context, VideoAsyncListener listener){

        this.context = context;
        this.listener = listener;
    }

    @Override
    protected VideoContainer doInBackground(VideoContainer... container) {

        VideoContainer updateContainer = getVideos(container[0]);

        return updateContainer;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(VideoContainer container) {
        super.onPostExecute(container);

        listener.onSuccess(container);
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

        container = retrieveYouTubeData(container);

        return container;

    }

    private VideoContainer modifyContainer(List<SearchResult> results, VideoContainer container){

        for(int i = 0; i < results.size(); i++){

            String videoId = results.get(i).getId().getVideoId();
            String videoTitle = results.get(i).getSnippet().getTitle();
            String thumbnailURL = results.get(i).getSnippet().getThumbnails().getMedium().getUrl();
            long pushlishedDate = results.get(i).getSnippet().getPublishedAt().getValue();

            VideoWrapper videoWrapper = new VideoWrapper();
            videoWrapper.setVideoId(videoId);
            videoWrapper.setVideoTitle(videoTitle);
            videoWrapper.setThumbnailURL(thumbnailURL);
            videoWrapper.setPublishedDate(pushlishedDate);

            String className = this.getClass().getName();
            Log.d(className, "VideoWrapper Number: " + i);
            Log.d(className, "VideoWrapper Id: " + videoId);
            Log.d(className, "VideoWrapper Title: " + videoTitle);
            Log.d(className, "VideoWrapper Thumbnail URL: " + thumbnailURL);
            Log.d(className, "VideoWrapper Publish Date: " + new Date(pushlishedDate).toString());

            container.getVideoWrappers().add(videoWrapper);
        }

        Log.d(this.getClass().getName(), "Next PageToken: " + container.getPageToken());

        return container;
    }

    /**
     * Retrieves values from YouTubes OAuth
     *
     * @param container
     * @return searchResultList
     */
    private VideoContainer retrieveYouTubeData(VideoContainer container){

        List<SearchResult> searchResultList = new ArrayList<>();

        try{

            YouTube youtube = YouTubeUtil.get(context).getYouTube();

            YouTube.Search.List search =  getSearchParams(youtube, container);
            
            search.setPageToken(container.getPageToken());

            SearchListResponse searchResponse = search.execute();
            searchResultList.addAll(searchResponse.getItems());

            container.setPageToken(searchResponse.getNextPageToken());
            container = modifyContainer(searchResultList, container);


        }catch(Exception e){

            Log.e(this.getClass().getName(), "Error Retrieving Search Results: " + e);
        }

        return container;
    }



    private YouTube.Search.List getSearchParams(YouTube youtube, VideoContainer container) throws IOException{

        String searchCategories = "id, snippet";
        YouTube.Search.List search = youtube.search().list(searchCategories);

        search.setKey(apiKey);
        search.setChannelId(container.getChannel().getChannelId());


        String searchType = "video";
        search.setType(searchType);

        String searchFields = "items(" +
                "id/kind,id/videoId," +
                "snippet/title," +
                "snippet/thumbnails/medium/url," +
                "snippet/publishedAt," +
                "statistics/viewCount," +
                "statistics/likeCount), nextPageToken";
        search.setFields(searchFields);

        long numberofvideos = 20;
        search.setMaxResults(numberofvideos);

        String searchOrder = "date";
        search.setOrder(searchOrder);

        return search;
    }
}

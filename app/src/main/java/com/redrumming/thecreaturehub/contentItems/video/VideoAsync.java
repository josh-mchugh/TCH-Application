package com.redrumming.thecreaturehub.contentItems.video;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.util.Joiner;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.redrumming.thecreaturehub.util.YouTubeUtil;

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

        VideoContainer updateContainer = new VideoContainer();
        updateContainer.setChannel(container[0].getChannel());
        updateContainer.setPageToken(container[0].getPageToken());

        updateContainer = getVideos(updateContainer);

        return updateContainer;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(VideoContainer container) {
        super.onPostExecute(container);

        String className = this.getClass().getName();

        for(int i = 0; i < container.getVideoWrappers().size(); i++){

            VideoWrapper video = (VideoWrapper) container.getVideoWrappers().get(i);

            Log.d(className, "Video Id: " + video.getVideoId());
            Log.d(className, "Video Title: " + video.getVideoTitle());
            Log.d(className, "Video Thumbnail URL: " + video.getThumbnailURL());
            Log.d(className, "Video PushDate: " + new Date(video.getPublishedDate()));
            Log.d(className, "Video View Count: " + video.getViewCount());
            Log.d(className, "Video Like Count: " + video.getLikeCount());
        }

        Log.d(className, "PageToken: " + container.getPageToken());

        if(isCancelled() != true) {

            listener.onSuccess(container);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.e(this.getClass().getName(), "Video Async onCancelled was called.");
    }

    private VideoContainer getVideos(VideoContainer container){

        container = retrieveYouTubeData(container);

        return container;

    }

    private VideoContainer retrieveYouTubeData(VideoContainer container){

        try{

            YouTube youtube = YouTubeUtil.get(context).getYouTube();

            List<String> videoIds = getVideoIds(youtube, container);

            container = getVideoDetails(youtube, videoIds, container);

        }catch(Exception e){

            Log.e(this.getClass().getName(), "Error Retrieving Search Results: " + e);
        }

        return container;
    }


    private List<String> getVideoIds(YouTube youTube, VideoContainer container) throws Exception{

        YouTube.Search.List search = youTube.search().list("id,snippet");

        search.setKey(apiKey);
        search.setChannelId(container.getChannel().getChannelId());
        search.setType("video");
        search.setFields("items(id/videoId), nextPageToken");
        search.setMaxResults((long) 20);
        search.setOrder("date");
        search.setPageToken(container.getPageToken());

        SearchListResponse searchResponse = search.execute();
        List<SearchResult> searchResults = searchResponse.getItems();
        List<String> videoIds = new ArrayList<String>();

        if(searchResults != null){

            for(int i = 0; i < searchResults.size(); i++){
                videoIds.add(searchResults.get(i).getId().getVideoId());
            }

            container.setPageToken(searchResponse.getNextPageToken());

        }else {

            //Cancel this async if results are null
            onCancelled();
        }

        return videoIds;
    }


    private VideoContainer getVideoDetails(YouTube youTube, List<String> videoIds, VideoContainer container) throws Exception {

        Joiner videoIdJoiner = Joiner.on(',');
        String videoId = videoIdJoiner.join(videoIds);

        YouTube.Videos.List listVideoRequest = youTube.videos().list("id, snippet, statistics").setId(videoId);
        listVideoRequest.setKey(apiKey);
        listVideoRequest.setFields("items(" +
                "id," +
                "snippet/title," +
                "snippet/thumbnails/medium/url," +
                "snippet/publishedAt," +
                "statistics/viewCount," +
                "statistics/likeCount)");
        listVideoRequest.setMaxResults((long) videoIds.size());

        VideoListResponse listResponse = listVideoRequest.execute();

        List<Video> videoList = listResponse.getItems();

        if (videoList != null) {

            for (int i = 0; i < videoList.size(); i++) {

                Video video = videoList.get(i);
                VideoWrapper videoWrapper = new VideoWrapper();

                videoWrapper.setVideoId(video.getId());
                videoWrapper.setVideoTitle(video.getSnippet().getTitle());
                videoWrapper.setThumbnailURL(video.getSnippet().getThumbnails().getMedium().getUrl());
                videoWrapper.setPublishedDate(video.getSnippet().getPublishedAt().getValue());
                videoWrapper.setViewCount(video.getStatistics().getViewCount());
                videoWrapper.setLikeCount(video.getStatistics().getLikeCount());

                container.getVideoWrappers().add(videoWrapper);
            }

        } else {

            //Cancel if list is null.
            onCancelled();
        }

        return container;
    }
}

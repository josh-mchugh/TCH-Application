package com.redrumming.thecreaturehub.contentItems.video;

import android.content.Context;
import android.util.Log;

import com.google.api.client.util.Joiner;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.redrumming.thecreaturehub.YouTubeApiKey;
import com.redrumming.thecreaturehub.contentItems.ContentAsync;
import com.redrumming.thecreaturehub.contentItems.ContentAsyncListener;
import com.redrumming.thecreaturehub.contentItems.ContentContainer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RedRumming on 7/20/2015.
 */
public class VideoAsync extends ContentAsync{

    public VideoAsync(Context context, ContentAsyncListener listener){
        super(context, listener);
    }

    @Override
    protected VideoContainer doInBackground(ContentContainer... container) {

        VideoContainer updateContainer = new VideoContainer();
        updateContainer.setChannel(container[0].getChannel());
        updateContainer.setPageToken(container[0].getPageToken());

        updateContainer = getVideos(updateContainer);

        return updateContainer;
    }

    @Override
    protected void onPostExecute(ContentContainer container) {
        super.onPostExecute(container);

        String className = this.getClass().getName();

        for(int i = 0; i < container.getItems().size(); i++){

            VideoItem video = (VideoItem) container.getItems().get(i);

            Log.d(className, "Video Id: " + video.getVideoId());
            Log.d(className, "Video Title: " + video.getVideoTitle());
            Log.d(className, "Video Thumbnail URL: " + video.getThumbnailURL());
            Log.d(className, "Video PushDate: " + new Date(video.getPublishedDate()));
            Log.d(className, "Video View Count: " + video.getViewCount());
            Log.d(className, "Video Like Count: " + video.getLikeCount());
        }

        Log.d(className, "PageToken: " + container.getPageToken());
    }

    private VideoContainer getVideos(VideoContainer container){

        container = retrieveYouTubeData(container);

        return container;
    }

    private VideoContainer retrieveYouTubeData(VideoContainer container){

        try{

            List<String> videoIds = getVideoIds(super.getYoutube(), container);

            container = getVideoDetails(super.getYoutube(), videoIds, container);

        }catch(Exception e){

            Log.e(this.getClass().getName(), "Error Retrieving Search Results: " + e);
        }

        return container;
    }


    private List<String> getVideoIds(YouTube youTube, VideoContainer container) throws Exception{

        YouTube.Search.List search = youTube.search().list("id,snippet");

        search.setKey(YouTubeApiKey.API_KEY);
        search.setChannelId(container.getChannel().getChannelId());
        search.setType("video");
        search.setFields("items(id/videoId), nextPageToken");
        search.setMaxResults(super.getMaxResults());
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
        listVideoRequest.setKey(YouTubeApiKey.API_KEY);
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
                VideoItem videoWrapper = new VideoItem();

                videoWrapper.setVideoId(video.getId());
                videoWrapper.setVideoTitle(video.getSnippet().getTitle());
                videoWrapper.setThumbnailURL(video.getSnippet().getThumbnails().getMedium().getUrl());
                videoWrapper.setPublishedDate(video.getSnippet().getPublishedAt().getValue());
                videoWrapper.setViewCount(video.getStatistics().getViewCount());
                videoWrapper.setLikeCount(video.getStatistics().getLikeCount());

                container.getItems().add(videoWrapper);
            }

        } else {

            //Cancel if list is null.
            onCancelled();
        }

        return container;
    }
}
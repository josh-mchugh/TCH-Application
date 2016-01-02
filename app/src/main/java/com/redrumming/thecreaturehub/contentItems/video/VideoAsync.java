package com.redrumming.thecreaturehub.contentItems.video;

import android.content.Context;
import android.util.Log;

import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
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
public class VideoAsync extends ContentAsync{

    public VideoAsync(Context context, ContentAsyncListener listener){
        super(context, listener);
    }

    @Override
    protected VideoContainer doInBackground(ContentContainer... container) {

        super.checkNetworkStatus();

        VideoContainer updateContainer = new VideoContainer();
        updateContainer.setChannelItem(container[0].getChannelItem());
        updateContainer.setPageToken(container[0].getPageToken());

        updateContainer = retrieveVideos(updateContainer);

        return updateContainer;
    }

    @Override
    protected void onPostExecute(ContentContainer container) {
        super.onPostExecute(container);

        if(container.getItems() != null && container.getItems().size() > 0) {

            //logger(container);
        }
    }

    private VideoContainer retrieveVideos(VideoContainer container){

        try{

            SearchListResponse searchListResponse = new YouTubeServiceCalls(getContext()).getVideoIds(container.getChannelItem().getChannelId(), container.getPageToken());
            List<String> videoIds = getVideoIds(searchListResponse);

            VideoListResponse videoListResponse = new YouTubeServiceCalls(getContext()).getVideoItems(videoIds);
            List<VideoItem> videoItems = getVideos(videoListResponse);

            container.setPageToken(searchListResponse.getNextPageToken());
            container.getItems().addAll(videoItems);

        }catch(Exception e){

            Log.e(this.getClass().getName(), "Error Retrieving Search Results: " + e);
        }

        return container;
    }

    private List<String> getVideoIds(SearchListResponse response){

        List<SearchResult> searchResults = response.getItems();

        List<String> videoIds = new ArrayList<String>();

        for(int i = 0; i < response.getItems().size(); i++){

            videoIds.add(searchResults.get(i).getId().getVideoId());
        }

        return videoIds;
    }

    private List<VideoItem> getVideos(VideoListResponse videoListResponse){

        List<Video> videos = videoListResponse.getItems();

        List<VideoItem> videoItems = new ArrayList<VideoItem>();

        for(int i = 0; i < videos.size(); i++){

            Video video = videos.get(i);
            VideoItem videoItem = new VideoItem();

            videoItem.setId(video.getId());
            videoItem.setTitle(video.getSnippet().getTitle());
            videoItem.setThumbnailURL(video.getSnippet().getThumbnails().getMedium().getUrl());
            videoItem.setPublishedAt(video.getSnippet().getPublishedAt().getValue());
            videoItem.setViewCount(video.getStatistics().getViewCount());
            videoItem.setLikeCount(video.getStatistics().getLikeCount());
            videoItem.setDislikeCount(video.getStatistics().getDislikeCount());
            videoItem.setDescription(video.getSnippet().getDescription());
            videoItem.setLicense(video.getStatus().getLicense());
            videoItem.setCategoryId(video.getSnippet().getCategoryId());

            videoItems.add(videoItem);
        }

        return videoItems;
    }

//    private void logger(ContentContainer container){
//
//        String className = this.getClass().getName();
//
//        for(int i = 0; i < container.getItems().size(); i++){
//
//            VideoItem video = (VideoItem) container.getItems().get(i);
//
//            Log.d(className, "Video Id: " + video.getId());
//            Log.d(className, "Video Title: " + video.getTitle());
//            Log.d(className, "Video Thumbnail URL: " + video.getThumbnailURL());
//            Log.d(className, "Video Published At: " + new Date(video.getPublishedAt()));
//            Log.d(className, "Video View Count: " + video.getViewCount());
//            Log.d(className, "Video Like Count: " + video.getLikeCount());
//            Log.d(className, "Video Dislike Count: " + video.getDislikeCount());
//            Log.d(className, "Video Description: " + video.getDescription());
//            Log.d(className, "Video Category Id: " + video.getCategoryId());
//            Log.d(className, "Video License: " + video.getLicense());
//        }
//
//        Log.d(className, "PageToken: " + container.getPageToken());
//    }
}
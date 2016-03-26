package com.redrumming.thecreaturehub.models.content.video;

import android.content.Context;
import android.util.Log;

import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.VideoListResponse;
import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.youtube.YouTubeServiceCalls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ME on 3/19/2016.
 */
public class VideoContainerFactory {

    public static VideoContainer createVideoContainer(Context context, VideoContainer container) throws Exception{

        VideoContainer updatedContainer = new VideoContainer();
        updatedContainer.setChannel(container.getChannel());
        updatedContainer.setPageToken(container.getPageToken());

        updatedContainer = retrieveVideos(context, updatedContainer);

        logger(updatedContainer);

        return updatedContainer;
    }

    private static VideoContainer retrieveVideos(Context context, VideoContainer container){

        try{

            SearchListResponse searchListResponse = new YouTubeServiceCalls(context).getVideoIds(container.getChannel().getId(), container.getPageToken());
            List<String> videoIds = getVideoIds(searchListResponse);

            VideoListResponse videoListResponse = new YouTubeServiceCalls(context).getVideoItems(videoIds);
            List<VideoItem> videoItems = VideoItemFactory.createVideoItems(videoListResponse);

            container.setPageToken(searchListResponse.getNextPageToken());
            container.getItems().addAll(videoItems);

        }catch(Exception e){

            Log.e(VideoContainerFactory.class.getName(), "Error Retrieving SearchAPI Results: " + e);
        }

        return container;
    }

    private static List<String> getVideoIds(SearchListResponse response){

        List<SearchResult> searchResults = response.getItems();

        List<String> videoIds = new ArrayList<String>();

        for(int i = 0; i < response.getItems().size(); i++){

            videoIds.add(searchResults.get(i).getId().getVideoId());
        }

        return videoIds;
    }

    private static void logger(ContentContainer container){

        String className = VideoContainerFactory.class.getName();

        for(int i = 0; i < container.getItems().size(); i++){

            VideoItem video = (VideoItem) container.getItems().get(i);

            Log.d(className, "Video Id: " + video.getId());
            Log.d(className, "Video Title: " + video.getTitle());
            Log.d(className, "Video Thumbnail URL: " + video.getThumbnailURL());
            Log.d(className, "Video Published At: " + new Date(video.getPublishedAt()));
            Log.d(className, "Video View Count: " + video.getViewCount());
            Log.d(className, "Video Like Count: " + video.getLikeCount());
            Log.d(className, "Video Dislike Count: " + video.getDislikeCount());
            Log.d(className, "Video Description: " + video.getDescription());
            Log.d(className, "Video Category Id: " + video.getCategoryId());
            Log.d(className, "Video License: " + video.getLicense());
        }

        Log.d(className, "PageToken: " + container.getPageToken());
    }
}

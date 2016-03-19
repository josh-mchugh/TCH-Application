package com.redrumming.thecreaturehub.models.content.playlistvideo;

import android.content.Context;
import android.util.Log;

import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.youtube.YouTubeServiceCalls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ME on 3/19/2016.
 */
public class PlaylistVideoContainerFactory {

    public static PlaylistVideoContainer createPlaylistVideoContainer(Context context, PlaylistVideoContainer container) throws Exception{

        PlaylistVideoContainer updatedContainer = new PlaylistVideoContainer();

        if(container instanceof PlaylistVideoContainer){

            PlaylistVideoContainer tempContainer = (PlaylistVideoContainer) container;

            updatedContainer.setChannelItem(tempContainer.getChannelItem());
            updatedContainer.setPageToken(tempContainer.getPageToken());
            updatedContainer.setPlaylistId(tempContainer.getPlaylistId());

            updatedContainer = getVideos(context, updatedContainer);

            logger(updatedContainer);
        }

        return updatedContainer;
    }

    private static PlaylistVideoContainer getVideos(Context context, PlaylistVideoContainer container) throws Exception{

        container = retrieveYouTubeData(context, container);

        return container;
    }

    private static PlaylistVideoContainer retrieveYouTubeData(Context context, PlaylistVideoContainer container) throws Exception{

        PlaylistItemListResponse response = new YouTubeServiceCalls(context).getPlaylistItems(container.getPlaylistId(), container.getPageToken());
        List<PlaylistItem> items = response.getItems();

        container.getItems().addAll(convertData(items));
        container.setPageToken(response.getNextPageToken());

        container = getVideoDetails(context, container);

        return container;
    }

    private static List<PlaylistVideoItem> convertData(List<PlaylistItem> items){

        List<PlaylistVideoItem> videos = new ArrayList<PlaylistVideoItem>();

        for(int i = 0; i < items.size(); i++){

            PlaylistVideoItem video = new PlaylistVideoItem();

            video.setId(items.get(i).getSnippet().getResourceId().getVideoId());
            video.setPosition(items.get(i).getSnippet().getPosition());
            video.setIsPublic(items.get(i).getStatus().getPrivacyStatus().equals("public"));

            videos.add(video);
        }

        return videos;
    }

    private static List<String> getVideoList(PlaylistVideoContainer container){

        List<String> videoIds = new ArrayList<String>();

        for (int i = 0; i < container.getItems().size(); i++) {

            PlaylistVideoItem videoWrapper = (PlaylistVideoItem) container.getItems().get(i);

            videoIds.add(videoWrapper.getId());
        }

        return videoIds;
    }

    private static PlaylistVideoContainer getVideoDetails(Context context, PlaylistVideoContainer container) throws Exception {

        VideoListResponse listResponse = new YouTubeServiceCalls(context).getVideoItems(getVideoList(container));

        List<Video> videoList = listResponse.getItems();

        if (videoList != null && container.getItems() != null) {

            container = PlaylistVideoItemFactory.createPlaylistVideoItems(listResponse, container);

        }

        return container;
    }

    private static void logger(ContentContainer container){

        String className = PlaylistVideoContainerFactory.class.getCanonicalName();

        for(int i = 0; i < container.getItems().size(); i++){

            PlaylistVideoItem video = (PlaylistVideoItem) container.getItems().get(i);

            Log.d(className, "Playlist VideoId: " + video.getId());
            Log.d(className, "Playlist Video Title: " + video.getTitle());
            Log.d(className, "Playlist Video Thumbnail URL: " + video.getThumbnailURL());
            Log.d(className, "Playlist Video Position: " + video.getPosition());
            Log.d(className, "Playlist Video Date: " + new Date(video.getPublishedAt()));
            Log.d(className, "Playlist Video Public: " + video.isPublic());
            Log.d(className, "Playlist Video LikeCount: " + video.getLikeCount());
            Log.d(className, "Playlist Video ViewCount: " + video.getViewCount());
            Log.d(className, "Playlist Video Description: " + video.getDescription());
            Log.d(className, "Playlist Video Category Id: " + video.getCategoryId());
            Log.d(className, "Playlist Video License: " + video.getLicense());
            Log.d(className, "Playlist Video Dislike Count: " + video.getDislikeCount());
            Log.d(className, "Playlist Id: " + video.getPlaylistId());
        }

        Log.d(className, "PageToken: " + container.getPageToken());
    }
}

package com.redrumming.thecreaturehub.async.content.playlistvideo;

import android.content.Context;
import android.util.Log;

import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.redrumming.thecreaturehub.async.content.ContentAsync;
import com.redrumming.thecreaturehub.async.content.ContentAsyncListener;
import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoContainer;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoItem;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoItemFactory;
import com.redrumming.thecreaturehub.youtube.YouTubeServiceCalls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ME on 10/18/2015.
 */
public class PlaylistVideoAsync extends ContentAsync{

    public PlaylistVideoAsync(Context context, ContentAsyncListener listener){
        super(context, listener);
    }

    @Override
    protected PlaylistVideoContainer doInBackground(ContentContainer... container) {

        PlaylistVideoContainer updatedContainer = new PlaylistVideoContainer();

        if(container[0] instanceof PlaylistVideoContainer){

            PlaylistVideoContainer tempContainer = (PlaylistVideoContainer) container[0];

            updatedContainer.setChannelItem(tempContainer.getChannelItem());
            updatedContainer.setPageToken(tempContainer.getPageToken());
            updatedContainer.setPlaylistId(tempContainer.getPlaylistId());

            updatedContainer = getVideos(updatedContainer);

        }else{

            super.cancel(true);
        }

        return updatedContainer;
    }

    @Override
    protected void onPostExecute(ContentContainer container) {
        super.onPostExecute(container);

        if(container.getItems() != null && container.getItems().size() > 0) {

            logger(container);
        }
    }

    private PlaylistVideoContainer getVideos(PlaylistVideoContainer container){

        container = retrieveYouTubeData(container);

        return container;
    }

    private PlaylistVideoContainer retrieveYouTubeData(PlaylistVideoContainer container){

        try{

            PlaylistItemListResponse response = new YouTubeServiceCalls(getContext()).getPlaylistItems(container.getPlaylistId(), container.getPageToken());
            List<PlaylistItem> items = response.getItems();

            container.getItems().addAll(convertData(items));
            container.setPageToken(response.getNextPageToken());

            container = getVideoDetails(container);

        }catch (Exception e){

            Log.e(this.getClass().getCanonicalName(), "Error retrieving YouTube Playlist Videos results.", e);
        }

        return container;
    }

    private List<PlaylistVideoItem> convertData(List<PlaylistItem> items){

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

    private List<String> getVideoList(PlaylistVideoContainer container){

        List<String> videoIds = new ArrayList<String>();

        for (int i = 0; i < container.getItems().size(); i++) {

            PlaylistVideoItem videoWrapper = (PlaylistVideoItem) container.getItems().get(i);

            videoIds.add(videoWrapper.getId());
        }

        return videoIds;
    }

    private PlaylistVideoContainer getVideoDetails(PlaylistVideoContainer container) throws Exception {

        VideoListResponse listResponse = new YouTubeServiceCalls(getContext()).getVideoItems(getVideoList(container));

        List<Video> videoList = listResponse.getItems();

        if (videoList != null && container.getItems() != null) {

            container = PlaylistVideoItemFactory.createPlaylistVideoItems(listResponse, container);

        } else {

            onCancelled();
        }

        return container;
    }

    private void logger(ContentContainer container){

        String className = this.getClass().getCanonicalName();

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
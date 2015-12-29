package com.redrumming.thecreaturehub.contentItems.PlaylistVideo;

import android.content.Context;
import android.util.Log;

import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
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

    private PlaylistVideoContainer getVideoDetails(PlaylistVideoContainer container) throws Exception {

        List<String> videoIds = new ArrayList<String>();

        for (int i = 0; i < container.getItems().size(); i++) {

            PlaylistVideoItem videoWrapper = (PlaylistVideoItem) container.getItems().get(i);

            videoIds.add(videoWrapper.getId());
        }

        VideoListResponse listResponse = new YouTubeServiceCalls(getContext()).getVideoItems(videoIds);

        List<Video> videoList = listResponse.getItems();

        if (videoList != null && container.getItems() != null) {

            for (int i = 0; i < videoList.size(); i++) {

                for (int j = 0; j < container.getItems().size(); j++) {

                    PlaylistVideoItem playlistVideoItem = (PlaylistVideoItem) container.getItems().get(j);

                    if (playlistVideoItem.getId().equals(videoList.get(i).getId())) {

                        playlistVideoItem.setTitle(videoList.get(i).getSnippet().getTitle());
                        playlistVideoItem.setThumbnailURL(videoList.get(i).getSnippet().getThumbnails().getMedium().getUrl());
                        playlistVideoItem.setPublishedAt(videoList.get(i).getSnippet().getPublishedAt().getValue());
                        playlistVideoItem.setLikeCount(videoList.get(i).getStatistics().getLikeCount());
                        playlistVideoItem.setViewCount(videoList.get(i).getStatistics().getViewCount());
                        playlistVideoItem.setDescription(videoList.get(i).getSnippet().getDescription());
                        playlistVideoItem.setCategoryId(videoList.get(i).getSnippet().getCategoryId());
                        playlistVideoItem.setLicense(videoList.get(i).getStatus().getLicense());
                        playlistVideoItem.setDislikeCount(videoList.get(i).getStatistics().getDislikeCount());
                        playlistVideoItem.setPlaylistId(container.getPlaylistId());

                        container.getItems().remove(j);
                        container.getItems().add(j, playlistVideoItem);
                    }
                }
            }
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
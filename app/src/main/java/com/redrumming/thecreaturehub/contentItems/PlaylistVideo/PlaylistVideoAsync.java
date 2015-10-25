package com.redrumming.thecreaturehub.contentItems.PlaylistVideo;

import android.content.Context;
import android.util.Log;

import com.google.api.client.util.Joiner;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
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

            updatedContainer.setChannel(tempContainer.getChannel());
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

        String className = this.getClass().getCanonicalName();

        for(int i = 0; i < container.getItems().size(); i++){

            PlaylistVideoItem video = (PlaylistVideoItem) container.getItems().get(i);

            Log.d(className, "Playlist VideoId: " + video.getVideoId());
            Log.d(className, "Playlist Video Title: " + video.getVideoTitle());
            Log.d(className, "Playlist Video Thumbnail URL: " + video.getThumbnailURL());
            Log.d(className, "Playlist Video Position: " + video.getPosition());
            Log.d(className, "Playlist Video Date: " + new Date(video.getPublishedDate()));
            Log.d(className, "Playlist Video Public: " + video.isPublic());
            Log.d(className, "Playlist Video LikeCount: " + video.getLikeCount());
            Log.d(className, "Playlist Video ViewCount: " + video.getViewCount());
        }

        Log.d(className, "PageToken: " + container.getPageToken());
    }

    private PlaylistVideoContainer getVideos(PlaylistVideoContainer container){

        container = retrieveYouTubeData(container);

        return container;

    }

    private PlaylistVideoContainer retrieveYouTubeData(PlaylistVideoContainer container){

        try{

            YouTube.PlaylistItems.List search = getSearchParms(super.getYoutube(), container);

            PlaylistItemListResponse response = search.execute();
            List<PlaylistItem> items = response.getItems();

            container.getItems().addAll(convertData(items));
            container.setPageToken(response.getNextPageToken());

            container = getVideoDetails(super.getYoutube(), container);

        }catch (Exception e){

            Log.e(this.getClass().getCanonicalName(), "Error retrieving YouTube Playlist Videos results.", e);
        }

        return container;
    }

    private YouTube.PlaylistItems.List getSearchParms(YouTube youtube, PlaylistVideoContainer container) throws Exception{

        String searchCategories = "id,snippet,status";
        YouTube.PlaylistItems.List search = youtube.playlistItems().list(searchCategories);

        search.setKey(YouTubeApiKey.API_KEY);
        search.setPlaylistId(container.getPlaylistId());
        search.setPageToken(container.getPageToken());

        String searchFields = "items( "
                + "id, "
                + "snippet/publishedAt, "
                + "snippet/title, "
                + "snippet/thumbnails/medium/url, "
                + "snippet/playlistId, "
                + "snippet/position, "
                + "snippet/resourceId/videoId, "
                + "status/privacyStatus "
                + "), "
                + "nextPageToken";
        search.setFields(searchFields);

        search.setMaxResults(super.getMaxResults());

        return search;
    }

    private List<PlaylistVideoItem> convertData(List<PlaylistItem> items){

        List<PlaylistVideoItem> videos = new ArrayList<PlaylistVideoItem>();

        for(int i = 0; i < items.size(); i++){

            PlaylistVideoItem video = new PlaylistVideoItem();

            video.setVideoId(items.get(i).getSnippet().getResourceId().getVideoId());
            video.setPosition(items.get(i).getSnippet().getPosition());
            video.setVideoTitle(items.get(i).getSnippet().getTitle());
            video.setThumbnailURL(items.get(i).getSnippet().getThumbnails().getMedium().getUrl());
            video.setPublishedDate(items.get(i).getSnippet().getPublishedAt().getValue());
            video.setIsPublic(items.get(i).getStatus().getPrivacyStatus().equals("public"));

            videos.add(video);
        }

        return videos;
    }

    private PlaylistVideoContainer getVideoDetails(YouTube youtube, PlaylistVideoContainer container) throws Exception {

        List<String> videoIds = new ArrayList<String>();

        Joiner videoIdJoiner = Joiner.on(',');

        for (int i = 0; i < container.getItems().size(); i++) {

            PlaylistVideoItem videoWrapper = (PlaylistVideoItem) container.getItems().get(i);

            videoIds.add(videoWrapper.getVideoId());
        }

        String videoId = videoIdJoiner.join(videoIds);

        YouTube.Videos.List listVideoRequest = youtube.videos().list("id, statistics").setId(videoId);
        listVideoRequest.setKey(YouTubeApiKey.API_KEY);
        listVideoRequest.setFields("items( " +
                "id, " +
                "statistics/viewCount, " +
                "statistics/likeCount)");
        listVideoRequest.setMaxResults((long) videoIds.size());

        VideoListResponse listResponse = listVideoRequest.execute();

        List<Video> videoList = listResponse.getItems();

        if (videoList != null && container.getItems() != null) {

            for (int i = 0; i < videoList.size(); i++) {

                for (int j = 0; j < container.getItems().size(); j++) {

                    PlaylistVideoItem videoWrapper = (PlaylistVideoItem) container.getItems().get(j);

                    if (videoWrapper.getVideoId().equals(videoList.get(i).getId())) {

                        videoWrapper.setLikeCount(videoList.get(i).getStatistics().getLikeCount());
                        videoWrapper.setViewCount(videoList.get(i).getStatistics().getViewCount());

                        container.getItems().remove(j);
                        container.getItems().add(j, videoWrapper);
                    }
                }
            }
        } else {

            onCancelled();
        }

        return container;
    }
}
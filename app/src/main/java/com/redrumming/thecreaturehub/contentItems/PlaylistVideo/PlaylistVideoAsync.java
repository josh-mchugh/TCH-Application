package com.redrumming.thecreaturehub.contentItems.PlaylistVideo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.util.Joiner;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.redrumming.thecreaturehub.util.YouTubeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ME on 10/18/2015.
 */
public class PlaylistVideoAsync extends AsyncTask<PlaylistVideoContainer, Void, PlaylistVideoContainer>{

    private Context context;
    private PlaylistVideoAsyncListener listener;
    private String apiKey = "ADD-API-KEY";

    public PlaylistVideoAsync(Context context, PlaylistVideoAsyncListener listener){

        this.context = context;
        this.listener = listener;
    }

    @Override
    protected PlaylistVideoContainer doInBackground(PlaylistVideoContainer... container) {

        PlaylistVideoContainer updatedContainer = new PlaylistVideoContainer();
        updatedContainer.setChannel(container[0].getChannel());
        updatedContainer.setPageToken(container[0].getPageToken());
        updatedContainer.setPlaylistId(container[0].getPlaylistId());

        updatedContainer = getVideos(updatedContainer);

        return updatedContainer;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(PlaylistVideoContainer container) {
        super.onPostExecute(container);

        String className = this.getClass().getCanonicalName();

        for(int i = 0; i < container.getVideoWrappers().size(); i++){

            PlaylistVideoWrapper video = (PlaylistVideoWrapper) container.getVideoWrappers().get(i);

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

        if(isCancelled() != true){

            listener.onSuccess(container);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    private PlaylistVideoContainer getVideos(PlaylistVideoContainer container){

        container = retrieveYouTubeData(container);

        return container;

    }

    private PlaylistVideoContainer retrieveYouTubeData(PlaylistVideoContainer container){

        try{

            YouTube youtube = YouTubeUtil.get(context).getYouTube();

            YouTube.PlaylistItems.List search = getSearchParms(youtube, container);

            PlaylistItemListResponse response = search.execute();
            List<PlaylistItem> items = response.getItems();

            container.getVideoWrappers().addAll(convertData(items));
            container.setPageToken(response.getNextPageToken());

            container = getVideoDetails(youtube, container);

        }catch (Exception e){

            Log.e(this.getClass().getCanonicalName(), "Error retrieving YouTube Playlist Videos results.", e);
        }

        return container;
    }

    private YouTube.PlaylistItems.List getSearchParms(YouTube youtube, PlaylistVideoContainer container) throws Exception{

        String searchCategories = "id,snippet,status";
        YouTube.PlaylistItems.List search = youtube.playlistItems().list(searchCategories);

        search.setKey(apiKey);
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

        long numberOfVideos = 20;
        search.setMaxResults(numberOfVideos);

        return search;
    }

    private List<PlaylistVideoWrapper> convertData(List<PlaylistItem> items){

        List<PlaylistVideoWrapper> videos = new ArrayList<PlaylistVideoWrapper>();

        for(int i = 0; i < items.size(); i++){

            PlaylistVideoWrapper video = new PlaylistVideoWrapper();

            video.setVideoId(items.get(i).getSnippet().getResourceId().getVideoId());
            video.setPosition(items.get(i).getSnippet().getPosition());
            video.setVideoTitle(items.get(i).getSnippet().getTitle());
            video.setThumbnailURL(items.get(i).getSnippet().getThumbnails().getMedium().getUrl());
            video.setPublishedDate(items.get(i).getSnippet().getPublishedAt().getValue());
            video.setIsPublic(items.get(i).getStatus().getPrivacyStatus().equals("public") ? true : false);

            videos.add(video);
        }

        return videos;
    }

    private PlaylistVideoContainer getVideoDetails(YouTube youtube, PlaylistVideoContainer container) throws Exception {

        List<String> videoIds = new ArrayList<String>();

        Joiner videoIdJoiner = Joiner.on(',');

        for (int i = 0; i < container.getVideoWrappers().size(); i++) {

            PlaylistVideoWrapper videoWrapper = (PlaylistVideoWrapper) container.getVideoWrappers().get(i);

            videoIds.add(videoWrapper.getVideoId());
        }

        String videoId = videoIdJoiner.join(videoIds);

        YouTube.Videos.List listVideoRequest = youtube.videos().list("id, statistics").setId(videoId);
        listVideoRequest.setKey(apiKey);
        listVideoRequest.setFields("items( " +
                "id, " +
                "statistics/viewCount, " +
                "statistics/likeCount)");
        listVideoRequest.setMaxResults((long) videoIds.size());

        VideoListResponse listResponse = listVideoRequest.execute();

        List<Video> videoList = listResponse.getItems();

        if (videoList != null && container.getVideoWrappers() != null) {

            for (int i = 0; i < videoList.size(); i++) {

                for (int j = 0; j < container.getVideoWrappers().size(); j++) {

                    PlaylistVideoWrapper videoWrapper = (PlaylistVideoWrapper) container.getVideoWrappers().get(j);

                    if (videoWrapper.getVideoId().equals(videoList.get(i).getId())) {

                        videoWrapper.setLikeCount(videoList.get(i).getStatistics().getLikeCount());
                        videoWrapper.setViewCount(videoList.get(i).getStatistics().getViewCount());

                        container.getVideoWrappers().remove(j);
                        container.getVideoWrappers().add(j, videoWrapper);
                    }
                }
            }
        } else {

            onCancelled();
        }

        return container;
    }
}

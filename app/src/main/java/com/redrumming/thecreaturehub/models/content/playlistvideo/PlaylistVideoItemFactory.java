package com.redrumming.thecreaturehub.models.content.playlistvideo;

import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.redrumming.thecreaturehub.models.content.ContentItemFactory;
import com.redrumming.thecreaturehub.util.NumberFormatterUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 1/10/2016.
 */
public class PlaylistVideoItemFactory extends ContentItemFactory {

    public static PlaylistVideoContainer createPlaylistVideoItems(VideoListResponse videoListResponse, PlaylistVideoContainer container){

        List<PlaylistVideoItem> playlistVideoItems = new ArrayList<PlaylistVideoItem>();

        List<Video> videoList = videoListResponse.getItems();

        for (int i = 0; i < videoList.size(); i++) {

            for (int j = 0; j < container.getItems().size(); j++) {

                PlaylistVideoItem playlistVideoItem = (PlaylistVideoItem) container.getItems().get(j);

                if (playlistVideoItem.getId().equals(videoList.get(i).getId())) {

                    playlistVideoItem.setTitle(videoList.get(i).getSnippet().getTitle());
                    playlistVideoItem.setThumbnailURL(videoList.get(i).getSnippet().getThumbnails().getMedium().getUrl());
                    playlistVideoItem.setPublishedAt(videoList.get(i).getSnippet().getPublishedAt().getValue());
                    playlistVideoItem.setLikeCount(NumberFormatterUtil.formatLikeCount(videoList.get(i).getStatistics().getLikeCount()));
                    playlistVideoItem.setViewCount(NumberFormatterUtil.formatViewCount(videoList.get(i).getStatistics().getViewCount()));
                    playlistVideoItem.setDescription(videoList.get(i).getSnippet().getDescription());
                    playlistVideoItem.setCategoryId(videoList.get(i).getSnippet().getCategoryId());
                    playlistVideoItem.setLicense(videoList.get(i).getStatus().getLicense());
                    playlistVideoItem.setDislikeCount(NumberFormatterUtil.formatLikeCount(videoList.get(i).getStatistics().getDislikeCount()));
                    playlistVideoItem.setPlaylistId(container.getPlaylistId());

                    container.getItems().remove(j);
                    container.getItems().add(j, playlistVideoItem);
                }
            }
        }

        return container;
    }
}

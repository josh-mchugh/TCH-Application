package com.redrumming.thecreaturehub.models.content.video;

import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.redrumming.thecreaturehub.models.content.ContentItemFactory;
import com.redrumming.thecreaturehub.util.CategoryFormatter;
import com.redrumming.thecreaturehub.util.LicenseFormatter;
import com.redrumming.thecreaturehub.util.NumberFormatterUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 1/10/2016.
 */
public class VideoItemFactory extends ContentItemFactory {

    public static List<VideoItem> createVideoItems(VideoListResponse videoListResponse){

        List<Video> videos = videoListResponse.getItems();

        List<VideoItem> videoItems = new ArrayList<VideoItem>();

        for(int i = 0; i < videos.size(); i++){

            Video video = videos.get(i);
            VideoItem videoItem = new VideoItem();

            videoItem.setId(video.getId());
            videoItem.setTitle(video.getSnippet().getTitle());
            videoItem.setThumbnailURL(video.getSnippet().getThumbnails().getMedium().getUrl());
            videoItem.setPublishedAt(video.getSnippet().getPublishedAt().getValue());
            videoItem.setViewCount(NumberFormatterUtil.formatViewCount(video.getStatistics().getViewCount()));
            videoItem.setLikeCount(NumberFormatterUtil.formatLikeCount(video.getStatistics().getLikeCount()));
            videoItem.setDislikeCount(NumberFormatterUtil.formatLikeCount(video.getStatistics().getDislikeCount()));
            videoItem.setDescription(video.getSnippet().getDescription());
            videoItem.setLicense(LicenseFormatter.formatLicense(video.getStatus().getLicense()));
            videoItem.setCategoryId(CategoryFormatter.formatCategory(video.getSnippet().getCategoryId()));

            videoItems.add(videoItem);
        }

        return videoItems;
    }
}

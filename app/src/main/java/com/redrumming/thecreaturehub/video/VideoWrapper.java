package com.redrumming.thecreaturehub.video;

import com.redrumming.thecreaturehub.ContentItem;

import java.math.BigInteger;

/**
 * Created by ME on 7/26/2015.
 */
public class VideoWrapper implements ContentItem {

    private String videoId;
    private String videoTitle;
    private String thumbnailURL;
    private long publishedDate;
    private BigInteger viewCount;
    private BigInteger likeCount;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public long getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(long publishedDate) {
        this.publishedDate = publishedDate;
    }

    public BigInteger getViewCount() {
        return viewCount;
    }

    public void setViewCount(BigInteger viewCount) {
        this.viewCount = viewCount;
    }

    public BigInteger getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(BigInteger likeCount) {
        this.likeCount = likeCount;
    }

    @Override
    public int getItemType() {
        return ContentItem.VIDEO_ITEM;
    }
}

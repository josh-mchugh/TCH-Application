package com.redrumming.thecreaturehub.video;

/**
 * Created by ME on 7/26/2015.
 */
public class Video implements VideoItem{

    private String videoId;
    private String videoTitle;
    private String thumbnailURL;
    private long publishedDate;

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

    @Override
    public int getItemType() {
        return VideoItem.VIDEO_ITEM;
    }
}

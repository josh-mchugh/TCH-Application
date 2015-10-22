package com.redrumming.thecreaturehub.contentItems.playlist;

import com.redrumming.thecreaturehub.contentItems.ContentItem;

/**
 * Created by ME on 7/30/2015.
 */
public class PlaylistWrapper implements ContentItem{

    private String id;
    private String title;
    private String thumbnailURL;
    private long publishedDate;
    private long videoCount;
    private boolean viewable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public long getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(long videoCount) {
        this.videoCount = videoCount;
    }

    public boolean isViewable() {
        return viewable;
    }

    public void setViewable(boolean viewable) {
        this.viewable = viewable;
    }

    @Override
    public int getItemType() {
        return ContentItem.PLAYLIST_ITEM;
    }
}

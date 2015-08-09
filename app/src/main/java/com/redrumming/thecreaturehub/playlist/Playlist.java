package com.redrumming.thecreaturehub.playlist;

import com.redrumming.thecreaturehub.ContentItem;

/**
 * Created by ME on 7/30/2015.
 */
public class Playlist implements ContentItem{

    private String id;
    private String title;
    private String thumbnailURL;
    private long publishedDate;

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

    @Override
    public int getItemType() {
        return ContentItem.PLAYLIST_ITEM;
    }
}

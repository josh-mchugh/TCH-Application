package com.redrumming.thecreaturehub.contentItems.PlaylistVideo;

import com.redrumming.thecreaturehub.contentItems.ContentItem;
import com.redrumming.thecreaturehub.contentItems.video.VideoItem;

import java.math.BigInteger;

/**
 * Created by ME on 10/18/2015.
 */
public class PlaylistVideoItem extends VideoItem {

    private Long position;
    private boolean isPublic;

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    @Override
    public String getVideoId() {
        return super.getVideoId();
    }

    @Override
    public void setVideoId(String videoId) {
        super.setVideoId(videoId);
    }

    @Override
    public String getVideoTitle() {
        return super.getVideoTitle();
    }

    @Override
    public void setVideoTitle(String videoTitle) {
        super.setVideoTitle(videoTitle);
    }

    @Override
    public String getThumbnailURL() {
        return super.getThumbnailURL();
    }

    @Override
    public void setThumbnailURL(String thumbnailURL) {
        super.setThumbnailURL(thumbnailURL);
    }

    @Override
    public long getPublishedDate() {
        return super.getPublishedDate();
    }

    @Override
    public void setPublishedDate(long publishedDate) {
        super.setPublishedDate(publishedDate);
    }

    @Override
    public BigInteger getViewCount() {
        return super.getViewCount();
    }

    @Override
    public void setViewCount(BigInteger viewCount) {
        super.setViewCount(viewCount);
    }

    @Override
    public BigInteger getLikeCount() {
        return super.getLikeCount();
    }

    @Override
    public void setLikeCount(BigInteger likeCount) {
        super.setLikeCount(likeCount);
    }

    @Override
    public int getItemType() {
        return ContentItem.PLAYLIST_VIDEO_ITEM;
    }
}

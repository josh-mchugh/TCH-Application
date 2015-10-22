package com.redrumming.thecreaturehub.contentItems.PlaylistVideo;

import com.redrumming.thecreaturehub.channel.Channel;
import com.redrumming.thecreaturehub.contentItems.ContentItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 10/18/2015.
 */
public class PlaylistVideoContainer implements Serializable{

    private Channel channel;
    private List<ContentItem> videoWrappers;
    private String pageToken;
    private String playlistId;

    public PlaylistVideoContainer(){

        videoWrappers = new ArrayList<ContentItem>();
        pageToken = "";
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<ContentItem> getVideoWrappers() {
        return videoWrappers;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
}

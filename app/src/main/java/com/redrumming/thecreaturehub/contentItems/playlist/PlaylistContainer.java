package com.redrumming.thecreaturehub.contentItems.playlist;

import com.redrumming.thecreaturehub.contentItems.ContentItem;
import com.redrumming.thecreaturehub.channel.Channel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 7/30/2015.
 */
public class PlaylistContainer{

    private Channel channel;
    private List<ContentItem> playlistWrappers;
    private String pageToken;

    public PlaylistContainer() {
        playlistWrappers = new ArrayList<ContentItem>();
        pageToken = "";
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<ContentItem> getPlaylistWrappers() {
        return playlistWrappers;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }
}

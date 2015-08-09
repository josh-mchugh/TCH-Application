package com.redrumming.thecreaturehub.playlist;

import com.redrumming.thecreaturehub.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 7/30/2015.
 */
public class PlaylistContainer {

    private Channel channel;
    private List<PlaylistWrapper> playlistWrappers;
    private String pageToken;

    public PlaylistContainer() {
        playlistWrappers = new ArrayList<PlaylistWrapper>();
        pageToken = "";
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<PlaylistWrapper> getPlaylistWrappers() {
        return playlistWrappers;
    }

    public void setPlaylistWrappers(List<PlaylistWrapper> playlistWrappers) {
        this.playlistWrappers = playlistWrappers;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }
}

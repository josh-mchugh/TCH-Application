package com.redrumming.thecreaturehub.playlist;

import com.redrumming.thecreaturehub.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 7/30/2015.
 */
public class PlaylistContainer {

    private Channel channel;
    private List<Playlist> playlists;
    private String pageToken;

    public PlaylistContainer() {
        playlists = new ArrayList<Playlist>();
        pageToken = "";
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }
}

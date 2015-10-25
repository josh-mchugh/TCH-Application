package com.redrumming.thecreaturehub.contentItems.PlaylistVideo;

import com.redrumming.thecreaturehub.channel.Channel;
import com.redrumming.thecreaturehub.contentItems.ContentContainer;
import com.redrumming.thecreaturehub.contentItems.ContentItem;

import java.util.List;

/**
 * Created by ME on 10/18/2015.
 */
public class PlaylistVideoContainer extends ContentContainer {

    private String playlistId;

    public PlaylistVideoContainer(){
        super();
    }

    @Override
    public Channel getChannel() {
        return super.getChannel();
    }

    @Override
    public void setChannel(Channel channel) {
        super.setChannel(channel);
    }

    @Override
    public List<ContentItem> getItems() {
        return super.getItems();
    }

    @Override
    public String getPageToken() {
        return super.getPageToken();
    }

    @Override
    public void setPageToken(String pageToken) {
        super.setPageToken(pageToken);
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
}

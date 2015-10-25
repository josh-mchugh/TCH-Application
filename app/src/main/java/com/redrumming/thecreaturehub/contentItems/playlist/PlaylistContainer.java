package com.redrumming.thecreaturehub.contentItems.playlist;

import com.redrumming.thecreaturehub.contentItems.ContentContainer;
import com.redrumming.thecreaturehub.contentItems.ContentItem;
import com.redrumming.thecreaturehub.channel.Channel;

import java.util.List;

/**
 * Created by ME on 7/30/2015.
 */
public class PlaylistContainer extends ContentContainer {

    public PlaylistContainer() {
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
}

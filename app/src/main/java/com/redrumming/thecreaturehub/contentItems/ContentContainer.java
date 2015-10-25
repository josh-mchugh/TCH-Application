package com.redrumming.thecreaturehub.contentItems;

import com.redrumming.thecreaturehub.channel.Channel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 10/23/2015.
 */
public class ContentContainer implements Serializable {

    private Channel channel;
    private List<ContentItem> items;
    private String pageToken;

    public ContentContainer(){

        items = new ArrayList<ContentItem>();
        pageToken = "";
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<ContentItem> getItems() {
        return items;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }
}

package com.redrumming.thecreaturehub.contentItems.video;

import com.redrumming.thecreaturehub.contentItems.ContentItem;
import com.redrumming.thecreaturehub.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 7/26/2015.
 */
public class VideoContainer {

    private Channel channel;
    private List<ContentItem> videoWrappers;
    private String pageToken;

    public VideoContainer(){

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
}

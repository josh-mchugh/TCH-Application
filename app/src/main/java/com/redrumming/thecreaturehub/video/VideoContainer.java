package com.redrumming.thecreaturehub.video;

import com.redrumming.thecreaturehub.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 7/26/2015.
 */
public class VideoContainer {

    private Channel channel;
    private List<VideoWrapper> videoWrappers;
    private String pageToken;

    public VideoContainer(){

        videoWrappers = new ArrayList<VideoWrapper>();
        pageToken = "";
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<VideoWrapper> getVideoWrappers() {
        return videoWrappers;
    }

    public void setVideoWrappers(List<VideoWrapper> videoWrappers) {
        this.videoWrappers = videoWrappers;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }
}

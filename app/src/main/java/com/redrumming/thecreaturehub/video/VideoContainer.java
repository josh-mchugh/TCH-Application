package com.redrumming.thecreaturehub.video;

import com.redrumming.thecreaturehub.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 7/26/2015.
 */
public class VideoContainer {

    private Channel channel;
    private List<Video> videos;
    private String pageToken;

    public VideoContainer(){

        videos = new ArrayList<Video>();
        pageToken = "";
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }
}

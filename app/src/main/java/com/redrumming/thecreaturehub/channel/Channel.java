package com.redrumming.thecreaturehub.channel;

import android.graphics.drawable.Drawable;

/**
 * Created by ME on 7/19/2015.
 */
public class Channel {

    private String channelName;
    private String channelId;
    private Drawable displayIcon;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Drawable getDisplayIcon() {
        return displayIcon;
    }

    public void setDisplayIcon(Drawable displayIcon) {
        this.displayIcon = displayIcon;
    }
}

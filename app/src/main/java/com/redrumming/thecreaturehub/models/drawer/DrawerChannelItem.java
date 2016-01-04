package com.redrumming.thecreaturehub.models.drawer;

import com.redrumming.thecreaturehub.models.channel.ChannelItem;

/**
 * Created by ME on 7/22/2015.
 */
public class DrawerChannelItem implements DrawerItem{

    private ChannelItem channelItem;

    public ChannelItem getChannelItem() {
        return channelItem;
    }

    public void setChannelItem(ChannelItem channelItem) {
        this.channelItem = channelItem;
    }

    @Override
    public int getType() {
        return CHANNEL;
    }
}

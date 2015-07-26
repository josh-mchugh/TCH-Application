package com.redrumming.thecreaturehub.drawer;

import com.redrumming.thecreaturehub.channel.Channel;

/**
 * Created by ME on 7/22/2015.
 */
public class DrawerChannelItem implements DrawerItem{

    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public int getType() {
        return DrawerItem.CHANNEL;
    }
}

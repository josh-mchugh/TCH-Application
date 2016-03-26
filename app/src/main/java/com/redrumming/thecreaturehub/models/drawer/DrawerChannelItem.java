package com.redrumming.thecreaturehub.models.drawer;


import com.redrumming.thecreaturehub.api.youtube.channel.model.Channel;

/**
 * Created by ME on 7/22/2015.
 */
public class DrawerChannelItem implements DrawerItem{

    private Channel channel;

    public Channel getChannelItem() {

        return channel;
    }

    public void setChannelItem(Channel channel) {

        this.channel = channel;
    }

    @Override
    public int getType() {

        return DrawerItem.CHANNEL;
    }
}

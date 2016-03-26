package com.redrumming.thecreaturehub.models.detail.channel;

import com.redrumming.thecreaturehub.api.youtube.channel.model.Channel;
import com.redrumming.thecreaturehub.models.detail.DetailItem;

/**
 * Created by ME on 12/5/2015.
 */
public class ChannelSectionItem implements DetailItem {

    private Channel channel;

    public Channel getChannel() {

        return channel;
    }

    public void setChannel(Channel channel) {

        this.channel = channel;
    }

    @Override
    public int getType() {

        return DetailItem.CHANNEL_SECTION_ITEM;
    }
}

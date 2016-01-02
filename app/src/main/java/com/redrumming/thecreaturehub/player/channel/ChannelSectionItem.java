package com.redrumming.thecreaturehub.player.channel;

import com.redrumming.thecreaturehub.channel.ChannelItem;
import com.redrumming.thecreaturehub.player.details.DetailItem;

/**
 * Created by ME on 12/5/2015.
 */
public class ChannelSectionItem implements DetailItem {

    private ChannelItem channelItem;

    public ChannelItem getChannelItem() {

        return channelItem;
    }

    public void setChannelItem(ChannelItem channelItem) {

        this.channelItem = channelItem;
    }

    @Override
    public int getType() {

        return DetailItem.CHANNEL_SECTION_ITEM;
    }
}
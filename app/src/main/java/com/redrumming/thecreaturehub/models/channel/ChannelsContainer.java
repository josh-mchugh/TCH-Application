package com.redrumming.thecreaturehub.models.channel;

import java.util.List;

/**
 * Created by ME on 11/13/2015.
 */
public class ChannelsContainer {

    private static ChannelsContainer INSTANCE = new ChannelsContainer();

    private List<ChannelItem> channels;
    private ChannelItem selectedChannel;

    public static ChannelsContainer getInstance() {

        return INSTANCE;
    }

    private ChannelsContainer() {

    }

    public List<ChannelItem> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelItem> channels) {
        this.channels = channels;
    }

    public void setSelectedChannel(ChannelItem selectedChannel) {

        this.selectedChannel = selectedChannel;
    }

    public ChannelItem getSelectedChannel() {

        if(selectedChannel == null){

            selectedChannel = channels.get(0);

            return selectedChannel;
        }

        return selectedChannel;
    }
}

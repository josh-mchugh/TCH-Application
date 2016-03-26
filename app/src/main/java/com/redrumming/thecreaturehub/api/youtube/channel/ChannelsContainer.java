package com.redrumming.thecreaturehub.api.youtube.channel;


import com.redrumming.thecreaturehub.api.youtube.channel.model.Channel;

import java.util.List;

/**
 * Created by ME on 11/13/2015.
 *
 * A Singleton of the channel information for this application. These variables never change since the
 * application revolves around these YouTube content creators.
 */
public class ChannelsContainer {

    private static ChannelsContainer INSTANCE = new ChannelsContainer();

    private List<Channel> channels;
    private Channel selectedChannel;

    public static ChannelsContainer getInstance() {

        return INSTANCE;
    }

    private ChannelsContainer() {

    }

    public List<Channel> getChannels() {

        return channels;
    }

    public void setChannels(List<Channel> channels) {

        this.channels = channels;
    }

    public void setSelectedChannel(Channel selectedChannel) {

        this.selectedChannel = selectedChannel;
    }

    public Channel getSelectedChannel() {

        if(selectedChannel == null){

            selectedChannel = channels.get(0);

            return selectedChannel;
        }

        return selectedChannel;
    }
}

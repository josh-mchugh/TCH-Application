package com.redrumming.thecreaturehub.async.channel;

import com.redrumming.thecreaturehub.models.channel.ChannelItem;

import java.util.List;

/**
 * Created by ME on 11/13/2015.
 */
public interface ChannelsAsyncListener {

    void onSuccess(List<ChannelItem> channels);
    void onFailure();
}

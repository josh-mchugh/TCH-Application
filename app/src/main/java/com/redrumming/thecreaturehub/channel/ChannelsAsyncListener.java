package com.redrumming.thecreaturehub.channel;

import java.util.List;

/**
 * Created by ME on 11/13/2015.
 */
public interface ChannelsAsyncListener {

    void onSuccess(List<ChannelItem> channels);
    void onFailure();
}

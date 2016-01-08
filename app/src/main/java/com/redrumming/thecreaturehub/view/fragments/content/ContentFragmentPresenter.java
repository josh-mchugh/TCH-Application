package com.redrumming.thecreaturehub.view.fragments.content;

import android.os.Bundle;

import com.redrumming.thecreaturehub.async.content.ContentAsync;
import com.redrumming.thecreaturehub.models.channel.ChannelItem;
import com.redrumming.thecreaturehub.models.content.ContentContainer;

/**
 * Created by ME on 1/8/2016.
 */
public interface ContentFragmentPresenter {

    void onCreate(Bundle savedInstanceState);

    void setup(ChannelItem channelItem);

    void onSelect(int position);

    void onLoadMore();
    void onRefresh();

    ContentContainer getContainer();
    ContentAsync getAsync();
}

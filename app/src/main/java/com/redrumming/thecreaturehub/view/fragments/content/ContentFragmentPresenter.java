package com.redrumming.thecreaturehub.view.fragments.content;

import android.os.Bundle;

import com.redrumming.thecreaturehub.models.channel.ChannelItem;
import com.redrumming.thecreaturehub.models.content.ContentContainer;

import rx.Observer;

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
    Observer<ContentContainer> getObserver();

    void onDestroy();
}

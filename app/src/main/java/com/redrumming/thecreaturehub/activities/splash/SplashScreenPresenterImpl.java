package com.redrumming.thecreaturehub.activities.splash;

import com.redrumming.thecreaturehub.channel.ChannelItem;
import com.redrumming.thecreaturehub.channel.ChannelsAsync;
import com.redrumming.thecreaturehub.channel.ChannelsAsyncListener;
import com.redrumming.thecreaturehub.channel.ChannelsContainer;

import java.util.List;

/**
 * Created by ME on 1/2/2016.
 */
public class SplashScreenPresenterImpl implements SplashScreenPresenter, ChannelsAsyncListener {

    private SplashScreenView view;

    public SplashScreenPresenterImpl(SplashScreenView view){
        this.view = view;
    }

    @Override
    public void fetchChannelData(String[] channelIds) {

        ChannelsAsync async = new ChannelsAsync(view.getContext(), this);
        async.execute(channelIds);
    }

    @Override
    public void onSuccess(List<ChannelItem> channels) {

        ChannelsContainer.getInstance().setChannels(channels);

        view.displayNextActivity();
    }

    @Override
    public void onFailure() {

        view.displayFailureAlert();
    }
}

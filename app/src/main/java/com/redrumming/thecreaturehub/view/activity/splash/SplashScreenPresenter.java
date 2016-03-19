package com.redrumming.thecreaturehub.view.activity.splash;

/**
 * Created by ME on 1/2/2016.
 */
public interface SplashScreenPresenter {

    void fetchChannelData(String[] channelIds);
    void onDestroy();
}

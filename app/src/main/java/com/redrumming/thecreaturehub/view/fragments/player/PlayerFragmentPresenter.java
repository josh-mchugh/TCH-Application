package com.redrumming.thecreaturehub.view.fragments.player;

import android.os.Bundle;
import android.os.Parcelable;

import com.google.android.youtube.player.YouTubePlayer;

/**
 * Created by ME on 1/8/2016.
 */
public interface PlayerFragmentPresenter {

    void onCreate(Bundle savedInstanceState, Bundle arguments);
    void onStop();

    void loadVideo();
    void releaseYouTubePlayerResources();
    YouTubePlayer.OnInitializedListener getOnInitializedListener();

    int getPlayTime();

    Parcelable getVideoItem();
    Parcelable getChannelItem();
}

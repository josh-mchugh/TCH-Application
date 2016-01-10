package com.redrumming.thecreaturehub.view.fragments.player.playlist;

import android.os.Parcelable;

import com.redrumming.thecreaturehub.view.fragments.player.PlayerFragmentPresenter;

/**
 * Created by ME on 1/9/2016.
 */
public interface PlaylistPlayerFragmentPresenter extends PlayerFragmentPresenter {

    Parcelable getPlaylistVideoContainer();
    int getPosition();
}

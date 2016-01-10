package com.redrumming.thecreaturehub.view.fragments.player;

import android.os.Parcelable;

/**
 * Created by ME on 1/8/2016.
 */
public interface PlayerFragmentView {

    void updateBottomFragment(Parcelable videoItem, Parcelable channelItem);
    void setLoading(boolean isLoading);
}

package com.redrumming.thecreaturehub.view.fragments.content.video;

import android.os.Parcelable;

import com.redrumming.thecreaturehub.view.fragments.content.ContentFragmentView;

/**
 * Created by ME on 1/8/2016.
 */
public interface VideoListFragmentView extends ContentFragmentView{

    void removePlayerFragment();
    void addPlayerFragment(Parcelable video, Parcelable channelItem);
}

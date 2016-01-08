package com.redrumming.thecreaturehub.view.fragments.content.playlistvideo;

import android.os.Parcelable;

import com.redrumming.thecreaturehub.view.fragments.content.ContentFragmentView;

/**
 * Created by ME on 1/8/2016.
 */
public interface PlaylistVideoFragmentView extends ContentFragmentView {

    void removePlayerFragment();
    void addPlayerFragment(Parcelable container, int position);
}

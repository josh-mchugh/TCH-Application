package com.redrumming.thecreaturehub.view.fragments.content.playlist;

import android.os.Parcelable;

import com.redrumming.thecreaturehub.view.fragments.content.ContentFragmentView;

/**
 * Created by ME on 1/8/2016.
 */
public interface PlaylistListFragmentView extends ContentFragmentView {

    void addPlaylistVideoFragment(Parcelable playlistVideoContainer, String title);
    void removePlaylistVideoFragment();
}

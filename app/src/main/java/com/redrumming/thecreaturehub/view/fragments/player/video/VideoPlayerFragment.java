package com.redrumming.thecreaturehub.view.fragments.player.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.view.fragments.player.PlayerFragment;
import com.redrumming.thecreaturehub.view.fragments.player.PlayerFragmentPresenter;

/**
 * Created by ME on 11/25/2015.
 */
public class VideoPlayerFragment extends PlayerFragment implements VideoPlayerFragmentView {

    private VideoPlayerPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        return super.onCreateView(inflater, viewGroup, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("video", presenter.getVideoItem());
        outState.putParcelable("channel", presenter.getChannelItem());
    }

    @Override
    public PlayerFragmentPresenter getPresenter() {

        if(presenter == null){

            presenter = new VideoPlayerFragmentPresenterImpl(this);

            return presenter;
        }

        return presenter;
    }
}

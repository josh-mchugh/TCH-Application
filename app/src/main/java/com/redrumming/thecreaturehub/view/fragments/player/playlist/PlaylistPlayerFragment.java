package com.redrumming.thecreaturehub.view.fragments.player.playlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.view.fragments.player.PlayerFragment;
import com.redrumming.thecreaturehub.view.fragments.player.PlayerFragmentPresenter;

/**
 * Created by ME on 11/26/2015.
 */
public class PlaylistPlayerFragment extends PlayerFragment implements PlaylistPlayerFragmentView{

    private PlaylistPlayerFragmentPresenter presenter;

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

        outState.putParcelable("container", ((PlaylistPlayerFragmentPresenter) getPresenter()).getPlaylistVideoContainer());
        outState.putInt("position", ((PlaylistPlayerFragmentPresenter) getPresenter()).getPosition());
    }

    @Override
    public PlayerFragmentPresenter getPresenter() {

        if(presenter == null){

            presenter = new PlaylistPlayerFragmentPresenterImpl(this);

            return presenter;
        }

        return presenter;
    }

    @Override
    public Context getContext() {

        return getActivity();
    }
}

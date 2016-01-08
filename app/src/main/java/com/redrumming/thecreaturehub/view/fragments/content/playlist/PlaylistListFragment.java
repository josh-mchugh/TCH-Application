package com.redrumming.thecreaturehub.view.fragments.content.playlist;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.view.fragments.content.ContentFragmentPresenter;
import com.redrumming.thecreaturehub.view.viewholders.content.playlist.PlaylistRecyclerAdapter;
import com.redrumming.thecreaturehub.models.content.playlist.PlaylistContainer;
import com.redrumming.thecreaturehub.view.viewholders.content.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.view.fragments.content.playlistvideo.PlaylistVideoFragment;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.view.fragments.content.ContentFragment;

/**
 *
 */
public class PlaylistListFragment extends ContentFragment implements PlaylistListFragmentView {

    private PlaylistListFragmentPresenter presenter;

    private PlaylistRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        adapter = new PlaylistRecyclerAdapter((PlaylistContainer) getPresenter().getContainer());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        return super.onCreateView(inflater, viewGroup, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("container", getPresenter().getContainer());
    }

    public void removePlaylistVideoFragment(){

        Fragment fragment = getFragmentManager().findFragmentByTag(PlaylistVideoFragment.TAG);

        if(fragment instanceof PlaylistVideoFragment) {

            if (fragment != null) {

                getFragmentManager().beginTransaction().remove(fragment);
            }
        }
    }

    @Override
    public void addPlaylistVideoFragment(Parcelable playlistVideoContainer, String title){

        PlaylistVideoFragment playlistVideoFragment = new PlaylistVideoFragment();
        playlistVideoFragment.setArguments(new Bundle());
        playlistVideoFragment.getArguments().putParcelable("container", playlistVideoContainer);

        getParentFragment()
                .getFragmentManager()
                .beginTransaction()
                .add(R.id.drawer_layout, playlistVideoFragment, PlaylistVideoFragment.TAG)
                .addToBackStack(null)
                .commit();

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
    }

    @Override
    public ContentRecyclerAdapter getAdapter() {

        return adapter;
    }

    @Override
    public ContentFragmentPresenter getPresenter() {

        if(presenter == null){

            presenter = new PlaylistListFragmentPresenterImpl(this);

            return presenter;
        }

        return presenter;
    }
}

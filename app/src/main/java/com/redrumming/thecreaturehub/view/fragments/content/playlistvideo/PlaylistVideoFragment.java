package com.redrumming.thecreaturehub.view.fragments.content.playlistvideo;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.view.fragments.content.ContentFragmentPresenter;
import com.redrumming.thecreaturehub.view.viewholders.content.playlistvideo.PlaylistVideoRecyclerAdapter;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoContainer;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.view.fragments.content.ContentFragment;
import com.redrumming.thecreaturehub.view.viewholders.content.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.view.drawer.NavigationDrawerHelper;
import com.redrumming.thecreaturehub.view.fragments.player.PlayerFragment;
import com.redrumming.thecreaturehub.view.fragments.player.playlist.PlaylistPlayerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistVideoFragment extends ContentFragment implements PlaylistVideoFragmentView{

    public static final String TAG = "PlaylistVideoFragment";

    private PlaylistVideoFragmentPresenter presenter;

    private PlaylistVideoRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){

            ((PlaylistVideoFragmentPresenter) getPresenter()).setContainer((PlaylistVideoContainer) getArguments().getParcelable("container"));
        }

        adapter = new PlaylistVideoRecyclerAdapter((PlaylistVideoContainer) getPresenter().getContainer());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, viewGroup, savedInstanceState);

        NavigationDrawerHelper.get().getDrawerToggle().setDrawerIndicatorEnabled(false);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("container", presenter.getContainer());
    }

    @Override
    public void removePlayerFragment(){

        Fragment fragment = getFragmentManager().findFragmentByTag(PlayerFragment.TAG);

        if(fragment != null){

            if(fragment instanceof PlayerFragment){

                getFragmentManager().beginTransaction().remove(fragment).commit();
                getFragmentManager().popBackStack();
            }
        }
    }

    @Override
    public void addPlayerFragment(Parcelable container, int position){

        PlaylistPlayerFragment playlistPlayerFragment = new PlaylistPlayerFragment();
        playlistPlayerFragment.setArguments(new Bundle());
        playlistPlayerFragment.getArguments().putParcelable("container", container);
        playlistPlayerFragment.getArguments().putInt("position", position);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.activity_layout, playlistPlayerFragment, PlayerFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public ContentRecyclerAdapter getAdapter() {

        return adapter;
    }

    @Override
    public ContentFragmentPresenter getPresenter() {

        if(presenter == null){

            presenter = new PlaylistVideoFragmentPresenterImpl(this);

            return presenter;
        }

        return presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getPresenter().getContainer().getChannelItem().getChannelName());
        NavigationDrawerHelper.get().getDrawerToggle().setDrawerIndicatorEnabled(true);
    }
}
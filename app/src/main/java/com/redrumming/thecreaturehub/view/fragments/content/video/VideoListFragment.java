package com.redrumming.thecreaturehub.view.fragments.content.video;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.view.fragments.content.ContentFragmentPresenter;
import com.redrumming.thecreaturehub.view.viewholders.content.video.VideoRecyclerAdapter;
import com.redrumming.thecreaturehub.models.content.video.VideoContainer;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.view.fragments.content.ContentFragment;
import com.redrumming.thecreaturehub.view.viewholders.content.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.view.fragments.player.PlayerFragment;
import com.redrumming.thecreaturehub.view.fragments.player.video.VideoPlayerFragment;

/**
 *
 */
public class VideoListFragment extends ContentFragment implements VideoListFragmentView {

    private VideoListFragmentPresenter presenter;

    private VideoRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        adapter = new VideoRecyclerAdapter((VideoContainer) getPresenter().getContainer());
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

    @Override
    public void removePlayerFragment(){

        Fragment fragment = getParentFragment().getFragmentManager().findFragmentByTag(PlayerFragment.TAG);

        if(fragment != null){

            if(fragment instanceof PlayerFragment){

                getParentFragment().getFragmentManager().beginTransaction().remove(fragment).commit();
                getParentFragment().getFragmentManager().popBackStack();
            }
        }
    }

    @Override
    public void addPlayerFragment(Parcelable video, Parcelable channelItem){

        VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();
        videoPlayerFragment.setArguments(new Bundle());
        videoPlayerFragment.getArguments().putParcelable("video", video);
        videoPlayerFragment.getArguments().putParcelable("channel", channelItem);

        getParentFragment()
                .getFragmentManager()
                .beginTransaction()
                .add(R.id.activity_layout, videoPlayerFragment, PlayerFragment.TAG)
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

            presenter = new VideoListFragmentPresenterImpl(this);
        }

        return presenter;
    }
}
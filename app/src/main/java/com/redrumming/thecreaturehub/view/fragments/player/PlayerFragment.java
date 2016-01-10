package com.redrumming.thecreaturehub.view.fragments.player;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.view.draggable.DraggableListener;
import com.redrumming.thecreaturehub.view.draggable.DraggablePanel;
import com.redrumming.thecreaturehub.view.fragments.detail.DetailsFragment;
import com.redrumming.thecreaturehub.youtube.YouTubeApiKey;

/**
 * Created by ME on 11/25/2015.
 */
public abstract class PlayerFragment extends Fragment implements PlayerFragmentView{

    public static final String TAG = "PlayerFragment";

    private PlayerFragmentPresenter presenter;

    private DraggablePanel draggablePanel;
    private YouTubePlayerSupportFragment youtubePlayerFragment;
    private DetailsFragment detailsFragment;

    public abstract PlayerFragmentPresenter getPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = getPresenter();
        presenter.onCreate(savedInstanceState, getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_player, viewGroup, false);

        draggablePanel = (DraggablePanel) view.findViewById(R.id.draggable_panel);

        hookDraggablePanelListeners();

        initializeDetailsFragment();
        initializeYouTubeFragment();
        initializeDraggablePanel();

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        presenter.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("playTime", presenter.getPlayTime());
    }

    @Override
    public void updateBottomFragment(Parcelable videoItem, Parcelable channelItem){

        detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(new Bundle());
        detailsFragment.getArguments().putParcelable("video", videoItem);
        detailsFragment.getArguments().putParcelable("channel", channelItem);

        getChildFragmentManager().beginTransaction().replace(R.id.second_view, detailsFragment).commit();
    }

    @Override
    public void setLoading(boolean isLoading) {

        detailsFragment.setLoading(isLoading);
    }

    private void initializeYouTubeFragment(){

        youtubePlayerFragment = (YouTubePlayerSupportFragment) getChildFragmentManager().findFragmentById(R.id.drag_view);

        if(youtubePlayerFragment == null) {

            youtubePlayerFragment = new YouTubePlayerSupportFragment();
        }

        youtubePlayerFragment.initialize(YouTubeApiKey.API_KEY, presenter.getOnInitializedListener());
    }

    private void initializeDetailsFragment(){

        detailsFragment = (DetailsFragment) getChildFragmentManager().findFragmentById(R.id.second_view);

        if(detailsFragment == null){

            detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(new Bundle());
            detailsFragment.getArguments().putParcelable("video", presenter.getVideoItem());
            detailsFragment.getArguments().putParcelable("channel", presenter.getChannelItem());

        }
    }

    private void initializeDraggablePanel(){

        draggablePanel.setFragmentManager(getChildFragmentManager());

        draggablePanel.setTopFragment(youtubePlayerFragment);

        draggablePanel.setBottomFragment(detailsFragment);

        draggablePanel.initializeView();

    }

    private void hookDraggablePanelListeners(){

        draggablePanel.setDraggableListener(new DraggableListener() {

            @Override
            public void onMaximized() {

                presenter.loadVideo();
            }

            @Override
            public void onMinimized() {

            }

            @Override
            public void onClosedToLeft() {

                presenter.releaseYouTubePlayerResources();
                getFragmentManager().popBackStack();
            }

            @Override
            public void onClosedToRight() {

                presenter.releaseYouTubePlayerResources();
                getFragmentManager().popBackStack();
            }
        });
    }
}
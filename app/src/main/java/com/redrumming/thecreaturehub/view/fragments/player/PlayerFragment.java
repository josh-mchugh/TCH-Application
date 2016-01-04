package com.redrumming.thecreaturehub.view.fragments.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.models.channel.ChannelItem;
import com.redrumming.thecreaturehub.models.content.video.VideoItem;
import com.redrumming.thecreaturehub.view.draggable.DraggableListener;
import com.redrumming.thecreaturehub.view.draggable.DraggablePanel;
import com.redrumming.thecreaturehub.view.fragments.detail.DetailsFragment;
import com.redrumming.thecreaturehub.youtube.YouTubeApiKey;

/**
 * Created by ME on 11/25/2015.
 */
public abstract class PlayerFragment extends Fragment implements YouTubePlayer.OnInitializedListener, YouTubePlayer.PlaylistEventListener{

    public static final String TAG = "PlayerFragment";

    private DraggablePanel draggablePanel;
    private YouTubePlayerSupportFragment youtubePlayerFragment;
    private YouTubePlayer youTubePlayer;

    private DetailsFragment detailsFragment;

    private int playTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){

            playTime = savedInstanceState.getInt("playTime");
        }
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

        if(youTubePlayer != null){

            playTime = youTubePlayer.getCurrentTimeMillis();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("playTime", playTime);
    }

    public abstract void loadVideo(int playTime);
    public abstract VideoItem getVideoItem();
    public abstract ChannelItem getChannelItem();

    private void initializeYouTubeFragment(){

        youtubePlayerFragment = (YouTubePlayerSupportFragment) getChildFragmentManager().findFragmentById(R.id.drag_view);

        if(youtubePlayerFragment == null) {

            youtubePlayerFragment = new YouTubePlayerSupportFragment();
        }

        youtubePlayerFragment.initialize(YouTubeApiKey.API_KEY, this);
    }

    private void initializeDetailsFragment(){

        detailsFragment = (DetailsFragment) getChildFragmentManager().findFragmentById(R.id.second_view);

        if(detailsFragment == null){

            detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(new Bundle());
            detailsFragment.getArguments().putParcelable("video", getVideoItem());
            detailsFragment.getArguments().putParcelable("channel", getChannelItem());

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

                PlayerFragment.this.loadVideo(playTime);
            }

            @Override
            public void onMinimized() {

            }

            @Override
            public void onClosedToLeft() {

                releaseYouTubePlayerResources();
                getFragmentManager().popBackStack();
            }

            @Override
            public void onClosedToRight() {

                releaseYouTubePlayerResources();
                getFragmentManager().popBackStack();
            }
        });
    }

    private void releaseYouTubePlayerResources(){

        if(youTubePlayer != null) {

            youTubePlayer.release();
            youTubePlayer = null;
        }
    }

    public YouTubePlayer getYouTubePlayer(){

        return youTubePlayer;
    }

    public DetailsFragment getVideoDetailsFragment(){

        return detailsFragment;
    }

    //Update Bottom Fragment
    public void updateBottomFragment(VideoItem videoItem, ChannelItem channelItem){

        detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(new Bundle());
        detailsFragment.getArguments().putParcelable("video", videoItem);
        detailsFragment.getArguments().putParcelable("channel", channelItem);

        getChildFragmentManager().beginTransaction().replace(R.id.second_view, detailsFragment).commit();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

        this.youTubePlayer = youTubePlayer;
        this.youTubePlayer.setShowFullscreenButton(true);
        this.youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

        this.youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION
                | YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE
                | YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI);

        this.youTubePlayer.setPlaylistEventListener(this);

        if(!wasRestored){

            if(youTubePlayer != null) {

                loadVideo(playTime);
            }
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onPrevious() {

    }

    @Override
    public void onNext() {

    }

    @Override
    public void onPlaylistEnded() {

    }
}
package com.redrumming.thecreaturehub.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggablePanel;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.YouTubeApiKey;

/**
 * Created by ME on 10/27/2015.
 */
public abstract class Player extends Fragment{

    private YouTubePlayerSupportFragment youTubePlayerSupportFragment;
    private YouTubePlayer youTubePlayer;

    private DraggablePanel draggablePanel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.video_player, viewGroup, false);

        draggablePanel = (DraggablePanel) view.findViewById(R.id.draggable_panel);

        initializeYouTubeFragment();
        initializeDraggablePanel();
        initializeDraggablePanelListener();

        return view;
    }

    public abstract void loadVideo(YouTubePlayer player);

    private void initializeYouTubeFragment(){

        youTubePlayerSupportFragment = new YouTubePlayerSupportFragment();
        youTubePlayerSupportFragment.initialize(YouTubeApiKey.API_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean restored) {

                if(restored == false){

                    youTubePlayer = player;
                    youTubePlayer.setShowFullscreenButton(true);

                    loadVideo(youTubePlayer);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    private void initializeDraggablePanel(){

        VideoDetails videoDetails = new VideoDetails();

        draggablePanel.setFragmentManager(getActivity().getSupportFragmentManager());
        draggablePanel.setTopFragment(youTubePlayerSupportFragment);
        draggablePanel.setBottomFragment(videoDetails);
        draggablePanel.initializeView();
    }

    private void initializeDraggablePanelListener(){

        draggablePanel.setDraggableListener(new DraggableListener() {
            @Override
            public void onMaximized() {

                playVideo();
            }

            @Override
            public void onMinimized() {

            }

            @Override
            public void onClosedToLeft() {

            }

            @Override
            public void onClosedToRight() {

            }
        });
    }

    private void playVideo(){

        if(youTubePlayer.isPlaying() == false){

            youTubePlayer.play();
        }
    }
}

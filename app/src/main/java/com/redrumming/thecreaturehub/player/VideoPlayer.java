package com.redrumming.thecreaturehub.player;


import android.os.Bundle;
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
import com.redrumming.thecreaturehub.channel.Channel;
import com.redrumming.thecreaturehub.contentItems.video.VideoWrapper;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoPlayer extends Fragment {

    private VideoWrapper video = null;
    private Channel channel = null;

    private YouTubePlayerSupportFragment youTubePlayerFragment;
    private YouTubePlayer youTubePlayer;

    private DraggablePanel draggablePanel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.video_player, container, false);

        Bundle bundle = getArguments();
        if(bundle != null){

            video = (VideoWrapper) getArguments().getSerializable("video");
            channel = (Channel) getArguments().getSerializable("channel");
        }

        draggablePanel = (DraggablePanel) view.findViewById(R.id.draggable_panel);

        initializeYouTubeFragment();
        initializeDraggablePanel();
        initializeDraggablePanelListeners();

        return view;
    }

    private void initializeYouTubeFragment(){

        youTubePlayerFragment = new YouTubePlayerSupportFragment();
        youTubePlayerFragment.initialize("ADD-API-KEY", new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {

                if(!wasRestored){
                    youTubePlayer = player;
                    youTubePlayer.loadVideo(video.getVideoId());
                    youTubePlayer.setShowFullscreenButton(true);
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
        draggablePanel.setTopFragment(youTubePlayerFragment);
        draggablePanel.setBottomFragment(videoDetails);
        draggablePanel.initializeView();;
    }

    private void initializeDraggablePanelListeners(){

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

        if(!youTubePlayer.isPlaying()){

            youTubePlayer.play();
        }
    }
}

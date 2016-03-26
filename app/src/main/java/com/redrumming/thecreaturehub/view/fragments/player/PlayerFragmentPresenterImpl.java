package com.redrumming.thecreaturehub.view.fragments.player;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.redrumming.thecreaturehub.api.youtube.channel.model.Channel;
import com.redrumming.thecreaturehub.models.content.video.VideoItem;

/**
 * Created by ME on 1/8/2016.
 */
public abstract class PlayerFragmentPresenterImpl implements PlayerFragmentPresenter{

    private PlayerFragmentView view;

    private YouTubePlayer youTubePlayer;
    private int playTime = 0;

    public abstract void loadVideo();
    public abstract VideoItem getVideoItem();
    public abstract Channel getChannelItem();

    public PlayerFragmentPresenterImpl(PlayerFragmentView view){

        this.view = view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Bundle arguments) {

        if(savedInstanceState != null){

            playTime = savedInstanceState.getInt("playTime");
        }
    }

    @Override
    public void onStop() {
        if(youTubePlayer != null){

            playTime = youTubePlayer.getCurrentTimeMillis();
        }
    }

    @Override
    public int getPlayTime() {

        return playTime;
    }

    @Override
    public YouTubePlayer.OnInitializedListener getOnInitializedListener() {

        return onInitializedListener;
    }

    @Override
    public void releaseYouTubePlayerResources(){

        if(youTubePlayer != null) {

            youTubePlayer.release();
            youTubePlayer = null;
        }
    }

    public void onNext(){


    }

    public void onPrevious(){


    }

    public YouTubePlayer getYouTubePlayer(){

        return youTubePlayer;
    }

    public PlayerFragmentView getView(){

        return view;
    }

    private YouTubePlayer.OnInitializedListener onInitializedListener = new YouTubePlayer.OnInitializedListener() {

        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

            PlayerFragmentPresenterImpl.this.youTubePlayer = youTubePlayer;
            PlayerFragmentPresenterImpl.this.youTubePlayer.setShowFullscreenButton(true);
            PlayerFragmentPresenterImpl.this.youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

            PlayerFragmentPresenterImpl.this.youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION
                    | YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE
                    | YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI);

            PlayerFragmentPresenterImpl.this.youTubePlayer.setPlaylistEventListener(onPlaylistEventListener);

            if(!wasRestored){

                if(youTubePlayer != null) {

                    loadVideo();
                }
            }
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        }
    };

    private YouTubePlayer.PlaylistEventListener onPlaylistEventListener = new YouTubePlayer.PlaylistEventListener() {

        @Override
        public void onPrevious() {

            onPrevious();
        }

        @Override
        public void onNext() {

            onNext();
        }

        @Override
        public void onPlaylistEnded() {

        }
    };
}

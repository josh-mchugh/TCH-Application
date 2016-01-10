package com.redrumming.thecreaturehub.view.fragments.player.video;

import android.os.Bundle;

import com.redrumming.thecreaturehub.models.channel.ChannelItem;
import com.redrumming.thecreaturehub.models.content.video.VideoItem;
import com.redrumming.thecreaturehub.view.fragments.player.PlayerFragmentPresenterImpl;

/**
 * Created by ME on 1/9/2016.
 */
public class VideoPlayerFragmentPresenterImpl extends PlayerFragmentPresenterImpl implements VideoPlayerPresenter {

    private VideoItem videoItem;
    private ChannelItem channelItem;

    public VideoPlayerFragmentPresenterImpl(VideoPlayerFragmentView view){

        super(view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Bundle arguments) {
        super.onCreate(savedInstanceState, arguments);

        if(savedInstanceState != null){

            videoItem = savedInstanceState.getParcelable("video");
            channelItem = savedInstanceState.getParcelable("channel");
        }

        if(arguments != null){

            videoItem = arguments.getParcelable("video");
            channelItem = arguments.getParcelable("channel");
        }
    }

    @Override
    public void loadVideo() {

        if (super.getYouTubePlayer() != null) {

            if (super.getYouTubePlayer().isPlaying() == false) {

                super.getYouTubePlayer().loadVideo(videoItem.getId(), super.getPlayTime());

                super.getYouTubePlayer().play();
            }
        }
    }

    @Override
    public VideoItem getVideoItem() {

        return videoItem;
    }

    @Override
    public ChannelItem getChannelItem() {

        return channelItem;
    }
}

package com.redrumming.thecreaturehub.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.channel.ChannelItem;
import com.redrumming.thecreaturehub.contentItems.video.VideoItem;

/**
 * Created by ME on 11/25/2015.
 */
public class VideoPlayerFragment extends PlayerFragment{

    private VideoItem videoItem;
    private ChannelItem channelItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){

            videoItem = savedInstanceState.getParcelable("video");
            channelItem = savedInstanceState.getParcelable("channel");
        }

        if(getArguments() != null){

            videoItem = getArguments().getParcelable("video");
            channelItem = getArguments().getParcelable("channel");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        return super.onCreateView(inflater, viewGroup, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("video", videoItem);
        outState.putParcelable("channel", channelItem);
    }

    @Override
    public void loadVideo(int playTime) {

        if (getYouTubePlayer() != null) {

            if (getYouTubePlayer().isPlaying() == false) {

                getYouTubePlayer().loadVideo(videoItem.getId(), playTime);

                getYouTubePlayer().play();
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

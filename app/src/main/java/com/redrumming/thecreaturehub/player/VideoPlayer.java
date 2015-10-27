package com.redrumming.thecreaturehub.player;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.youtube.player.YouTubePlayer;
import com.redrumming.thecreaturehub.channel.Channel;
import com.redrumming.thecreaturehub.contentItems.video.VideoItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoPlayer extends Player {

    private VideoItem video = null;
    private Channel channel = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if(bundle != null){

            video = (VideoItem) getArguments().getSerializable("video");
            channel = (Channel) getArguments().getSerializable("channel");
        }

        return super.onCreateView(inflater, viewGroup, savedInstanceState);
    }

    @Override
    public void loadVideo(YouTubePlayer player) {

        player.loadVideo(video.getVideoId());
    }
}

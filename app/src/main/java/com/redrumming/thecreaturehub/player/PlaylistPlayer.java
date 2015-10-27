package com.redrumming.thecreaturehub.player;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.youtube.player.YouTubePlayer;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.Channel;
import com.redrumming.thecreaturehub.contentItems.PlaylistVideo.PlaylistVideoItem;

/**
 * Created by ME on 10/26/2015.
 */
public class PlaylistPlayer extends Player{

    private PlaylistVideoItem item = null;
    private Channel channel = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        Bundle bundle = getArguments();
        if(bundle != null){

            item = (PlaylistVideoItem) getArguments().getSerializable("item");
            channel = (Channel) getArguments().getSerializable("channel");
        }

        return super.onCreateView(inflater, viewGroup, savedInstanceState);
    }

    @Override
    public void loadVideo(YouTubePlayer player) {

        player.loadPlaylist(item.getPlaylistId(), item.getPosition().intValue(), 0);
    }
}

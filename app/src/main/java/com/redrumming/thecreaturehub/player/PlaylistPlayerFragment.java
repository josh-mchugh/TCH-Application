package com.redrumming.thecreaturehub.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.channel.ChannelItem;
import com.redrumming.thecreaturehub.contentItems.ContentAsyncListener;
import com.redrumming.thecreaturehub.contentItems.ContentContainer;
import com.redrumming.thecreaturehub.contentItems.PlaylistVideo.PlaylistVideoAsync;
import com.redrumming.thecreaturehub.contentItems.PlaylistVideo.PlaylistVideoContainer;
import com.redrumming.thecreaturehub.contentItems.PlaylistVideo.PlaylistVideoItem;
import com.redrumming.thecreaturehub.contentItems.video.VideoItem;

/**
 * Created by ME on 11/26/2015.
 */
public class PlaylistPlayerFragment extends PlayerFragment implements ContentAsyncListener{

    private PlaylistVideoContainer playlistVideoContainer;
    private int position;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){

            playlistVideoContainer = savedInstanceState.getParcelable("container");
            position = savedInstanceState.getInt("position");
        }

        if(getArguments() != null){

            playlistVideoContainer = getArguments().getParcelable("container");
            position = getArguments().getInt("position");
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

        outState.putParcelable("container", playlistVideoContainer);
        outState.putInt("position", position);
    }

    @Override
    public void loadVideo(int playTime) {

        if(getYouTubePlayer() != null) {

            if(getYouTubePlayer().isPlaying() == false) {

                getYouTubePlayer().loadPlaylist(
                        playlistVideoContainer.getPlaylistId(),
                        ((PlaylistVideoItem) playlistVideoContainer.getItems().get(position)).getPosition().intValue(),
                        playTime);

                getYouTubePlayer().play();

            }
        }
    }

    @Override
    public VideoItem getVideoItem() {

        return (PlaylistVideoItem) playlistVideoContainer.getItems().get(position);
    }

    @Override
    public ChannelItem getChannelItem() {

        return playlistVideoContainer.getChannelItem();
    }

    @Override
    public void onPrevious() {
        super.onPrevious();

        position--;

        super.updateBottomFragment((VideoItem)playlistVideoContainer.getItems().get(position), playlistVideoContainer.getChannelItem());
    }

    @Override
    public void onNext() {
        super.onNext();

        position++;

        int lastVideo = playlistVideoContainer.getItems().size() - 1;

        if(position == lastVideo){

            PlaylistVideoAsync async = new PlaylistVideoAsync(getActivity(), this);
            async.execute(playlistVideoContainer);

            super.updateBottomFragment((VideoItem)playlistVideoContainer.getItems().get(position), playlistVideoContainer.getChannelItem());

        }else if(position > lastVideo){

            super.getVideoDetailsFragment().setLoading(true);

        }else {

           super.updateBottomFragment((VideoItem)playlistVideoContainer.getItems().get(position), playlistVideoContainer.getChannelItem());
        }
    }

    @Override
    public void onSuccess(ContentContainer container) {

        playlistVideoContainer.getItems().addAll(container.getItems());
        playlistVideoContainer.setPageToken(container.getPageToken());

        super.updateBottomFragment((VideoItem)playlistVideoContainer.getItems().get(position), playlistVideoContainer.getChannelItem());
    }

    @Override
    public void onFailure() {

    }
}

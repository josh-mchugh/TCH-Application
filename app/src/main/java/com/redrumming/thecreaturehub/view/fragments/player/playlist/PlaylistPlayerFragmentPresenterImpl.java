package com.redrumming.thecreaturehub.view.fragments.player.playlist;

import android.os.Bundle;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.async.content.ContentAsyncListener;
import com.redrumming.thecreaturehub.async.content.playlistvideo.PlaylistVideoAsync;
import com.redrumming.thecreaturehub.models.channel.ChannelItem;
import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoContainer;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoItem;
import com.redrumming.thecreaturehub.models.content.video.VideoItem;
import com.redrumming.thecreaturehub.view.fragments.player.PlayerFragmentPresenterImpl;

/**
 * Created by ME on 1/9/2016.
 */
public class PlaylistPlayerFragmentPresenterImpl extends PlayerFragmentPresenterImpl implements PlaylistPlayerFragmentPresenter, ContentAsyncListener {

    private PlaylistVideoContainer playlistVideoContainer;
    private int position;

    public PlaylistPlayerFragmentPresenterImpl(PlaylistPlayerFragmentView view){

        super(view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Bundle arguments) {
        super.onCreate(savedInstanceState, arguments);

        if(savedInstanceState != null){

            playlistVideoContainer = savedInstanceState.getParcelable("container");
            position = savedInstanceState.getInt("position");
        }

        if(arguments != null){

            playlistVideoContainer = arguments.getParcelable("container");
            position = arguments.getInt("position");
        }
    }

    @Override
    public void loadVideo() {

        if(super.getYouTubePlayer() != null) {

            if(super.getYouTubePlayer().isPlaying() == false) {

                super.getYouTubePlayer().loadPlaylist(
                        playlistVideoContainer.getPlaylistId(),
                        ((PlaylistVideoItem) playlistVideoContainer.getItems().get(position)).getPosition().intValue(),
                        super.getPlayTime());

                super.getYouTubePlayer().play();

            }
        }
    }

    @Override
    public void onNext() {
        super.onNext();

        position++;

        int lastVideo = playlistVideoContainer.getItems().size() - 1;

        if(position == lastVideo){

            PlaylistVideoAsync async = new PlaylistVideoAsync(((PlaylistPlayerFragmentView) getView()).getContext(), this);
            async.execute(playlistVideoContainer);

            super.getView().updateBottomFragment(playlistVideoContainer.getItems().get(position), playlistVideoContainer.getChannelItem());

        }else if(position > lastVideo){

            super.getView().setLoading(true);

        }else {

            super.getView().updateBottomFragment(playlistVideoContainer.getItems().get(position), playlistVideoContainer.getChannelItem());
        }
    }

    @Override
    public void onPrevious() {
        super.onPrevious();

        position--;

        super.getView().updateBottomFragment(playlistVideoContainer.getItems().get(position), playlistVideoContainer.getChannelItem());
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
    public Parcelable getPlaylistVideoContainer() {

        return playlistVideoContainer;
    }

    @Override
    public int getPosition() {

        return position;
    }

    @Override
    public void onSuccess(ContentContainer container) {

        playlistVideoContainer.getItems().addAll(container.getItems());
        playlistVideoContainer.setPageToken(container.getPageToken());

        super.getView().updateBottomFragment(playlistVideoContainer.getItems().get(position), playlistVideoContainer.getChannelItem());
    }

    @Override
    public void onFailure() {

    }
}

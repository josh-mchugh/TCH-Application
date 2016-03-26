package com.redrumming.thecreaturehub.view.fragments.player.playlist;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.redrumming.thecreaturehub.api.youtube.channel.model.Channel;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoContainer;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoContainerFactory;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoItem;
import com.redrumming.thecreaturehub.models.content.video.VideoItem;
import com.redrumming.thecreaturehub.view.fragments.player.PlayerFragmentPresenterImpl;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ME on 1/9/2016.
 */
public class PlaylistPlayerFragmentPresenterImpl extends PlayerFragmentPresenterImpl implements PlaylistPlayerFragmentPresenter {

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

            Observable.just(playlistVideoContainer)
                    .map(new Func1<PlaylistVideoContainer, PlaylistVideoContainer>() {

                        @Override
                        public PlaylistVideoContainer call(PlaylistVideoContainer container) {

                            PlaylistVideoContainer updatedContainer = null;

                            try{

                                updatedContainer = PlaylistVideoContainerFactory.createPlaylistVideoContainer(((PlaylistPlayerFragmentView)getView()).getContext(), container);

                            }catch (Exception e){

                                Log.e(this.getClass().getName(), "Error updating PlaylistVideoContainer with new videos within player fragment.", e);
                            }

                            return updatedContainer;
                        }
                    }).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<PlaylistVideoContainer>() {

                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(PlaylistVideoContainer container) {

                            if(container != null) {

                                playlistVideoContainer.getItems().addAll(container.getItems());
                                playlistVideoContainer.setPageToken(container.getPageToken());

                                PlaylistPlayerFragmentPresenterImpl.this.getView().updateBottomFragment(playlistVideoContainer.getItems().get(position), playlistVideoContainer.getChannel());
                            }
                        }
                    });

            super.getView().updateBottomFragment(playlistVideoContainer.getItems().get(position), playlistVideoContainer.getChannel());

        }else if(position > lastVideo){

            super.getView().setLoading(true);

        }else {

            super.getView().updateBottomFragment(playlistVideoContainer.getItems().get(position), playlistVideoContainer.getChannel());
        }
    }

    @Override
    public void onPrevious() {
        super.onPrevious();

        position--;

        super.getView().updateBottomFragment(playlistVideoContainer.getItems().get(position), playlistVideoContainer.getChannel());
    }

    @Override
    public VideoItem getVideoItem() {

        return (PlaylistVideoItem) playlistVideoContainer.getItems().get(position);
    }

    @Override
    public Channel getChannelItem() {

        return playlistVideoContainer.getChannel();
    }


    @Override
    public Parcelable getPlaylistVideoContainer() {

        return playlistVideoContainer;
    }

    @Override
    public int getPosition() {

        return position;
    }
}

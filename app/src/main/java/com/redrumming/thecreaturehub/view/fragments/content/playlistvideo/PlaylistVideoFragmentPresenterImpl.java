package com.redrumming.thecreaturehub.view.fragments.content.playlistvideo;

import android.os.Parcelable;
import android.util.Log;

import com.redrumming.thecreaturehub.api.youtube.channel.model.Channel;
import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.models.content.ContentType;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoContainer;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoContainerFactory;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoItem;
import com.redrumming.thecreaturehub.view.fragments.content.ContentFragmentPresenterImpl;

import rx.Observer;
import rx.functions.Func1;

/**
 * Created by ME on 1/8/2016.
 */
public class PlaylistVideoFragmentPresenterImpl extends ContentFragmentPresenterImpl implements PlaylistVideoFragmentPresenter{

    private PlaylistVideoContainer container;

    public PlaylistVideoFragmentPresenterImpl(PlaylistVideoFragmentView view){
        super(view);
    }

    @Override
    public void onSelect(int position) {

        PlaylistVideoItem playlistVideo = null;
        Channel channel = null;

        if(getContainer().getItems().get(position).getItemType() == ContentType.PLAYLIST_VIDEO_ITEM){

            playlistVideo = (PlaylistVideoItem) getContainer().getItems().get(position);
            channel = getContainer().getChannel();
        }

        ((PlaylistVideoFragmentView) getView()).removePlayerFragment();

        if(playlistVideo != null && channel != null){

            ((PlaylistVideoFragmentView) getView()).addPlayerFragment(getContainer(), playlistVideo.getPosition().intValue());
        }
    }

    @Override
    public void setContainer(Parcelable container) {

        this.container = (PlaylistVideoContainer) container;
    }

    @Override
    public ContentContainer getContainer() {

        if( container == null){

            container = new PlaylistVideoContainer();

            return container;
        }

        return container;
    }

    @Override
    public void setContainer(PlaylistVideoContainer container) {

        this.container = container;
    }

    @Override
    public Observer<ContentContainer> getObserver() {

        return new Observer<ContentContainer>() {



            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                Log.e(this.getClass().getName(), "Error occurred trying to fetch videos of a playlist.", e);
            }

            @Override
            public void onNext(ContentContainer contentContainer) {

                PlaylistVideoFragmentPresenterImpl.this.updateView(contentContainer);
                Log.i(this.getClass().getName(), "Recieved playlist videos for channel id: " + contentContainer.getChannel().getSnippet().getTitle() + " with page token: " + contentContainer.getPageToken());
            }
        };
    }

    @Override
    public Func1<ContentContainer, ContentContainer> onCall(ContentContainer contentContainer) {

        return new Func1<ContentContainer, ContentContainer>() {

            @Override
            public ContentContainer call(ContentContainer contentContainer) {

                PlaylistVideoContainer updatedContainer = null;

                try{

                    updatedContainer = PlaylistVideoContainerFactory.createPlaylistVideoContainer(PlaylistVideoFragmentPresenterImpl.this.getView().getContext(), (PlaylistVideoContainer) contentContainer);

                }catch(Exception e){

                    Log.e(this.getClass().getName(), "Error trying to retrieve playlist videos within playlist video fragment.", e);
                }

                return updatedContainer;
            }
        };
    }
}

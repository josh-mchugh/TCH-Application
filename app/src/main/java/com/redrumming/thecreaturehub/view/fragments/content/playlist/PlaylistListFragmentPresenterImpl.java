package com.redrumming.thecreaturehub.view.fragments.content.playlist;

import android.os.Parcelable;
import android.util.Log;

import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.models.content.ContentType;
import com.redrumming.thecreaturehub.models.content.playlist.PlaylistContainer;
import com.redrumming.thecreaturehub.models.content.playlist.PlaylistContainerFactory;
import com.redrumming.thecreaturehub.models.content.playlist.PlaylistItem;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoContainer;
import com.redrumming.thecreaturehub.view.fragments.content.ContentFragmentPresenterImpl;

import rx.Observer;
import rx.functions.Func1;

/**
 * Created by ME on 1/8/2016.
 */
public class PlaylistListFragmentPresenterImpl extends ContentFragmentPresenterImpl implements PlaylistListFragmentPresenter {

    private PlaylistContainer container;

    public PlaylistListFragmentPresenterImpl(PlaylistListFragmentView view){
        super(view);
    }

    @Override
    public void onSelect(int position) {

        PlaylistItem playlist = null;

        if(getContainer().getItems().get(position).getItemType() == ContentType.PLAYLIST_ITEM){

            playlist = (PlaylistItem) getContainer().getItems().get(position);
        }

        ((PlaylistListFragmentView) getView()).removePlaylistVideoFragment();

        if(playlist != null){

            PlaylistVideoContainer playlistVideoContainer = new PlaylistVideoContainer();
            playlistVideoContainer.setPageToken("");
            playlistVideoContainer.setPlaylistId(playlist.getId());
            playlistVideoContainer.setChannel(container.getChannel());

            ((PlaylistListFragmentView) getView()).addPlaylistVideoFragment(playlistVideoContainer, playlist.getTitle());
        }
    }

    @Override
    public void setContainer(Parcelable container) {

    }

    @Override
    public ContentContainer getContainer() {

        if(container == null){

            container = new PlaylistContainer();

            return container;
        }

        return container;
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

                PlaylistListFragmentPresenterImpl.this.updateView(contentContainer);
                Log.i(this.getClass().getName(), "Recieved Playlist information for channel id: " + contentContainer.getChannel().getSnippet().getTitle() + " with page token: " + contentContainer.getPageToken());
            }
        };
    }

    @Override
    public Func1<ContentContainer, ContentContainer> onCall(ContentContainer contentContainer) {

        return new Func1<ContentContainer, ContentContainer>() {

            @Override
            public ContentContainer call(ContentContainer contentContainer) {

                PlaylistContainer updatedContainer = null;

                try{

                    updatedContainer = PlaylistContainerFactory.createPlaylistContainer(PlaylistListFragmentPresenterImpl.this.getView().getContext(), (PlaylistContainer) contentContainer);

                }catch(Exception e){

                    Log.e(this.getClass().getName(), "Error trying to retrieve playlist information for the playlist fragment.", e);
                }

                return updatedContainer;
            }
        };
    }


}

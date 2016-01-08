package com.redrumming.thecreaturehub.view.fragments.content.playlist;

import android.os.Parcelable;

import com.redrumming.thecreaturehub.async.content.ContentAsync;
import com.redrumming.thecreaturehub.async.content.playlist.PlaylistAsync;
import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.models.content.ContentType;
import com.redrumming.thecreaturehub.models.content.playlist.PlaylistContainer;
import com.redrumming.thecreaturehub.models.content.playlist.PlaylistItem;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoContainer;
import com.redrumming.thecreaturehub.view.fragments.content.ContentFragmentPresenterImpl;

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
            playlistVideoContainer.setChannelItem(container.getChannelItem());

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
    public ContentAsync getAsync() {

        return new PlaylistAsync(getView().getContext(), this);
    }
}

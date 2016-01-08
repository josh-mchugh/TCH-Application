package com.redrumming.thecreaturehub.view.fragments.content.playlistvideo;

import android.os.Parcelable;

import com.redrumming.thecreaturehub.async.content.ContentAsync;
import com.redrumming.thecreaturehub.async.content.playlistvideo.PlaylistVideoAsync;
import com.redrumming.thecreaturehub.models.channel.ChannelItem;
import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.models.content.ContentType;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoContainer;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoItem;
import com.redrumming.thecreaturehub.view.fragments.content.ContentFragmentPresenterImpl;

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
        ChannelItem channelItem = null;

        if(getContainer().getItems().get(position).getItemType() == ContentType.PLAYLIST_VIDEO_ITEM){

            playlistVideo = (PlaylistVideoItem) getContainer().getItems().get(position);
            channelItem = getContainer().getChannelItem();
        }

        ((PlaylistVideoFragmentView) getView()).removePlayerFragment();

        if(playlistVideo != null && channelItem != null){

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
    public ContentAsync getAsync() {

        return new PlaylistVideoAsync(getView().getContext(), this);
    }

    @Override
    public void setContainer(PlaylistVideoContainer container) {

        this.container = container;
    }
}

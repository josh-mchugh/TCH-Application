package com.redrumming.thecreaturehub.view.fragments.content.video;

import android.os.Parcelable;

import com.redrumming.thecreaturehub.async.content.ContentAsync;
import com.redrumming.thecreaturehub.async.content.video.VideoAsync;
import com.redrumming.thecreaturehub.models.channel.ChannelItem;
import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.models.content.ContentType;
import com.redrumming.thecreaturehub.models.content.video.VideoContainer;
import com.redrumming.thecreaturehub.models.content.video.VideoItem;
import com.redrumming.thecreaturehub.view.fragments.content.ContentFragmentPresenterImpl;
import com.redrumming.thecreaturehub.view.fragments.content.ContentFragmentView;

/**
 * Created by ME on 1/8/2016.
 */
public class VideoListFragmentPresenterImpl extends ContentFragmentPresenterImpl implements VideoListFragmentPresenter {

    private VideoContainer container;

    public VideoListFragmentPresenterImpl(ContentFragmentView view){

        super(view);
    }

    @Override
    public void onSelect(int position) {

        VideoItem video = null;
        ChannelItem channelItem = null;

        if(getContainer().getItems().get(position).getItemType() == ContentType.VIDEO_ITEM){

            video = (VideoItem) getContainer().getItems().get(position);
            channelItem = getContainer().getChannelItem();
        }

        ((VideoListFragmentView) getView()).removePlayerFragment();

        if(video != null && channelItem != null) {

            ((VideoListFragmentView) getView()).addPlayerFragment(video, channelItem);
        }
    }

    @Override
    public void setContainer(Parcelable container) {

        this.container = (VideoContainer) container;
    }

    @Override
    public ContentContainer getContainer() {

        if(container == null){

            container = new VideoContainer();

            return container;
        }

        return container;
    }

    @Override
    public ContentAsync getAsync() {

        return new VideoAsync(getView().getContext(), this);
    }
}

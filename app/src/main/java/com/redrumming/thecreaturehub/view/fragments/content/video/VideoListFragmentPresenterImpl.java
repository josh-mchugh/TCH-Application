package com.redrumming.thecreaturehub.view.fragments.content.video;

import android.os.Parcelable;
import android.util.Log;

import com.redrumming.thecreaturehub.models.channel.ChannelItem;
import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.models.content.ContentType;
import com.redrumming.thecreaturehub.models.content.video.VideoContainer;
import com.redrumming.thecreaturehub.models.content.video.VideoContainerFactory;
import com.redrumming.thecreaturehub.models.content.video.VideoItem;
import com.redrumming.thecreaturehub.view.fragments.content.ContentFragmentPresenterImpl;
import com.redrumming.thecreaturehub.view.fragments.content.ContentFragmentView;

import rx.Observer;

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
    public Observer<ContentContainer> getObserver() {

        return new Observer<ContentContainer>() {

            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {

                Log.e(this.getClass().getName(), "Error retrieving video list.", e);
            }

            @Override
            public void onNext(ContentContainer contentContainer) {

                VideoListFragmentPresenterImpl.this.updateView(contentContainer);
                Log.i(getClass().getName(), "Retreieved video informnation for channel: " + contentContainer.getChannelItem().getChannelName() + " with page token: " + contentContainer.getPageToken());
            }
        };
    }

    /**
     * Call to YouTube API to retrieve more videos, this method should be ran on a thread other than
     * the main thread as it calls out to the YouTube servers.
     *
     * @param contentContainer
     * @return
     */
    @Override
    public ContentContainer onCall(ContentContainer contentContainer) {

        VideoContainer updatedContainer = null;

        try {

            updatedContainer = VideoContainerFactory.createVideoContainer(super.getView().getContext(), (VideoContainer) contentContainer);

        }catch(Exception e){

            Log.e(this.getClass().getName(), "Error trying trying to call server for video information within video fragment.", e);
        }

        return updatedContainer;
    }
}

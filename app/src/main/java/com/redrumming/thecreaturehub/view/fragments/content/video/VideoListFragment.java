package com.redrumming.thecreaturehub.view.fragments.content.video;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.async.content.video.VideoAsync;
import com.redrumming.thecreaturehub.view.viewholders.content.video.VideoRecyclerAdapter;
import com.redrumming.thecreaturehub.models.content.video.VideoContainer;
import com.redrumming.thecreaturehub.models.content.video.VideoItem;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.models.channel.ChannelItem;
import com.redrumming.thecreaturehub.async.content.ContentAsync;
import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.view.fragments.content.ContentFragment;
import com.redrumming.thecreaturehub.models.content.ContentType;
import com.redrumming.thecreaturehub.view.viewholders.content.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.view.fragments.player.PlayerFragment;
import com.redrumming.thecreaturehub.view.fragments.player.video.VideoPlayerFragment;

/**
 *
 */
public class VideoListFragment extends ContentFragment {

    private VideoContainer container;
    private VideoRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){

            container = savedInstanceState.getParcelable("container");

        }else {

            container = new VideoContainer();
        }

        adapter = new VideoRecyclerAdapter(container);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        return super.onCreateView(inflater, viewGroup, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("container", container);
    }

    @Override
    public void onSelect(int position){

        VideoItem video = null;
        ChannelItem channelItem = null;

        if(getContainer().getItems().get(position).getItemType() == ContentType.VIDEO_ITEM){

            video = (VideoItem) getContainer().getItems().get(position);
            channelItem = getContainer().getChannelItem();
        }

        Fragment fragment = getParentFragment().getFragmentManager().findFragmentByTag(PlayerFragment.TAG);

        if(fragment != null){

            if(fragment instanceof PlayerFragment){

                getParentFragment().getFragmentManager().beginTransaction().remove(fragment).commit();
                getParentFragment().getFragmentManager().popBackStack();
            }
        }

        if(video != null && channelItem != null) {

            VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();
            videoPlayerFragment.setArguments(new Bundle());
            videoPlayerFragment.getArguments().putParcelable("video", video);
            videoPlayerFragment.getArguments().putParcelable("channel", channelItem);

            getParentFragment()
                    .getFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_layout, videoPlayerFragment, PlayerFragment.TAG)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public ContentContainer getContainer() {

        return container;
    }

    @Override
    public ContentAsync getAsync() {

        return new VideoAsync(getActivity(), this);
    }

    @Override
    public ContentRecyclerAdapter getAdapter() {

        return adapter;
    }
}
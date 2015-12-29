package com.redrumming.thecreaturehub.contentItems.video;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.FragmentTags;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.ChannelItem;
import com.redrumming.thecreaturehub.contentItems.ContentAsync;
import com.redrumming.thecreaturehub.contentItems.ContentContainer;
import com.redrumming.thecreaturehub.contentItems.ContentFragment;
import com.redrumming.thecreaturehub.contentItems.ContentType;
import com.redrumming.thecreaturehub.contentItems.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.player.PlayerFragment;
import com.redrumming.thecreaturehub.player.VideoPlayerFragment;

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

        Fragment fragment = getParentFragment().getFragmentManager().findFragmentByTag(FragmentTags.PLAYER_FRAGMENT);

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
                    .add(R.id.activity_layout, videoPlayerFragment, FragmentTags.PLAYER_FRAGMENT)
                    .addToBackStack(FragmentTags.CONTENT_ACTIVITY)
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
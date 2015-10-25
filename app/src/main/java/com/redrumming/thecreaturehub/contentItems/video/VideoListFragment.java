package com.redrumming.thecreaturehub.contentItems.video;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.redrumming.thecreaturehub.contentItems.ContentAsync;
import com.redrumming.thecreaturehub.contentItems.ContentContainer;
import com.redrumming.thecreaturehub.contentItems.ContentFragment;
import com.redrumming.thecreaturehub.contentItems.ContentItem;
import com.redrumming.thecreaturehub.contentItems.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.player.VideoPlayer;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.Channel;

/**
 *
 */
public class VideoListFragment extends ContentFragment {

    private VideoContainer container = new VideoContainer();
    private VideoRecyclerAdapter adapter = new VideoRecyclerAdapter(container);

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        return super.onCreateView(inflater, viewGroup, savedInstanceState);
    }

    @Override
    public void onSelect(int position){

        VideoItem video = null;
        Channel channel = null;

        if(getContainer().getItems().get(position).getItemType() == ContentItem.VIDEO_ITEM){

            video = (VideoItem) getContainer().getItems().get(position);
            channel = getContainer().getChannel();
        }

        if(video != null && channel != null) {

            Toast.makeText(getActivity(), "Video Id: " + video.getVideoId(), Toast.LENGTH_SHORT).show();

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            VideoPlayer videoPlayer = new VideoPlayer();

            videoPlayer.setArguments(new Bundle());
            videoPlayer.getArguments().putSerializable("video", video);
            videoPlayer.getArguments().putSerializable("channel", channel);

            ft.replace(R.id.content, videoPlayer);
            ft.addToBackStack("tabbedFragment");
            ft.commit();
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
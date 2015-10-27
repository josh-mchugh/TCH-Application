package com.redrumming.thecreaturehub.contentItems.PlaylistVideo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.Channel;
import com.redrumming.thecreaturehub.contentItems.ContentAsync;
import com.redrumming.thecreaturehub.contentItems.ContentContainer;
import com.redrumming.thecreaturehub.contentItems.ContentFragment;
import com.redrumming.thecreaturehub.contentItems.ContentItem;
import com.redrumming.thecreaturehub.contentItems.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.player.PlaylistPlayer;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistVideoFragment extends ContentFragment{

    private PlaylistVideoContainer container = new PlaylistVideoContainer();
    private PlaylistVideoRecyclerAdapter adapter = new PlaylistVideoRecyclerAdapter(container);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        return super.onCreateView(inflater, viewGroup, savedInstanceState);
    }

    public void setupPlaylist(PlaylistVideoContainer contentContainer){

        container.setChannel(contentContainer.getChannel());
        container.setPageToken(contentContainer.getPageToken());
        container.setPlaylistId(contentContainer.getPlaylistId());

        super.setup(container.getChannel(), new PlaylistVideoAsync(getActivity(), this));
    }

    @Override
    public void onSelect(int position){

        PlaylistVideoItem video = null;
        Channel channel = null;

        if(getContainer().getItems().get(position).getItemType() == ContentItem.PLAYLIST_VIDEO_ITEM){

            video = (PlaylistVideoItem) getContainer().getItems().get(position);
            channel = getContainer().getChannel();
        }

        if(video != null && channel != null){

            Toast.makeText(getActivity(), "Playlist Video Id: " + video.getVideoId(), Toast.LENGTH_SHORT).show();

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            PlaylistPlayer videoPlayer = new PlaylistPlayer();

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

        return new PlaylistVideoAsync(getActivity(), this);
    }

    @Override
    public ContentRecyclerAdapter getAdapter() {

        return adapter;
    }
}
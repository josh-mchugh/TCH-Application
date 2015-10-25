package com.redrumming.thecreaturehub.contentItems.playlist;

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
import com.redrumming.thecreaturehub.contentItems.PlaylistVideo.PlaylistVideoContainer;
import com.redrumming.thecreaturehub.contentItems.PlaylistVideo.PlaylistVideoFragment;
import com.redrumming.thecreaturehub.R;

/**
 *
 */
public class PlaylistListFragment extends ContentFragment{

    private PlaylistContainer container = new PlaylistContainer();
    private PlaylistRecyclerAdapter adapter = new PlaylistRecyclerAdapter(container);

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

        PlaylistItem playlist = null;

        if(getContainer().getItems().get(position).getItemType() == ContentItem.PLAYLIST_ITEM){

            playlist = (PlaylistItem) getContainer().getItems().get(position);
        }

        if(playlist != null){

            Toast.makeText(getActivity(), "Playlist Id: " + playlist.getId(), Toast.LENGTH_SHORT).show();

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            PlaylistVideoFragment playlistVideoFragment = new PlaylistVideoFragment();

            PlaylistVideoContainer container = new PlaylistVideoContainer();
            container.setChannel(getContainer().getChannel());
            container.setPageToken("");
            container.setPlaylistId(playlist.getId());

            ft.replace(R.id.content, playlistVideoFragment);
            ft.addToBackStack("tabbedFragment");
            ft.commit();

            playlistVideoFragment.setupPlaylist(container);
        }
    }

    @Override
    public ContentContainer getContainer() {

        return container;
    }

    @Override
    public ContentAsync getAsync() {

        return new PlaylistAsync(getActivity(), this);
    }

    @Override
    public ContentRecyclerAdapter getAdapter() {

        return adapter;
    }
}

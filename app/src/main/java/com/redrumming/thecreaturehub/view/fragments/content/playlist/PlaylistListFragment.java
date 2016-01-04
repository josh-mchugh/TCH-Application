package com.redrumming.thecreaturehub.view.fragments.content.playlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.async.content.playlist.PlaylistAsync;
import com.redrumming.thecreaturehub.view.viewholders.content.playlist.PlaylistRecyclerAdapter;
import com.redrumming.thecreaturehub.models.content.playlist.PlaylistContainer;
import com.redrumming.thecreaturehub.models.content.playlist.PlaylistItem;
import com.redrumming.thecreaturehub.async.content.ContentAsync;
import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.models.content.ContentType;
import com.redrumming.thecreaturehub.view.viewholders.content.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoContainer;
import com.redrumming.thecreaturehub.view.fragments.content.playlistvideo.PlaylistVideoFragment;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.view.fragments.content.ContentFragment;

/**
 *
 */
public class PlaylistListFragment extends ContentFragment {

    private PlaylistContainer container;
    private PlaylistRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){

            container = savedInstanceState.getParcelable("container");

        }else {

            container = new PlaylistContainer();
        }

        adapter = new PlaylistRecyclerAdapter(container);
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

        PlaylistItem playlist = null;

        if(getContainer().getItems().get(position).getItemType() == ContentType.PLAYLIST_ITEM){

            playlist = (PlaylistItem) getContainer().getItems().get(position);
        }

        Fragment fragment = getFragmentManager().findFragmentByTag(PlaylistVideoFragment.TAG);

        if(fragment instanceof PlaylistVideoFragment) {

            if (fragment != null) {

                getFragmentManager().beginTransaction().remove(fragment);
            }
        }

        if(playlist != null){

            PlaylistVideoContainer playlistVideoContainer = new PlaylistVideoContainer();
            playlistVideoContainer.setPageToken("");
            playlistVideoContainer.setPlaylistId(playlist.getId());
            playlistVideoContainer.setChannelItem(container.getChannelItem());

            PlaylistVideoFragment playlistVideoFragment = new PlaylistVideoFragment();
            playlistVideoFragment.setArguments(new Bundle());
            playlistVideoFragment.getArguments().putParcelable("container", playlistVideoContainer);

            getParentFragment()
                   .getFragmentManager()
                    .beginTransaction()
                    .add(R.id.drawer_layout, playlistVideoFragment, PlaylistVideoFragment.TAG)
                    .addToBackStack(null)
                    .commit();

            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(playlist.getTitle());
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

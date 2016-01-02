package com.redrumming.thecreaturehub.contentItems.playlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.util.FragmentTags;
import com.redrumming.thecreaturehub.contentItems.ContentAsync;
import com.redrumming.thecreaturehub.contentItems.ContentContainer;
import com.redrumming.thecreaturehub.contentItems.ContentFragment;
import com.redrumming.thecreaturehub.contentItems.ContentType;
import com.redrumming.thecreaturehub.contentItems.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.contentItems.PlaylistVideo.PlaylistVideoContainer;
import com.redrumming.thecreaturehub.contentItems.PlaylistVideo.PlaylistVideoFragment;
import com.redrumming.thecreaturehub.R;

/**
 *
 */
public class PlaylistListFragment extends ContentFragment{

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

        Fragment fragment = getFragmentManager().findFragmentByTag(FragmentTags.PLAYLIST_VIDEO_FRAGMENT);

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
                    .add(R.id.drawer_layout, playlistVideoFragment, FragmentTags.PLAYLIST_VIDEO_FRAGMENT)
                    .addToBackStack(FragmentTags.TABBED_FRAGMENT)
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

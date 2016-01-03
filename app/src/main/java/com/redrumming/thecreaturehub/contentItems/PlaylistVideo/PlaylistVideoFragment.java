package com.redrumming.thecreaturehub.contentItems.PlaylistVideo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.util.FragmentTags;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.ChannelItem;
import com.redrumming.thecreaturehub.contentItems.ContentAsync;
import com.redrumming.thecreaturehub.contentItems.ContentContainer;
import com.redrumming.thecreaturehub.contentItems.ContentFragment;
import com.redrumming.thecreaturehub.contentItems.ContentType;
import com.redrumming.thecreaturehub.contentItems.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.navigation.NavigationDrawerHelper;
import com.redrumming.thecreaturehub.player.PlayerFragment;
import com.redrumming.thecreaturehub.player.PlaylistPlayerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistVideoFragment extends ContentFragment{

    public static final String TAG = "PlaylistVideoFragment";

    private PlaylistVideoContainer container;
    private PlaylistVideoRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){

            container = getArguments().getParcelable("container");
        }

        if(savedInstanceState != null){

            container = savedInstanceState.getParcelable("container");
        }

        if(savedInstanceState == null && getArguments() == null) {

            container = new PlaylistVideoContainer();
        }

        adapter = new PlaylistVideoRecyclerAdapter(container);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, viewGroup, savedInstanceState);

        NavigationDrawerHelper.get().getDrawerToggle().setDrawerIndicatorEnabled(false);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("container", container);
    }

    @Override
    public void onSelect(int position){

        PlaylistVideoItem playlistVideo = null;
        ChannelItem channelItem = null;

        if(getContainer().getItems().get(position).getItemType() == ContentType.PLAYLIST_VIDEO_ITEM){

            playlistVideo = (PlaylistVideoItem) getContainer().getItems().get(position);
            channelItem = getContainer().getChannelItem();
        }

       Fragment fragment = getFragmentManager().findFragmentByTag(FragmentTags.PLAYER_FRAGMENT);

        if(fragment != null){

            if(fragment instanceof PlayerFragment){

                getFragmentManager().beginTransaction().remove(fragment);
                getFragmentManager().popBackStack();
            }
        }

        if(playlistVideo != null && channelItem != null){

            PlaylistPlayerFragment playlistPlayerFragment = new PlaylistPlayerFragment();
            playlistPlayerFragment.setArguments(new Bundle());
            playlistPlayerFragment.getArguments().putParcelable("container", container);
            playlistPlayerFragment.getArguments().putInt("position", position);

            getFragmentManager()
                   .beginTransaction()
                    .add(R.id.activity_layout, playlistPlayerFragment,FragmentTags.PLAYER_FRAGMENT)
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

        return new PlaylistVideoAsync(getActivity(), this);
    }

    @Override
    public ContentRecyclerAdapter getAdapter() {

        return adapter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(container.getChannelItem().getChannelName());
        NavigationDrawerHelper.get().getDrawerToggle().setDrawerIndicatorEnabled(true);
    }
}
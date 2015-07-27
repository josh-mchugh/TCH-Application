package com.redrumming.thecreaturehub;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.playlist.PlaylistListFragment;
import com.redrumming.thecreaturehub.video.VideoListFragment;

/**
 *
 */
public class TabView extends Fragment{

    private final String VIDEO_FRAG_TAG = "videos";
    private final String PLAYLIST_FRAG_TAG = "playlists";
    private FragmentTabHost tabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tab_view, container, false);

        tabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        tabHost.setup(this.getActivity(), getFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec(VIDEO_FRAG_TAG).setIndicator(VIDEO_FRAG_TAG), VideoListFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec(PLAYLIST_FRAG_TAG).setIndicator(PLAYLIST_FRAG_TAG), PlaylistListFragment.class, null);

        return view;
    }

    public String getCurrentTabTag(){
        return tabHost.getCurrentTabTag();
    }

    public String getVideoFragmentTag() {
        return VIDEO_FRAG_TAG;
    }

    public String getPlaylistFragmentTag() {
        return PLAYLIST_FRAG_TAG;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

package com.redrumming.thecreaturehub.view.tablayout.pager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.models.channel.ChannelsContainer;
import com.redrumming.thecreaturehub.view.fragments.content.playlist.PlaylistListFragment;
import com.redrumming.thecreaturehub.view.fragments.content.video.VideoListFragment;

/**
 * Created by ME on 10/14/2015.
 */
public class NavigationPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 2;

    private final int VIDEOS_LIST_POSITION = 0;
    private final int PLAYLIST_LIST_POSITION = 1;

    private String[] tabTitles = new String[] {"Videos", "Playlist"};

    private VideoListFragment videoListFragment;
    private PlaylistListFragment playlistListFragment;

    public NavigationPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case VIDEOS_LIST_POSITION:

                return new VideoListFragment();

            case PLAYLIST_LIST_POSITION:

                return new PlaylistListFragment();

            default:

                return new VideoListFragment();
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);

        switch (position){

            case VIDEOS_LIST_POSITION:

                videoListFragment = (VideoListFragment) createdFragment;
                break;

            case PLAYLIST_LIST_POSITION:

                playlistListFragment = (PlaylistListFragment) createdFragment;
                break;
        }

        return createdFragment;
    }

    @Override
    public int getCount() {

        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];
    }

    public void updateFragments(){

        if(videoListFragment != null){

            videoListFragment.setup(ChannelsContainer.getInstance().getSelectedChannel());
        }

        if(playlistListFragment != null){

            playlistListFragment.setup(ChannelsContainer.getInstance().getSelectedChannel());
        }
    }
}

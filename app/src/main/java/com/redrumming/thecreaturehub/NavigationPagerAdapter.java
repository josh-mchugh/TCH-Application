package com.redrumming.thecreaturehub;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.redrumming.thecreaturehub.contentItems.playlist.PlaylistListFragment;
import com.redrumming.thecreaturehub.contentItems.video.VideoListFragment;

/**
 * Created by ME on 10/14/2015.
 */
public class NavigationPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 2;
    private String[] tabTitles = new String[] {"Videos", "Playlists"};

    private VideoListFragment videoListFragment = new VideoListFragment();
    private PlaylistListFragment playlistListFragment = new PlaylistListFragment();

    public NavigationPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){

            case 0:
                return videoListFragment;

            case 1:
                return playlistListFragment;

            default:
                return videoListFragment;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    public VideoListFragment getVideoListFragment(){

        return videoListFragment;
    }

    public PlaylistListFragment getPlaylistListFragment(){

        return playlistListFragment;
    }
}

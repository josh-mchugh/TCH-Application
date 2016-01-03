package com.redrumming.thecreaturehub.activities.main;

import android.app.Activity;
import android.content.res.Configuration;
import android.view.MenuItem;

import com.redrumming.thecreaturehub.channel.ChannelsContainer;
import com.redrumming.thecreaturehub.navigation.NavigationDrawerHelper;

/**
 * Created by ME on 1/2/2016.
 */
public class EntryActivityPresenterImpl implements EntryActivityPresenter{

    private EntryActivityView view;

    public EntryActivityPresenterImpl(EntryActivityView view){
        this.view = view;
    }

    @Override
    public String setTitle() {

        return ChannelsContainer.getInstance().getSelectedChannel().getChannelName();
    }

    @Override
    public void initDrawer(Activity activity) {

        NavigationDrawerHelper.get().init(activity);
    }

    @Override
    public void drawerSyncState() {

        NavigationDrawerHelper.get().getDrawerToggle().syncState();
    }

    @Override
    public void drawerConfigurationChanged(Configuration newConfig) {

        NavigationDrawerHelper.get().getDrawerToggle().onConfigurationChanged(newConfig);
    }

    @Override
    public boolean isDrawerOpen() {

        return NavigationDrawerHelper.get().isDrawerOpen();
    }

    @Override
    public void closeDrawer() {

        NavigationDrawerHelper.get().closeDrawer();
    }

    @Override
    public boolean onDrawerOptionsItemSelected(MenuItem item) {

        return NavigationDrawerHelper.get().onDrawerOptionsItemSelected(item);
    }
}

package com.redrumming.thecreaturehub.activities.main;

import android.app.Activity;
import android.content.res.Configuration;
import android.view.MenuItem;

/**
 * Created by ME on 1/2/2016.
 */
public interface EntryActivityPresenter {

    void initDrawer(Activity activity);
    void drawerSyncState();
    void drawerConfigurationChanged(Configuration newConfig);
    boolean isDrawerOpen();
    void closeDrawer();
    boolean onDrawerOptionsItemSelected(MenuItem item);
    String setTitle();
}

package com.redrumming.thecreaturehub.activities.main;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.redrumming.thecreaturehub.util.FragmentTags;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.ChannelsContainer;
import com.redrumming.thecreaturehub.contentItems.PlaylistVideo.PlaylistVideoFragment;
import com.redrumming.thecreaturehub.navigation.NavigationDrawerHelper;
import com.redrumming.thecreaturehub.navigation.TabbedContent;

public class EntryActivity extends AppCompatActivity {


    private TabbedContent tabbedContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setTitle(ChannelsContainer.getInstance().getSelectedChannel().getChannelName());

        //init Drawer
        NavigationDrawerHelper.get().init(this);

        if(savedInstanceState != null) {

            tabbedContent = (TabbedContent) getSupportFragmentManager().getFragment(savedInstanceState, "TabbedContentFragment");

        }else {

            tabbedContent = new TabbedContent();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawer_content, tabbedContent, FragmentTags.TABBED_CONTENT_FRAGMENT)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState, "TabbedContentFragment", tabbedContent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(NavigationDrawerHelper.get().getDrawerToggle().onOptionsItemSelected(item)){

            return true;
        }

        if(id == android.R.id.home){

            Fragment fragment = getSupportFragmentManager().findFragmentByTag(FragmentTags.PLAYLIST_VIDEO_FRAGMENT);

            if(fragment != null){

                if(fragment instanceof PlaylistVideoFragment){

                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    getSupportFragmentManager().popBackStack();
                }
            }
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        NavigationDrawerHelper.get().getDrawerToggle().syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        NavigationDrawerHelper.get().getDrawerToggle().onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {

        if(getFragmentManager().getBackStackEntryCount() != 0){

            getFragmentManager().popBackStack();

        }else if(NavigationDrawerHelper.get().getDrawerLayout().isDrawerOpen(GravityCompat.START)) {

            NavigationDrawerHelper.get().getDrawerLayout().closeDrawer(GravityCompat.START);

        }else{

            super.onBackPressed();
        }
    }


}
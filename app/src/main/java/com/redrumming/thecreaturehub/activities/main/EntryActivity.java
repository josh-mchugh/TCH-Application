package com.redrumming.thecreaturehub.activities.main;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.contentItems.PlaylistVideo.PlaylistVideoFragment;
import com.redrumming.thecreaturehub.navigation.TabbedContent;
import com.redrumming.thecreaturehub.navigation.drawer.NavigationDrawerHelper;

public class EntryActivity extends AppCompatActivity implements EntryActivityView {

    private TabbedContent tabbedContent;

    private EntryActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        //init Presenter
        presenter = new EntryActivityPresenterImpl(this);

        //init ToolBar
        initToolBar();

        //init Drawer
        NavigationDrawerHelper.get().init(this);

        initSavedInstance(savedInstanceState);
    }

    private void initToolBar(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setTitle(presenter.setTitle());
    }

    private void initSavedInstance(Bundle savedInstanceState){

        if(savedInstanceState != null) {

            tabbedContent = (TabbedContent) getSupportFragmentManager().getFragment(savedInstanceState, "TabbedContentFragment");

        }else {

            tabbedContent = new TabbedContent();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawer_content, tabbedContent, TabbedContent.TAG)
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

        if(NavigationDrawerHelper.get().onDrawerOptionsItemSelected(item)){

            return true;
        }

        if(id == android.R.id.home){

            Fragment fragment = getSupportFragmentManager().findFragmentByTag(PlaylistVideoFragment.TAG);

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

        NavigationDrawerHelper.get().syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        NavigationDrawerHelper.get().onConfigurationChange(newConfig);
    }

    @Override
    public void onBackPressed() {

        if(getFragmentManager().getBackStackEntryCount() != 0){

            getFragmentManager().popBackStack();

        }else if(NavigationDrawerHelper.get().isDrawerOpen()) {

            NavigationDrawerHelper.get().closeDrawer();

        }else{

            super.onBackPressed();
        }
    }
}
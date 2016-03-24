package com.redrumming.thecreaturehub.view.activity.main;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.api.youtube.YouTubeParams;
import com.redrumming.thecreaturehub.api.youtube.channel.ChannelParams;
import com.redrumming.thecreaturehub.api.youtube.channel.ChannelsAPI;
import com.redrumming.thecreaturehub.api.youtube.channel.model.Channel;
import com.redrumming.thecreaturehub.api.youtube.video.VideosAPI;
import com.redrumming.thecreaturehub.api.youtube.video.VideosParams;
import com.redrumming.thecreaturehub.api.youtube.video.model.Videos;
import com.redrumming.thecreaturehub.retrofit.YouTubeRetrofit;
import com.redrumming.thecreaturehub.view.fragments.content.playlistvideo.PlaylistVideoFragment;
import com.redrumming.thecreaturehub.view.tablayout.TabbedContent;
import com.redrumming.thecreaturehub.view.drawer.NavigationDrawerHelper;
import com.redrumming.thecreaturehub.youtube.YouTubeApiKey;
import com.redrumming.thecreaturehub.api.youtube.search.model.Search;
import com.redrumming.thecreaturehub.api.youtube.search.SearchParameters;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

        Retrofit retrofit = YouTubeRetrofit.build();

        VideosAPI videosAPI = retrofit.create(VideosAPI.class);

        Map<String, String> parameters = new HashMap<>();
        parameters.put(VideosParams.PARAM_PART, VideosParams.VALUE_PART);
        parameters.put(VideosParams.PARAM_FIELDS, VideosParams.VALUE_FIELDS);
        parameters.put(VideosParams.PARAM_MAX_RESULTS, VideosParams.VALUE_MAX_RESULTS);
        parameters.put(VideosParams.PARAM_ID, "M1OlSuMlXMo, vrYhhGaa5mU");
        parameters.put(VideosParams.PARAM_API_KEY, VideosParams.VALUE_API_KEY);

        final Call<Videos> respos = videosAPI.getVideos(parameters);
        respos.enqueue(new Callback<Videos>() {

            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {

                String tag = this.getClass().getName();

                Log.d(tag, "Response code: " + response.code());
                Log.d(tag, "Body: " + response.raw().body().toString());
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {

                Log.e(this.getClass().getName(), "Error in Retrofit test: ", t);

            }
        });
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
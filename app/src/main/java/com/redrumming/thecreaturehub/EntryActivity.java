package com.redrumming.thecreaturehub;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.redrumming.thecreaturehub.channel.Channel;
import com.redrumming.thecreaturehub.drawer.DrawerChannelItem;
import com.redrumming.thecreaturehub.drawer.DrawerHeaderItem;
import com.redrumming.thecreaturehub.drawer.DrawerItem;
import com.redrumming.thecreaturehub.drawer.DrawerRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class EntryActivity extends AppCompatActivity{

    private NavigationPagerAdapter pagerAdapter;

    private DrawerLayout drawerLayout;
    private RecyclerView drawerRecycler;
    private ActionBarDrawerToggle drawerToggle;

    private List<Channel> channels;
    private List<DrawerItem> drawerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        //init channels
        channels = getChannels();

        //init Drawer
        drawerRecycler = (RecyclerView) findViewById(R.id.drawer_recycler_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerItems = getDrawerItems();

        initializeDrawerLayout();
        initializeRecyclerView();

        //Init tabbed content
        pagerAdapter = new NavigationPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);

        pagerAdapter.getVideoListFragment().setup(this, channels.get(0));
        pagerAdapter.getPlaylistListFragment().setup(this, channels.get(0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public List<Channel> getChannels() {

        List<Channel> channels = new ArrayList<Channel>();

        String[] channelNames = getResources().getStringArray(R.array.channel_names);
        String[] channelIds = getResources().getStringArray(R.array.channel_ids);
        String[] channelDisplayIcons = getResources().getStringArray(R.array.channel_display_icons);

        for (int i = 0; i < channelNames.length; i++) {

            Channel channel = new Channel();
            channel.setChannelName(channelNames[i]);
            channel.setChannelId(channelIds[i]);

            int imageResource = this
                    .getResources()
                    .getIdentifier(channelDisplayIcons[i], "drawable", getApplicationContext().getPackageName());

            Drawable displayIcon = getResources().getDrawable(imageResource);
            channel.setDisplayIcon(displayIcon);

            //Add Channel to Channels list
            channels.add(channel);
        }

        return channels;
    }

    private void initializeDrawerLayout(){

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        initializeDrawerToggle();

        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });

        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void initializeDrawerToggle(){

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.ic_drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                supportInvalidateOptionsMenu();
                getSupportActionBar().setTitle(R.string.app_name);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                supportInvalidateOptionsMenu();
            }
        };
    }

    private void initializeRecyclerView(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        drawerRecycler.setLayoutManager(linearLayoutManager);

        DrawerRecyclerAdapter adapter = new DrawerRecyclerAdapter(drawerItems);
        drawerRecycler.setAdapter(adapter);

        drawerRecycler.addOnItemTouchListener(new RecyclerOnItemClickListener(this, onItemClickListener));
    }

    private List<DrawerItem> getDrawerItems(){

        List<DrawerItem> drawerItems = new ArrayList<DrawerItem>();

        DrawerHeaderItem headerItem = new DrawerHeaderItem();
        headerItem.setTitle("Channels");
        drawerItems.add(headerItem);

        for (int i = 0; i < channels.size(); i++) {

            DrawerChannelItem drawerChannel = new DrawerChannelItem();
            drawerChannel.setChannel(channels.get(i));

            drawerItems.add(drawerChannel);
        }

        return drawerItems;
    }

    private void onSelect(int position) {

        DrawerItem drawerItem = drawerItems.get(position);

        if (drawerItem.getType() == DrawerItem.CHANNEL) {

            DrawerChannelItem channelItem = (DrawerChannelItem) drawerItem;
            Channel channel = channelItem.getChannel();

            pagerAdapter.getVideoListFragment().setup(this, channel);
            pagerAdapter.getPlaylistListFragment().setup(this, channel);

            drawerLayout.closeDrawers();

            getSupportActionBar().setTitle(channel.getChannelName());
        }
    }


    private RecyclerOnItemClickListener.OnItemClickListener onItemClickListener = new RecyclerOnItemClickListener.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {

            onSelect(position);
        }
    };
}

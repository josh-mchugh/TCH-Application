package com.redrumming.thecreaturehub.navigation;

import android.app.Activity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.redrumming.thecreaturehub.FragmentTags;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.ChannelItem;
import com.redrumming.thecreaturehub.channel.ChannelsContainer;
import com.redrumming.thecreaturehub.drawer.DrawerChannelItem;
import com.redrumming.thecreaturehub.drawer.DrawerHeaderItem;
import com.redrumming.thecreaturehub.drawer.DrawerItem;
import com.redrumming.thecreaturehub.drawer.DrawerRecyclerAdapter;
import com.redrumming.thecreaturehub.util.RecyclerOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 12/10/2015.
 */
public class NavigationDrawerHelper {

    private static NavigationDrawerHelper INSTANCE = null;

    private Activity activity;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<DrawerItem> drawerItems;

    private RecyclerView recyclerView;

    protected NavigationDrawerHelper(){

    }

    public static NavigationDrawerHelper get(){

        if(INSTANCE == null){

            INSTANCE = new NavigationDrawerHelper();
        }

        return INSTANCE;
    }

    public void init(Activity activity){

        this.activity = activity;

        recyclerView = (RecyclerView) activity.findViewById(R.id.drawer_recycler_view);
        drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);

        drawerItems = initDrawerItems();

        initializeDrawerLayout();
        initializeRecyclerView(itemClickListener);
    }

    private List<DrawerItem> initDrawerItems(){

        List<DrawerItem> drawerItems = new ArrayList<DrawerItem>();

        DrawerHeaderItem headerItem = new DrawerHeaderItem();
        headerItem.setTitle("Channels");
        drawerItems.add(headerItem);

        for (int i = 0; i < ChannelsContainer.getInstance().getChannels().size(); i++) {

            DrawerChannelItem drawerChannel = new DrawerChannelItem();
            drawerChannel.setChannelItem(ChannelsContainer.getInstance().getChannels().get(i));

            drawerItems.add(drawerChannel);
        }

        return drawerItems;
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

        drawerToggle = new ActionBarDrawerToggle(
                activity,
                drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                ((AppCompatActivity) activity).supportInvalidateOptionsMenu();

                ((AppCompatActivity) activity).getSupportActionBar().setTitle(ChannelsContainer.getInstance().getSelectedChannel().getChannelName());
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                ((AppCompatActivity) activity).supportInvalidateOptionsMenu();

                ((AppCompatActivity) activity).getSupportActionBar().setTitle(R.string.app_name);
            }
        };
    }

    private void initializeRecyclerView(RecyclerOnItemClickListener.OnItemClickListener onItemClickListener){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);

        DrawerRecyclerAdapter adapter = new DrawerRecyclerAdapter(drawerItems);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerOnItemClickListener(activity, onItemClickListener));
    }

    public DrawerLayout getDrawerLayout() {

        return drawerLayout;
    }

    public ActionBarDrawerToggle getDrawerToggle() {

        return drawerToggle;
    }

    private RecyclerOnItemClickListener.OnItemClickListener itemClickListener = new RecyclerOnItemClickListener.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {

            DrawerItem drawerItem = drawerItems.get(position);

            if (drawerItem.getType() == DrawerItem.CHANNEL) {

                DrawerChannelItem channelItem = (DrawerChannelItem) drawerItem;
                ChannelItem channel = channelItem.getChannelItem();

                ChannelsContainer.getInstance().setSelectedChannel(channel);

                drawerLayout.closeDrawer(GravityCompat.START);

                updateViewPagerFragments();
            }
        }
    };

    private void updateViewPagerFragments(){

        TabbedContent tabbedContent  = (TabbedContent) ((AppCompatActivity) activity).getSupportFragmentManager().findFragmentByTag(FragmentTags.TABBED_CONTENT_FRAGMENT);

        if(tabbedContent != null){

           tabbedContent.updateTabbedContent();
        }
    }
}

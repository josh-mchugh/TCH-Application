package com.redrumming.thecreaturehub.navigation;

import com.redrumming.thecreaturehub.channel.ChannelItem;
import com.redrumming.thecreaturehub.channel.ChannelsContainer;
import com.redrumming.thecreaturehub.drawer.DrawerChannelItem;
import com.redrumming.thecreaturehub.drawer.DrawerHeaderItem;
import com.redrumming.thecreaturehub.drawer.DrawerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 1/3/2016.
 */
public class NavigationDrawerHelperPresenterImpl implements NavigationDrawerHelperPresenter {

    private NavigationDrawerHelperView view;

    private List<DrawerItem> drawerItems;

    public NavigationDrawerHelperPresenterImpl(NavigationDrawerHelperView view){
        this.view = view;
        this.drawerItems = initDrawerItems();
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

    @Override
    public List<DrawerItem> getDrawerItems() {

        return drawerItems;
    }

    @Override
    public String getTitle() {

        return ChannelsContainer.getInstance().getSelectedChannel().getChannelName();
    }

    @Override
    public void onClick(int position) {

        DrawerItem drawerItem = drawerItems.get(position);

        if (drawerItem.getType() == DrawerItem.CHANNEL) {

            DrawerChannelItem channelItem = (DrawerChannelItem) drawerItem;
            ChannelItem channel = channelItem.getChannelItem();

            ChannelsContainer.getInstance().setSelectedChannel(channel);

            view.onItemClick();
        }
    }
}

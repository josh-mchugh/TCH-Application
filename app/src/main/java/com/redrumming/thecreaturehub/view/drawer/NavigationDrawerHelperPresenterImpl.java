package com.redrumming.thecreaturehub.view.drawer;

import com.redrumming.thecreaturehub.api.youtube.channel.ChannelsContainer;
import com.redrumming.thecreaturehub.api.youtube.channel.model.Channel;
import com.redrumming.thecreaturehub.models.drawer.DrawerChannelItem;
import com.redrumming.thecreaturehub.models.drawer.DrawerHeaderItem;
import com.redrumming.thecreaturehub.models.drawer.DrawerItem;

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

        return ChannelsContainer.getInstance().getSelectedChannel().getSnippet().getTitle();
    }

    @Override
    public void onClick(int position) {

        DrawerItem drawerItem = drawerItems.get(position);

        if (drawerItem.getType() == DrawerItem.CHANNEL) {

            DrawerChannelItem channelItem = (DrawerChannelItem) drawerItem;
            Channel channel = channelItem.getChannelItem();

            ChannelsContainer.getInstance().setSelectedChannel(channel);

            view.onItemClick();
        }
    }
}

package com.redrumming.thecreaturehub.models.channel;

import android.graphics.BitmapFactory;

import com.google.api.services.youtube.model.Channel;
import com.redrumming.thecreaturehub.util.NumberFormatterUtil;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 1/10/2016.
 */
public class ChannelItemFactory {

    public static List<ChannelItem> createChannelItems(List<Channel> channels){

        List<ChannelItem> channelItems = new ArrayList<ChannelItem>();

        for(int i = 0; i < channels.size(); i++){

            ChannelItem channel = new ChannelItem();

            channel.setChannelId(channels.get(i).getId());
            channel.setChannelName(channels.get(i).getSnippet().getTitle());

            String subscriberCount = NumberFormatterUtil.formatSubscriberCount(channels.get(i).getStatistics().getSubscriberCount());
            channel.setSubscriberCount(subscriberCount);

            channel.setDisplayIconURL(channels.get(i).getSnippet().getThumbnails().getMedium().getUrl());

            channelItems.add(channel);
        }

        return channelItems;
    }
}

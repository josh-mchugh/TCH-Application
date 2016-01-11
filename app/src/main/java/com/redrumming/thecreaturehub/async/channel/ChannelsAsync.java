package com.redrumming.thecreaturehub.async.channel;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.redrumming.thecreaturehub.models.channel.ChannelItem;
import com.redrumming.thecreaturehub.models.channel.ChannelItemFactory;
import com.redrumming.thecreaturehub.util.NetworkUtil;
import com.redrumming.thecreaturehub.youtube.YouTubeServiceCalls;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ME on 11/13/2015.
 */
public class ChannelsAsync extends AsyncTask<String[], Void, List<ChannelItem>> {

    private Context context;
    private ChannelsAsyncListener listener;

    public ChannelsAsync(Context context, ChannelsAsyncListener listener) {

        this.context = context;
        this.listener = listener;
    }

    @Override
    protected List<ChannelItem> doInBackground(String[]... params) {

        try{

            if(NetworkUtil.hasConnection(context) == false){

                this.cancel(true);
            }

            List<ChannelItem> channelItems = new ArrayList<ChannelItem>();

            ChannelListResponse channelListResponse = new YouTubeServiceCalls(context).getChannels(Arrays.asList(params[0]));

            channelItems = ChannelItemFactory.createChannelItems(channelListResponse.getItems());

            channelItems = sortList(channelItems, params[0]);

            if(channelItems.size() != params[0].length){

                this.cancel(true);

                return null;
            }

            return channelItems;

        }catch(Exception e){

            Log.e(this.getClass().getCanonicalName(), "Error retrieving YouTube Channels./n" + e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<ChannelItem> channelItems) {
        super.onPostExecute(channelItems);

        if(channelItems != null) {

            logger(channelItems);
            listener.onSuccess(channelItems);

        }else {

            listener.onFailure();
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

        listener.onFailure();
    }

    private List<ChannelItem> sortList(List<ChannelItem> channelItems, String[] channelIds){

        List<ChannelItem> sortedList = new ArrayList<ChannelItem>();

        for(int i = 0; i < channelIds.length; i++){

            for(int j = 0; j < channelItems.size(); j++){

                if(channelIds[i].equals(channelItems.get(j).getChannelId())){

                    sortedList.add(channelItems.get(j));
                }
            }
        }

        return sortedList;
    }

    private void logger(List<ChannelItem> items){

        String className = this.getClass().getName();

        for(int i = 0; i < items.size(); i++){

            Log.d(className, "Channel Name: " + items.get(i).getChannelName());
            Log.d(className, "Channel Id: " + items.get(i).getChannelId());
            Log.d(className, "Channel Subscriber Count: " + items.get(i).getSubscriberCount());
            Log.d(className, "Channel Display Icon URL: " + items.get(i).getDisplayIconURL());
        }
    }
}
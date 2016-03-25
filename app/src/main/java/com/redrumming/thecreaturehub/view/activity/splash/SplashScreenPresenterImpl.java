package com.redrumming.thecreaturehub.view.activity.splash;

import android.util.Log;

import com.redrumming.thecreaturehub.api.youtube.channel.ChannelsParams;
import com.redrumming.thecreaturehub.api.youtube.channel.ChannelsAPI;
import com.redrumming.thecreaturehub.api.youtube.channel.model.Channel;
import com.redrumming.thecreaturehub.api.youtube.channel.model.Channels;
import com.redrumming.thecreaturehub.models.channel.ChannelItem;
import com.redrumming.thecreaturehub.retrofit.YouTubeRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by ME on 1/2/2016.
 */
public class SplashScreenPresenterImpl implements SplashScreenPresenter {

    private SplashScreenView view;
    private Subscription subscription;

    public SplashScreenPresenterImpl(SplashScreenView view){

        this.view = view;
    }

    @Override
    public void fetchChannelData(final String[] channelIds) {

        Retrofit retrofit = YouTubeRetrofit.build();

        ChannelsAPI channelsAPI = retrofit.create(ChannelsAPI.class);

        subscription = channelsAPI.getChannels(ChannelsParams.createParams(channelIds))
                .map(new Func1<Channels, Channels>() {

                    @Override
                    public Channels call(Channels channels) {

                        return sortList(channels, channelIds);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Channels>() {

                    @Override
                    public void onCompleted() {

                        Log.d(getClass().getName(), "DONE`````````````````````````````````````````````!");
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(getClass().getName(), " Error !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", e);
                    }

                    @Override
                    public void onNext(Channels channels) {

                        for(Channel c: channels.getChannels()){

                            Log.d(getClass().getName(), "Channel Name: " + c.getSnippet().getTitle());
                        }

                    }
                });
    }

    @Override
    public void onDestroy() {

        if(subscription != null && !subscription.isUnsubscribed()){

            subscription.unsubscribe();
            subscription = null;
        }
    }

    private final Channels sortList(Channels channels, String[] channelIds){

        Channels sorted = new Channels();

        for(int i = 0; i < channelIds.length; i++){

            for(int j = 0; j < channels.getChannels().size(); j++){

                if(channelIds[i].equals(channels.getChannels().get(j).getId())){

                    sorted.getChannels().add(channels.getChannels().get(j));
                }
            }
        }

        return sorted;
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

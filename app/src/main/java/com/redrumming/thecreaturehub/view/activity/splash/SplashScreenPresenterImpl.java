package com.redrumming.thecreaturehub.view.activity.splash;

import android.util.Log;

import com.redrumming.thecreaturehub.api.youtube.channel.ChannelsContainer;
import com.redrumming.thecreaturehub.api.youtube.channel.ChannelsParams;
import com.redrumming.thecreaturehub.api.youtube.channel.ChannelsAPI;
import com.redrumming.thecreaturehub.api.youtube.channel.model.Channels;
import com.redrumming.thecreaturehub.retrofit.YouTubeRetrofit;

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

                        logger(channels);
                        return sortList(channels, channelIds);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Channels>() {

                    @Override
                    public void onCompleted() {

                        Log.d(getClass().getName(), "Completed attempting to relieving channel information.");
                        view.displayNextActivity();
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(getClass().getName(), "An error has occurred trying to retrieve channel information.", e);
                        view.displayFailureAlert();
                    }

                    @Override
                    public void onNext(Channels channels) {

                        ChannelsContainer.getInstance().setChannels(channels.getChannels());
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

    private void logger(Channels channels){

        String className = this.getClass().getName();

        for(int i = 0; i < channels.getChannels().size(); i++){

            Log.d(className, "Channel Name: " + channels.getChannels().get(i).getSnippet().getTitle());
            Log.d(className, "Channel Id: " + channels.getChannels().get(i).getId());
            Log.d(className, "Channel Subscriber Count: " + channels.getChannels().get(i).getStatistics().getSubscriberCount());
            Log.d(className, "Channel Display Icon URL: " + channels.getChannels().get(i).getSnippet().getThumbnails().getMedium().getUrl());
        }
    }
}

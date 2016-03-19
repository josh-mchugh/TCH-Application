package com.redrumming.thecreaturehub.view.activity.splash;

import android.util.Log;

import com.google.api.services.youtube.model.ChannelListResponse;
import com.redrumming.thecreaturehub.models.channel.ChannelItem;
import com.redrumming.thecreaturehub.models.channel.ChannelItemFactory;
import com.redrumming.thecreaturehub.models.channel.ChannelsContainer;
import com.redrumming.thecreaturehub.youtube.YouTubeServiceCalls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
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

       subscription = Observable.just(channelIds)
               .map(new Func1<String[], List<ChannelItem>>() {

                   @Override
                   public List<ChannelItem> call(String[] strings) {

                       List<ChannelItem> channelItems = new ArrayList<ChannelItem>();

                       try {

                           ChannelListResponse channelListResponse = new YouTubeServiceCalls(view.getContext()).getChannels(Arrays.asList(strings));

                           channelItems = ChannelItemFactory.createChannelItems(channelListResponse.getItems());

                           channelItems = sortList(channelItems, strings);

                           if (channelItems.size() != strings.length) {

                               return null;
                           }

                           logger(channelItems);

                       } catch (Exception e) {

                           Log.e(this.getClass().getName(), "Error getting channel information", e);
                       }

                       return channelItems;
                   }
               })
          .subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Observer<List<ChannelItem>>() {

              @Override
              public void onCompleted() {

                  view.displayNextActivity();
              }

              @Override
              public void onError(Throwable e) {

                  Log.e(this.getClass().getName(), "Error getting channel information.", e);

                  view.displayFailureAlert();
              }

              @Override
              public void onNext(List<ChannelItem> channelItems) {

                  if(channelItems == null){

                      onError(new Throwable("Returned channel items were null."));
                  }

                  ChannelsContainer.getInstance().setChannels(channelItems);
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

    private final List<ChannelItem> sortList(List<ChannelItem> channelItems, String[] channelIds){

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

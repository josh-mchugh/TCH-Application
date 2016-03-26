package com.redrumming.thecreaturehub.view.fragments.content;

import android.os.Bundle;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.api.youtube.channel.ChannelsContainer;
import com.redrumming.thecreaturehub.api.youtube.channel.model.Channel;
import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.models.content.ContentType;
import com.redrumming.thecreaturehub.models.content.loading.LoadingItem;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ME on 1/8/2016.
 */
public abstract class ContentFragmentPresenterImpl implements ContentFragmentPresenter {

    private ContentFragmentView view;

    private boolean isLoading = false;

    private Subscription subscription;

    public ContentFragmentPresenterImpl(ContentFragmentView view){

        this.view = view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        if(savedInstanceState != null){

            setContainer(savedInstanceState.getParcelable("container"));
        }

        if(getContainer().getItems().size() == 0 &&
                (getContainer().getPageToken() == null || getContainer().getPageToken().isEmpty())) {

            setup(ChannelsContainer.getInstance().getSelectedChannel());
        }
    }

    @Override
    public void onDestroy() {

        if(subscription != null && !subscription.isUnsubscribed()){

            subscription.isUnsubscribed();
            subscription = null;
        }
    }

    private void removeLoadingStatus(){

        if(isLoading == true){

            view.disableSwipeRefreshLoading();

            if(getContainer().getItems().size() > 0){

                int lastItem = (getContainer().getItems().size() - 1);
                ContentType contentType = getContainer().getItems().get(lastItem);

                if(contentType.getItemType() == ContentType.LOADING_ITEM){

                    getContainer().getItems().remove(lastItem);
                    view.notifyAdapterChange();
                }
            }

            isLoading = false;
        }
    }

    @Override
    public void setup(Channel channel){

        getContainer().setChannel(channel);
        getContainer().setPageToken("");
        getContainer().getItems().clear();

        getContent();

        setLoading();
    }

    @Override
    public void onLoadMore(){

        if(getContainer().getPageToken() != null) {

            if (isLoading == false) {

                getContent();

                setLoading();
            }
        }
    }

    private void getContent(){

        subscription = Observable.just(getContainer())
                .map(onCall(getContainer()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private void setLoading(){

        getContainer().getItems().add(new LoadingItem());
        view.notifyAdapterChange();
        isLoading = true;
    }

    public void updateView(ContentContainer contentContainer){

        removeLoadingStatus();

        getContainer().getItems().addAll(contentContainer.getItems());
        getContainer().setPageToken(contentContainer.getPageToken());
        view.notifyAdapterChange();
    }

    @Override
    public void onRefresh(){

        if(isLoading == false){

            getContainer().setPageToken("");
            getContainer().getItems().clear();
            view.notifyAdapterChange();

            getContent();

            isLoading = true;
        }
    }

    public abstract void onSelect(int position);
    public abstract void setContainer(Parcelable container);
    public abstract ContentContainer getContainer();
    public abstract Observer<ContentContainer> getObserver();
    public abstract Func1<ContentContainer, ContentContainer> onCall(ContentContainer contentContainer);

    public ContentFragmentView getView(){

        return view;
    }
}

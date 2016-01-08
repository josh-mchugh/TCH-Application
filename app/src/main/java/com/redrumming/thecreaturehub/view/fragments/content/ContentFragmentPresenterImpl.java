package com.redrumming.thecreaturehub.view.fragments.content;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.async.content.ContentAsync;
import com.redrumming.thecreaturehub.async.content.ContentAsyncListener;
import com.redrumming.thecreaturehub.models.channel.ChannelItem;
import com.redrumming.thecreaturehub.models.channel.ChannelsContainer;
import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.models.content.ContentType;
import com.redrumming.thecreaturehub.models.content.loading.LoadingItem;

/**
 * Created by ME on 1/8/2016.
 */
public abstract class ContentFragmentPresenterImpl implements ContentFragmentPresenter, ContentAsyncListener {

    private ContentFragmentView view;

    private ContentAsync async;
    private boolean isLoading = false;

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

    private void checkAsyncStatus(){

        if(async != null){

            if(async.getStatus() == AsyncTask.Status.RUNNING){

                async.cancel(true);
            }

            async = null;
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
    public void setup(ChannelItem channelItem){

        getContainer().setChannelItem(channelItem);
        getContainer().setPageToken("");
        getContainer().getItems().clear();

        executeAsync();

        setLoading();
    }

    @Override
    public void onLoadMore(){

        if(getContainer().getPageToken() != null) {

            if (isLoading == false) {

                executeAsync();

                setLoading();
            }
        }
    }

    private void executeAsync(){

        checkAsyncStatus();
        this.async = getAsync();
        this.async.execute(getContainer());
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

            executeAsync();

            isLoading = true;
        }
    }

    public abstract void onSelect(int position);
    public abstract void setContainer(Parcelable container);
    public abstract ContentContainer getContainer();
    public abstract ContentAsync getAsync();

    public ContentFragmentView getView(){

        return view;
    }

    @Override
    public void onSuccess(ContentContainer container) {

        if(container.getChannelItem() == null && container.getItems() != null){

            return;
        }

        updateView(container);
    }

    @Override
    public void onFailure() {

        view.displayErrorMsg();

        removeLoadingStatus();
    }
}

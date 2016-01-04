package com.redrumming.thecreaturehub.view.fragments.content;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.async.content.ContentAsync;
import com.redrumming.thecreaturehub.async.content.ContentAsyncListener;
import com.redrumming.thecreaturehub.view.viewholders.content.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.models.channel.ChannelsContainer;
import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.models.content.ContentType;
import com.redrumming.thecreaturehub.models.content.loading.LoadingItem;
import com.redrumming.thecreaturehub.util.RecyclerOnItemClickListener;
import com.redrumming.thecreaturehub.models.channel.ChannelItem;
import com.redrumming.thecreaturehub.util.EndlessScrollListener;

/**
 * Created by ME on 10/24/2015.
 */
public abstract class ContentFragment extends Fragment implements ContentAsyncListener {

    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;

    private EndlessScrollListener scrollListener;

    private ContentAsync async;

    private boolean isLoading = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content, viewGroup, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.content_swift_refresh);
        swipeRefresh.setColorSchemeResources(R.color.ColorPrimary, R.color.ColorPrimaryDark);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                ContentFragment.this.onRefresh(getAsync());
            }
        });


        scrollListener = new EndlessScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore() {

                ContentFragment.this.onLoadMore(getAsync());
            }
        };

        recyclerView = (RecyclerView) view.findViewById(R.id.content_recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(getAdapter());
        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.addOnItemTouchListener(new RecyclerOnItemClickListener(getActivity(), onItemClickListener));

        if(getContainer().getItems().size() == 0 &&
                (getContainer().getPageToken() == null || getContainer().getPageToken().isEmpty())) {

            setup(ChannelsContainer.getInstance().getSelectedChannel(), getAsync());
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("layoutManagerPosition", recyclerView.getLayoutManager().onSaveInstanceState());
    }

    public abstract void onSelect(int position);
    public abstract ContentContainer getContainer();
    public abstract ContentAsync getAsync();
    public abstract ContentRecyclerAdapter getAdapter();

    public void setup(ChannelItem channelItem, ContentAsync contentAsync){

        getContainer().setChannelItem(channelItem);
        getContainer().setPageToken("");
        getContainer().getItems().clear();

        checkAsyncStatus();
        executeAsync(contentAsync);

        setLoading();
    }

    private void onLoadMore(ContentAsync contentAsync){

        if(getContainer().getPageToken() != null) {

            if (isLoading == false) {

                checkAsyncStatus();
                executeAsync(contentAsync);

                setLoading();
            }
        }
    }

    private void onRefresh(ContentAsync contentAsync){

        if(isLoading == false){

            getContainer().setPageToken("");
            getContainer().getItems().clear();
            getAdapter().notifyDataSetChanged();

            checkAsyncStatus();
            executeAsync(contentAsync);

            isLoading = true;
        }
    }

    public void updateView(ContentContainer contentContainer){

        removeLoadingStatus();

        getContainer().getItems().addAll(contentContainer.getItems());
        getContainer().setPageToken(contentContainer.getPageToken());
        getAdapter().notifyDataSetChanged();
    }

    private void checkAsyncStatus(){

        if(async != null){

            if(async.getStatus() == AsyncTask.Status.RUNNING){

                async.cancel(true);
            }

            async = null;
        }
    }

    private void executeAsync(ContentAsync contentAsync){

        async = contentAsync;
        async.execute(getContainer());
    }

    private void setLoading(){

        getContainer().getItems().add(new LoadingItem());
        getAdapter().notifyDataSetChanged();
        isLoading = true;
    }

    private void removeLoadingStatus(){

        if(isLoading == true){

            if(swipeRefresh != null && swipeRefresh.isRefreshing()){

                swipeRefresh.setRefreshing(false);
            }

            if(getContainer().getItems().size() > 0){

                int lastItem = (getContainer().getItems().size() - 1);
                ContentType contentType = getContainer().getItems().get(lastItem);

                if(contentType.getItemType() == ContentType.LOADING_ITEM){

                    getContainer().getItems().remove(lastItem);
                    getAdapter().notifyDataSetChanged();
                }
            }

            isLoading = false;
        }
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

        Toast.makeText(getActivity(), "Unable retrieve data. Try again.", Toast.LENGTH_SHORT).show();

        removeLoadingStatus();
    }

    private RecyclerOnItemClickListener.OnItemClickListener onItemClickListener = new RecyclerOnItemClickListener.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {

            onSelect(position);
        }
    };
}
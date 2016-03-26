package com.redrumming.thecreaturehub.view.fragments.content;

import android.content.Context;
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
import com.redrumming.thecreaturehub.api.youtube.channel.model.Channel;
import com.redrumming.thecreaturehub.view.viewholders.content.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.util.RecyclerOnItemClickListener;
import com.redrumming.thecreaturehub.util.EndlessScrollListener;

/**
 * Created by ME on 10/24/2015.
 */
public abstract class ContentFragment extends Fragment implements ContentFragmentView {

    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;
    private EndlessScrollListener scrollListener;

    private ContentFragmentPresenter presenter;

    public abstract ContentRecyclerAdapter getAdapter();
    public abstract ContentFragmentPresenter getPresenter();

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

                presenter.onRefresh();
            }
        });


        scrollListener = new EndlessScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore() {

                presenter.onLoadMore();
            }
        };

        recyclerView = (RecyclerView) view.findViewById(R.id.content_recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(getAdapter());
        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.addOnItemTouchListener(new RecyclerOnItemClickListener(getActivity(), onItemClickListener));

        presenter = getPresenter();
        presenter.onCreate(savedInstanceState);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }

    public void setup(Channel channel){

        presenter.setup(channel);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("layoutManagerPosition", recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void disableSwipeRefreshLoading() {

        if(swipeRefresh != null && swipeRefresh.isRefreshing()){

            swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void notifyAdapterChange() {

        getAdapter().notifyDataSetChanged();
    }

    @Override
    public Context getContext(){

        return getActivity();
    }

    @Override
    public void displayErrorMsg() {

        Toast.makeText(getActivity(), "Unable retrieve data. Try again.", Toast.LENGTH_SHORT).show();
    }

    private RecyclerOnItemClickListener.OnItemClickListener onItemClickListener = new RecyclerOnItemClickListener.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {

            presenter.onSelect(position);
        }
    };
}
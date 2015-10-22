package com.redrumming.thecreaturehub.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ME on 8/7/2015.
 */
public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private int visibleThreshold = 5;
    private int previousTotalItemCount = 0;
    private boolean loading = true;
    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;

    private LinearLayoutManager linearLayoutManager;

    public EndlessScrollListener(LinearLayoutManager linearLayoutManager){
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = linearLayoutManager.getItemCount();
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

        //Assumming the list has been reset if total item count is 1 or less.
        //we use one becuase the loading item should be present.
        if(totalItemCount <= 1){

            previousTotalItemCount = 0;
        }

        if(loading){

            if(totalItemCount > previousTotalItemCount){

                loading = false;
                previousTotalItemCount = totalItemCount;
            }
        }

        if(!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)){

            onLoadMore();
            loading = true;
        }
    }

    public abstract void onLoadMore();
}

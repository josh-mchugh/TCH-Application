package com.redrumming.thecreaturehub.view.viewholders.content;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.view.viewholders.content.loading.LoadingViewHolder;
import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.models.content.ContentType;

/**
 * Created by ME on 10/24/2015.
 */
public class ContentRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ContentContainer container;
    private Context context;

    public ContentRecyclerAdapter(ContentContainer container){

        this.container = container;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == ContentType.LOADING_ITEM){

            View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);

            context = parent.getContext();
            LoadingViewHolder viewHolder = new LoadingViewHolder(view);

            return viewHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {

        return container.getItems().get(position).getItemType();
    }

    @Override
    public int getItemCount() {

        return container.getItems().size();
    }

    public ContentContainer getContainer() {

        return container;
    }

    public void setContext(Context context) {

        this.context = context;
    }

    public Context getContext() {

        return context;
    }
}
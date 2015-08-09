package com.redrumming.thecreaturehub.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.redrumming.thecreaturehub.R;

import java.util.List;

/**
 * Created by ME on 7/22/2015.
 */
public class DrawerRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements DrawerItemOnClickListener {

    private List<DrawerItem> items;

    public DrawerRecyclerAdapter(List<DrawerItem> items){
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == DrawerItem.HEADER){

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.drawer_header_item, parent, false);

            DrawerHeaderViewHolder viewHolder = new DrawerHeaderViewHolder(view, this);

            return viewHolder;

        }else if(viewType == DrawerItem.CHANNEL){

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.drawer_channel_item, parent, false);

            DrawerChannelViewHolder viewHolder = new DrawerChannelViewHolder(view, this);

            return viewHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        DrawerItem item = items.get(position);

        if(item.getType() == DrawerItem.HEADER){

            DrawerHeaderItem headerItem = (DrawerHeaderItem) item;
            DrawerHeaderViewHolder viewHolder = (DrawerHeaderViewHolder) holder;

            viewHolder.getTitle().setText(headerItem.getTitle());

        }else if(item.getType() == DrawerItem.CHANNEL){

            DrawerChannelItem channelItem = (DrawerChannelItem) item;
            DrawerChannelViewHolder viewHolder = (DrawerChannelViewHolder) holder;

            viewHolder.getTitle().setText(channelItem.getChannel().getChannelName());
            viewHolder.getDisplayIcon().setImageDrawable(channelItem.getChannel().getDisplayIcon());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public void testToast(String message, View view) {
        //Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}

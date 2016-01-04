package com.redrumming.thecreaturehub.view.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.models.drawer.DrawerChannelItem;
import com.redrumming.thecreaturehub.models.drawer.DrawerHeaderItem;
import com.redrumming.thecreaturehub.models.drawer.DrawerItem;

import java.util.List;

/**
 * Created by ME on 7/22/2015.
 */
public class DrawerRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<DrawerItem> items;

    public DrawerRecyclerAdapter(List<DrawerItem> items){
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == DrawerItem.HEADER){

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_drawer_header, parent, false);

            DrawerHeaderViewHolder viewHolder = new DrawerHeaderViewHolder(view);

            return viewHolder;

        }else if(viewType == DrawerItem.CHANNEL){

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_drawer_channel, parent, false);

            DrawerChannelViewHolder viewHolder = new DrawerChannelViewHolder(view);

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

            viewHolder.getTitle().setText(channelItem.getChannelItem().getChannelName());
            viewHolder.getDisplayIcon().setImageBitmap(channelItem.getChannelItem().getDisplayIcon());
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
}

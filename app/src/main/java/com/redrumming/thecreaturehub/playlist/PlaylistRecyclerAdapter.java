package com.redrumming.thecreaturehub.playlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.ContentItem;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.Channel;
import com.redrumming.thecreaturehub.util.ImageLoaderUtil;
import com.redrumming.thecreaturehub.util.TimePassedUtil;

/**
 * Created by ME on 8/6/2015.
 */
public class PlaylistRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private PlaylistContainer container;
    private Context context;

    public PlaylistRecyclerAdapter(PlaylistContainer container){

        this.container = container;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == PlaylistWrapper.PLAYLIST_ITEM){

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.playlist_item, parent, false);

            context = parent.getContext();
            PlaylistViewHolder viewHolder = new PlaylistViewHolder(view);

            return viewHolder;

        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ContentItem item = container.getPlaylistWrappers().get(position);

        if(item.getItemType() == ContentItem.PLAYLIST_ITEM){

            PlaylistWrapper playlistWrapper = (PlaylistWrapper) item;
            Channel channel = container.getChannel();
            PlaylistViewHolder viewHolder = (PlaylistViewHolder) holder;

            ImageLoaderUtil.get(context).displayImage(playlistWrapper.getThumbnailURL(), viewHolder.getThumbnail());
            viewHolder.getTitle().setText(playlistWrapper.getTitle());
            viewHolder.getDisplayIcon().setImageDrawable(channel.getDisplayIcon());
            viewHolder.getChannelName().setText(channel.getChannelName());
            viewHolder.getPublishedTime().setText(new TimePassedUtil().getTimeDifference(playlistWrapper.getPublishedDate()));
        }

    }

    @Override
    public int getItemCount() {
        return container.getPlaylistWrappers().size();
    }

    @Override
    public int getItemViewType(int position) {
        return container.getPlaylistWrappers().get(position).getItemType();
    }
}

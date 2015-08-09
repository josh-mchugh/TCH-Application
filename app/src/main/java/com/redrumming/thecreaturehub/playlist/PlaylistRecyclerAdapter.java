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

        if(viewType == Playlist.PLAYLIST_ITEM){

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

        ContentItem item = container.getPlaylists().get(position);

        if(item.getItemType() == ContentItem.PLAYLIST_ITEM){

            Playlist playlist = (Playlist) item;
            Channel channel = container.getChannel();
            PlaylistViewHolder viewHolder = (PlaylistViewHolder) holder;

            ImageLoaderUtil.get(context).displayImage(playlist.getThumbnailURL(), viewHolder.getThumbnail());
            viewHolder.getTitle().setText(playlist.getTitle());
            viewHolder.getDisplayIcon().setImageDrawable(channel.getDisplayIcon());
            viewHolder.getChannelName().setText(channel.getChannelName());
            viewHolder.getPublishedTime().setText(new TimePassedUtil().getTimeDifference(playlist.getPublishedDate()));
        }

    }

    @Override
    public int getItemCount() {
        return container.getPlaylists().size();
    }

    @Override
    public int getItemViewType(int position) {
        return container.getPlaylists().get(position).getItemType();
    }
}

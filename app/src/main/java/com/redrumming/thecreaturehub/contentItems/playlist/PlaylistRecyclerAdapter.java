package com.redrumming.thecreaturehub.contentItems.playlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.contentItems.ContentItem;
import com.redrumming.thecreaturehub.contentItems.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.Channel;
import com.redrumming.thecreaturehub.util.ImageLoaderUtil;
import com.redrumming.thecreaturehub.util.TimePassedUtil;

/**
 * Created by ME on 8/6/2015.
 */
public class PlaylistRecyclerAdapter extends ContentRecyclerAdapter{

    public PlaylistRecyclerAdapter(PlaylistContainer container){
        super(container);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == ContentItem.PLAYLIST_ITEM){

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.playlist_item, parent, false);

            super.setContext(parent.getContext());
            PlaylistViewHolder viewHolder = new PlaylistViewHolder(view);

            return viewHolder;

        }

        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ContentItem item = super.getContainer().getItems().get(position);

        if(item.getItemType() == ContentItem.PLAYLIST_ITEM){

            PlaylistItem playlistItem = (PlaylistItem) item;
            Channel channel = super.getContainer().getChannel();
            PlaylistViewHolder viewHolder = (PlaylistViewHolder) holder;

            ImageLoaderUtil.get(super.getContext()).displayImage(playlistItem.getThumbnailURL(), viewHolder.getThumbnail());
            viewHolder.getTitle().setText(playlistItem.getTitle());
            viewHolder.getChannelIcon().setImageDrawable(channel.getDisplayIcon());
            viewHolder.getChannelName().setText(channel.getChannelName());
            viewHolder.getPublishDate().setText(new TimePassedUtil().getTimeDifference(playlistItem.getPublishedDate()));
        }
    }
}

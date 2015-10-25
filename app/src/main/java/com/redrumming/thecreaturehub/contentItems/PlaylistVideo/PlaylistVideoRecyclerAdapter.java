package com.redrumming.thecreaturehub.contentItems.PlaylistVideo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.Channel;
import com.redrumming.thecreaturehub.contentItems.ContentItem;
import com.redrumming.thecreaturehub.contentItems.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.util.ImageLoaderUtil;
import com.redrumming.thecreaturehub.util.TimePassedUtil;

/**
 * Created by ME on 10/18/2015.
 */
public class PlaylistVideoRecyclerAdapter extends ContentRecyclerAdapter{

    public PlaylistVideoRecyclerAdapter(PlaylistVideoContainer container){
        super(container);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        if(viewType == ContentItem.PLAYLIST_VIDEO_ITEM){

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.playlist_video_item, parent, false);
            
            super.setContext(parent.getContext());
            PlaylistVideoViewHolder viewHolder = new PlaylistVideoViewHolder(view);
            
            return viewHolder;
            
        }
        
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ContentItem contentItem = super.getContainer().getItems().get(position);

        if(contentItem.getItemType() == ContentItem.PLAYLIST_VIDEO_ITEM){

            PlaylistVideoItem video = (PlaylistVideoItem) contentItem;
            Channel channel = super.getContainer().getChannel();
            PlaylistVideoViewHolder viewHolder = (PlaylistVideoViewHolder) holder;

            ImageLoaderUtil.get(super.getContext()).displayImage(video.getThumbnailURL(), viewHolder.getThumbnail());
            viewHolder.getTitle().setText(video.getVideoTitle());
            viewHolder.getChannelIcon().setImageDrawable(channel.getDisplayIcon());
            viewHolder.getChannelName().setText(channel.getChannelName());
            viewHolder.getPublishDate().setText(new TimePassedUtil().getTimeDifference(video.getPublishedDate()));
        }
    }
}

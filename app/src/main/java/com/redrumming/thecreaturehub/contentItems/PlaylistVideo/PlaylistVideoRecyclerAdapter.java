package com.redrumming.thecreaturehub.contentItems.PlaylistVideo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.Channel;
import com.redrumming.thecreaturehub.contentItems.ContentItem;
import com.redrumming.thecreaturehub.contentItems.LoadingViewHolder;
import com.redrumming.thecreaturehub.util.ImageLoaderUtil;
import com.redrumming.thecreaturehub.util.TimePassedUtil;

/**
 * Created by ME on 10/18/2015.
 */
public class PlaylistVideoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private PlaylistVideoContainer container;
    private Context context;

    public PlaylistVideoRecyclerAdapter(PlaylistVideoContainer container){

        this.container = container;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        if(viewType == ContentItem.PLAYLIST_VIDEO_ITEM){

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.playlist_video_item, parent, false);
            
            context = parent.getContext();
            PlaylistVideoViewHolder viewHolder = new PlaylistVideoViewHolder(view);
            
            return viewHolder;
            
        }else if(viewType == ContentItem.LOADING_ITEM){
            
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.loading_item, parent, false);

            LoadingViewHolder viewHolder = new LoadingViewHolder(view);

            return viewHolder;
        }
        
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ContentItem contentItem = container.getVideoWrappers().get(position);

        if(contentItem.getItemType() == ContentItem.PLAYLIST_VIDEO_ITEM){

            PlaylistVideoWrapper video = (PlaylistVideoWrapper) contentItem;
            Channel channel = container.getChannel();
            PlaylistVideoViewHolder viewHolder = (PlaylistVideoViewHolder) holder;

            ImageLoaderUtil.get(context).displayImage(video.getThumbnailURL(), viewHolder.getThumbnail());
            viewHolder.getTitle().setText(video.getVideoTitle());
            viewHolder.getChannelIcon().setImageDrawable(channel.getDisplayIcon());
            viewHolder.getChannelName().setText(channel.getChannelName());
            viewHolder.getPublishDate().setText(new TimePassedUtil().getTimeDifference(video.getPublishedDate()));
        }

    }

    @Override
    public int getItemCount() {

        return container.getVideoWrappers().size();
    }

    @Override
    public int getItemViewType(int position) {

        return container.getVideoWrappers().get(position).getItemType();
    }
}

package com.redrumming.thecreaturehub.contentItems.video;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.contentItems.ContentItem;
import com.redrumming.thecreaturehub.contentItems.LoadingViewHolder;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.Channel;
import com.redrumming.thecreaturehub.util.ImageLoaderUtil;
import com.redrumming.thecreaturehub.util.TimePassedUtil;

/**
 * Created by ME on 8/4/2015.
 */
public class VideoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private VideoContainer container;
    private Context context;

    public VideoRecyclerAdapter(VideoContainer container){

        this.container = container;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == ContentItem.VIDEO_ITEM){

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.video_item, parent, false);

            context = parent.getContext();
            VideoViewHolder viewHolder = new VideoViewHolder(view);

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

        ContentItem item = container.getVideoWrappers().get(position);

        if(item.getItemType() == ContentItem.VIDEO_ITEM){

            VideoWrapper videoWrapper = (VideoWrapper) item;
            Channel channel = container.getChannel();
            VideoViewHolder viewHolder = (VideoViewHolder) holder;

            ImageLoaderUtil.get(context).displayImage(videoWrapper.getThumbnailURL(), viewHolder.getVideoThumbnail());
            viewHolder.getVideoTitle().setText(videoWrapper.getVideoTitle());
            viewHolder.getChannelIcon().setImageDrawable(channel.getDisplayIcon());
            viewHolder.getChannelName().setText(channel.getChannelName());
            viewHolder.getPushishedDate().setText(new TimePassedUtil().getTimeDifference(videoWrapper.getPublishedDate()));
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

package com.redrumming.thecreaturehub.contentItems.video;

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
 * Created by ME on 8/4/2015.
 */
public class VideoRecyclerAdapter extends ContentRecyclerAdapter{


    public VideoRecyclerAdapter(VideoContainer container){

        super(container);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == ContentItem.VIDEO_ITEM){

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.video_item, parent, false);

            super.setContext(parent.getContext());
            VideoViewHolder viewHolder = new VideoViewHolder(view);

            return viewHolder;
        }

        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ContentItem item = super.getContainer().getItems().get(position);

        if(item.getItemType() == ContentItem.VIDEO_ITEM){

            VideoItem videoItem = (VideoItem) item;
            Channel channel = super.getContainer().getChannel();
            VideoViewHolder viewHolder = (VideoViewHolder) holder;

            ImageLoaderUtil.get(super.getContext()).displayImage(videoItem.getThumbnailURL(), viewHolder.getThumbnail());
            viewHolder.getTitle().setText(videoItem.getVideoTitle());
            viewHolder.getChannelIcon().setImageDrawable(channel.getDisplayIcon());
            viewHolder.getChannelName().setText(channel.getChannelName());
            viewHolder.getPublishDate().setText(new TimePassedUtil().getTimeDifference(videoItem.getPublishedDate()));
        }
    }
}

package com.redrumming.thecreaturehub.contentItems.video;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.contentItems.ContentType;
import com.redrumming.thecreaturehub.contentItems.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.ChannelItem;
import com.redrumming.thecreaturehub.util.NumberFormatterUtil;
import com.redrumming.thecreaturehub.util.TimePassedUtil;
import com.squareup.picasso.Picasso;

/**
 * Created by ME on 8/4/2015.
 */
public class VideoRecyclerAdapter extends ContentRecyclerAdapter{


    public VideoRecyclerAdapter(VideoContainer container){

        super(container);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == ContentType.VIDEO_ITEM){

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_video, parent, false);

            super.setContext(parent.getContext());
            VideoViewHolder viewHolder = new VideoViewHolder(view);

            return viewHolder;
        }

        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ContentType item = super.getContainer().getItems().get(position);

        if(item.getItemType() == ContentType.VIDEO_ITEM){

            VideoItem videoItem = (VideoItem) item;
            ChannelItem channelItem = super.getContainer().getChannelItem();
            VideoViewHolder viewHolder = (VideoViewHolder) holder;

            Picasso.with(super.getContext()).load(videoItem.getThumbnailURL()).into(viewHolder.getThumbnail());
            viewHolder.getTitle().setText(videoItem.getTitle());
            viewHolder.getChannelIcon().setImageBitmap(channelItem.getDisplayIcon());
            viewHolder.getPublishDate().setText(TimePassedUtil.getTimeDifference(videoItem.getPublishedAt()));
            viewHolder.getViewCountSpacer().setText(" \u00B7 ");
            viewHolder.getViewCount().setText(new NumberFormatterUtil().formatShortView(videoItem.getViewCount()));
        }
    }
}

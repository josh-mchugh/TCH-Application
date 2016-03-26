package com.redrumming.thecreaturehub.view.viewholders.content.video;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.api.youtube.channel.model.Channel;
import com.redrumming.thecreaturehub.models.content.ContentType;
import com.redrumming.thecreaturehub.view.viewholders.content.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.models.content.video.VideoContainer;
import com.redrumming.thecreaturehub.models.content.video.VideoItem;
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
            Channel channel = super.getContainer().getChannel();
            VideoViewHolder viewHolder = (VideoViewHolder) holder;

            Picasso.with(super.getContext()).load(videoItem.getThumbnailURL()).into(viewHolder.getThumbnail());
            viewHolder.getTitle().setText(videoItem.getTitle());

            Picasso.with(super.getContext())
                    .load(channel.getSnippet().getThumbnails().getMedium().getUrl())
                    .error(R.drawable.display_user_profile_image_default)
                    .noFade()
                    .into(viewHolder.getChannelIcon());

            viewHolder.getPublishDate().setText(TimePassedUtil.getTimeDifference(videoItem.getPublishedAt()));
            viewHolder.getViewCountSpacer().setText(" \u00B7 ");
            viewHolder.getViewCount().setText(videoItem.getViewCount());
        }
    }
}

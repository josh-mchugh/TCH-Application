package com.redrumming.thecreaturehub.contentItems.video;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 8/4/2015.
 */
public class VideoViewHolder extends RecyclerView.ViewHolder{

    private ImageView videoThumbnail;
    private TextView videoTitle;
    private ImageView channelIcon;
    private TextView channelName;
    private TextView pushishedDate;

    public VideoViewHolder(View itemView){
        super(itemView);

        videoThumbnail = (ImageView) itemView.findViewById(R.id.video_item_thumbnail);
        videoTitle = (TextView) itemView.findViewById(R.id.video_item_title);
        channelIcon = (ImageView) itemView.findViewById(R.id.video_item_channel_icon);
        channelName = (TextView) itemView.findViewById(R.id.video_item_channel_name);
        pushishedDate = (TextView) itemView.findViewById(R.id.video_item_pushished_date);
    }

    public ImageView getVideoThumbnail() {
        return videoThumbnail;
    }

    public TextView getVideoTitle() {
        return videoTitle;
    }

    public ImageView getChannelIcon() {
        return channelIcon;
    }

    public TextView getChannelName() {
        return channelName;
    }

    public TextView getPushishedDate() {
        return pushishedDate;
    }
}

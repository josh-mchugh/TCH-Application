package com.redrumming.thecreaturehub.contentItems.video;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.redrumming.thecreaturehub.contentItems.ContentViewHolder;

/**
 * Created by ME on 8/4/2015.
 */
public class VideoViewHolder extends ContentViewHolder{


    public VideoViewHolder(View itemView){
        super(itemView);
    }

    @Override
    public ImageView getThumbnail() {
        return super.getThumbnail();
    }

    @Override
    public ImageView getChannelIcon() {
        return super.getChannelIcon();
    }

    @Override
    public TextView getTitle() {
        return super.getTitle();
    }

    @Override
    public TextView getChannelName() {
        return super.getChannelName();
    }

    @Override
    public TextView getPublishDate() {
        return super.getPublishDate();
    }
}

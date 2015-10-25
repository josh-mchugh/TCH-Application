package com.redrumming.thecreaturehub.contentItems.PlaylistVideo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.redrumming.thecreaturehub.contentItems.ContentViewHolder;

/**
 * Created by ME on 10/18/2015.
 */
public class PlaylistVideoViewHolder extends ContentViewHolder {

    public PlaylistVideoViewHolder(View itemView){
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

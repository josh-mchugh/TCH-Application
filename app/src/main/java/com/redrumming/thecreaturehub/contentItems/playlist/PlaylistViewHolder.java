package com.redrumming.thecreaturehub.contentItems.playlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 8/6/2015.
 */
public class PlaylistViewHolder extends RecyclerView.ViewHolder{

    private ImageView thumbnail;
    private TextView title;
    private ImageView displayIcon;
    private TextView channelName;
    private TextView publishedTime;

    public PlaylistViewHolder(View viewItem){
        super(viewItem);

        thumbnail = (ImageView) viewItem.findViewById(R.id.playlist_item_thumbnail);
        title = (TextView) viewItem.findViewById(R.id.playlist_item_title);
        displayIcon = (ImageView) viewItem.findViewById(R.id.playlist_item_display_icon);
        channelName = (TextView) viewItem.findViewById(R.id.playlist_item_channe_name);
        publishedTime = (TextView) viewItem.findViewById(R.id.playlist_item_publish_time);
    }

    public ImageView getThumbnail() {
        return thumbnail;
    }

    public TextView getTitle() {
        return title;
    }

    public ImageView getDisplayIcon() {
        return displayIcon;
    }

    public TextView getChannelName() {
        return channelName;
    }

    public TextView getPublishedTime() {
        return publishedTime;
    }
}

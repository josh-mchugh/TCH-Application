package com.redrumming.thecreaturehub.contentItems.PlaylistVideo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 10/18/2015.
 */
public class PlaylistVideoViewHolder extends RecyclerView.ViewHolder {

    private ImageView thumbnail;
    private TextView title;
    private ImageView channelIcon;
    private TextView channelName;
    private TextView publishDate;

    public PlaylistVideoViewHolder(View itemView){
        super(itemView);

        thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        title = (TextView) itemView.findViewById(R.id.title);
        channelIcon = (ImageView) itemView.findViewById(R.id.channel_icon);
        channelName = (TextView) itemView.findViewById(R.id.channel_name);
        publishDate = (TextView) itemView.findViewById(R.id.publish_date);
    }

    public ImageView getThumbnail() {
        return thumbnail;
    }

    public TextView getTitle() {
        return title;
    }

    public ImageView getChannelIcon() {
        return channelIcon;
    }

    public TextView getChannelName() {
        return channelName;
    }

    public TextView getPublishDate() {
        return publishDate;
    }
}

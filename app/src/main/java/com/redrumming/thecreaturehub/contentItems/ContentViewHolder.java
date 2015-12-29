package com.redrumming.thecreaturehub.contentItems;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 10/25/2015.
 */
public class ContentViewHolder extends RecyclerView.ViewHolder {

    private ImageView thumbnail;
    private ImageView channelIcon;

    private TextView title;
    private TextView publishDate;

    public ContentViewHolder(View itemView) {
        super(itemView);

        thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        channelIcon = (ImageView) itemView.findViewById(R.id.channel_icon);

        title = (TextView) itemView.findViewById(R.id.title);
        publishDate = (TextView) itemView.findViewById(R.id.publish_date);
    }

    public ImageView getThumbnail() {

        return thumbnail;
    }

    public ImageView getChannelIcon() {

        return channelIcon;
    }

    public TextView getTitle() {

        return title;
    }

    public TextView getPublishDate() {

        return publishDate;
    }
}

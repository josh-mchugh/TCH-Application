package com.redrumming.thecreaturehub.view.viewholders.detail.channel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 12/5/2015.
 */
public class ChannelSectionViewHolder extends RecyclerView.ViewHolder {

    private ImageView channelIcon;
    private TextView channelName;
    private TextView subscriberCount;

    public ChannelSectionViewHolder(View itemView) {
        super(itemView);

        channelIcon = (ImageView) itemView.findViewById(R.id.channel_icon);
        channelName = (TextView) itemView.findViewById(R.id.channel_name);
        subscriberCount = (TextView) itemView.findViewById(R.id.subscriber_count);
    }

    public ImageView getChannelIcon() {

        return channelIcon;
    }

    public TextView getChannelName() {

        return channelName;
    }

    public TextView getSubscriberCount() {

        return subscriberCount;
    }
}

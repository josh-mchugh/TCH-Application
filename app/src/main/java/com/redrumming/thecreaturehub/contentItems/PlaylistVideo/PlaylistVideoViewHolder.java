package com.redrumming.thecreaturehub.contentItems.PlaylistVideo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.contentItems.ContentViewHolder;

/**
 * Created by ME on 10/18/2015.
 */
public class PlaylistVideoViewHolder extends ContentViewHolder {

    private TextView viewCount;
    private TextView viewCountSpacer;

    public PlaylistVideoViewHolder(View itemView){
        super(itemView);

        viewCount = (TextView) itemView.findViewById(R.id.view_count);
        viewCountSpacer = (TextView) itemView.findViewById(R.id.view_count_spacer);
    }

    public TextView getViewCount() {
        return viewCount;
    }

    public TextView getViewCountSpacer() {
        return viewCountSpacer;
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
    public TextView getPublishDate() {
        return super.getPublishDate();
    }
}

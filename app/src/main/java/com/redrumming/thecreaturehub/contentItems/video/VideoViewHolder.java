package com.redrumming.thecreaturehub.contentItems.video;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.contentItems.ContentViewHolder;

import org.w3c.dom.Text;

/**
 * Created by ME on 8/4/2015.
 */
public class VideoViewHolder extends ContentViewHolder{

    private TextView viewCount;
    private TextView viewCountSpacer;

    public VideoViewHolder(View itemView){
        super(itemView);

        viewCount = (TextView) itemView.findViewById(R.id.view_count);
        viewCountSpacer = (TextView) itemView.findViewById(R.id.view_count_spacer);
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

    public TextView getViewCount() {
        return viewCount;
    }

    public TextView getViewCountSpacer() {
        return viewCountSpacer;
    }
}

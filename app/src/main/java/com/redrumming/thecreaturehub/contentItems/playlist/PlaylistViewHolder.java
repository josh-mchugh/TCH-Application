package com.redrumming.thecreaturehub.contentItems.playlist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.contentItems.ContentViewHolder;

import org.w3c.dom.Text;

/**
 * Created by ME on 8/6/2015.
 */
public class PlaylistViewHolder extends ContentViewHolder{

    private TextView numberOfVideos;

    public PlaylistViewHolder(View viewItem){
        super(viewItem);

        numberOfVideos = (TextView) viewItem.findViewById(R.id.number_of_videos);
    }

    public TextView getNumberOfVideos() {
        return numberOfVideos;
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

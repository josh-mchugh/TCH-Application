package com.redrumming.thecreaturehub.view.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 7/26/2015.
 */
public class DrawerChannelViewHolder extends RecyclerView.ViewHolder{

    private TextView title;
    private ImageView displayIcon;

    public DrawerChannelViewHolder(View itemView){
        super(itemView);

        title = (TextView)itemView.findViewById(R.id.channel_display_name);
        displayIcon = (ImageView)itemView.findViewById(R.id.channel_display_icon);
    }

    public TextView getTitle() {
        return title;
    }

    public ImageView getDisplayIcon() {
        return displayIcon;
    }
}

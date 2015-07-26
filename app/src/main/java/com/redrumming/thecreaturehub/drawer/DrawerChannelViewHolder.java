package com.redrumming.thecreaturehub.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 7/26/2015.
 */
public class DrawerChannelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView title;
    private ImageView displayIcon;
    private DrawerItemOnClickListener listener;

    public DrawerChannelViewHolder(View itemView, DrawerItemOnClickListener listener){
        super(itemView);

        this.listener = listener;

        title = (TextView)itemView.findViewById(R.id.channel_display_name);
        displayIcon = (ImageView)itemView.findViewById(R.id.channel_display_icon);

        itemView.setOnClickListener(this);
    }

    public TextView getTitle() {
        return title;
    }

    public ImageView getDisplayIcon() {
        return displayIcon;
    }

    @Override
    public void onClick(View v) {

        listener.testToast("Channel: " + title.getText().toString(), v);
    }
}

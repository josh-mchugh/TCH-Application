package com.redrumming.thecreaturehub.view.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 7/26/2015.
 */
public class DrawerHeaderViewHolder extends RecyclerView.ViewHolder{

    private TextView title;

    public DrawerHeaderViewHolder(View itemView){
        super(itemView);

        title = (TextView)itemView.findViewById(R.id.drawer_header_title);
    }

    public TextView getTitle() {
        return title;
    }
}

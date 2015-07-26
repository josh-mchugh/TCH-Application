package com.redrumming.thecreaturehub.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 7/26/2015.
 */
public class DrawerHeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView title;
    private DrawerItemOnClickListener listener;

    public DrawerHeaderViewHolder(View itemView, DrawerItemOnClickListener listener){
        super(itemView);

        this.listener = listener;

        title = (TextView)itemView.findViewById(R.id.drawer_header_title);

        itemView.setOnClickListener(this);
    }

    public TextView getTitle() {
        return title;
    }

    @Override
    public void onClick(View v) {

        listener.testToast("Header: " + title.getText().toString(), v);
    }
}

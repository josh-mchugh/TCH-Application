package com.redrumming.thecreaturehub.player.comments.topComments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 12/18/2015.
 */
public class NoCommentsViewHolder extends RecyclerView.ViewHolder {

    private TextView title;

    public NoCommentsViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.no_comments_text);
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }
}

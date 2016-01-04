package com.redrumming.thecreaturehub.view.viewholders.detail.comments.header;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 12/18/2015.
 */
public class CommentsHeaderViewHolder extends RecyclerView.ViewHolder {

    private TextView title;

    public CommentsHeaderViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.comment_header_title);
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }
}

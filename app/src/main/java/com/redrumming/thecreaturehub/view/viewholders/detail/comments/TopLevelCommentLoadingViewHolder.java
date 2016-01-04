package com.redrumming.thecreaturehub.view.viewholders.detail.comments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 12/28/2015.
 */
public class TopLevelCommentLoadingViewHolder extends RecyclerView.ViewHolder {

    private ProgressBar loadingProgressBar;

    public TopLevelCommentLoadingViewHolder(View itemView) {
        super(itemView);

        loadingProgressBar = (ProgressBar) itemView.findViewById(R.id.top_comments_progress_bar);
    }
}

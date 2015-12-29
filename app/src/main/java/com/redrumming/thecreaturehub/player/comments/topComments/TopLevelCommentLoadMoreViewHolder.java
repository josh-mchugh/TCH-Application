package com.redrumming.thecreaturehub.player.comments.topComments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 12/7/2015.
 */
public class TopLevelCommentLoadMoreViewHolder extends RecyclerView.ViewHolder {

    private RelativeLayout loadMoreWrapper;
    private TextView loadMoreText;
    private ProgressBar loadMoreProgressBar;

    public TopLevelCommentLoadMoreViewHolder(View itemView) {
        super(itemView);

        loadMoreWrapper = (RelativeLayout) itemView.findViewById(R.id.top_comments_load_more_wrapper);

        loadMoreText = (TextView) itemView.findViewById(R.id.top_comments_load_more_text);
        loadMoreProgressBar = (ProgressBar) itemView.findViewById(R.id.top_comments_load_more_progress_bar);
    }

    public RelativeLayout getLoadMoreWrapper() {

        return loadMoreWrapper;
    }

    public TextView getLoadMoreText() {

        return loadMoreText;
    }

    public ProgressBar getLoadMoreProgressBar() {

        return loadMoreProgressBar;
    }
}

package com.redrumming.thecreaturehub.view.viewholders.detail.comments.top;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 12/6/2015.
 */
public class TopLevelCommentViewHolder extends RecyclerView.ViewHolder {

    private ImageView profileImage;
    private TextView profileName;
    private TextView comment;
    private TextView publishedAt;

    private RelativeLayout loadMoreRepliesContainer;
    private TextView loadMoreRepliesText;
    private ImageView loadMoreRepliesArrowImage;
    private ProgressBar loadMoreRepliesProgressBar;

    private LinearLayout repliesContainer;

    public TopLevelCommentViewHolder(View itemView) {
        super(itemView);

        profileImage = (ImageView) itemView.findViewById(R.id.comment_user_profile_image);
        profileName = (TextView) itemView.findViewById(R.id.comment_user_display_name);
        comment = (TextView) itemView.findViewById(R.id.comment_display_comment);
        publishedAt = (TextView) itemView.findViewById(R.id.comment_published_date);

        loadMoreRepliesContainer = (RelativeLayout) itemView.findViewById(R.id.comment_load_more_replies);
        loadMoreRepliesText = (TextView) itemView.findViewById(R.id.comment_load_more_replies_text);
        loadMoreRepliesArrowImage = (ImageView) itemView.findViewById(R.id.comment_load_more_replies_arrow);
         loadMoreRepliesProgressBar = (ProgressBar) itemView.findViewById(R.id.comment_load_more_replies_progress_bar);

        repliesContainer = (LinearLayout) itemView.findViewById(R.id.reply_comments_container);
    }

    public ImageView getProfileImage() {

        return profileImage;
    }

    public TextView getProfileName() {

        return profileName;
    }

    public TextView getComment() {

        return comment;
    }

    public TextView getPublishedAt() {

        return publishedAt;
    }

    public RelativeLayout getLoadMoreRepliesContainer() {

        return loadMoreRepliesContainer;
    }

    public TextView getLoadMoreRepliesText() {

        return loadMoreRepliesText;
    }

    public ImageView getLoadMoreRepliesArrowImage() {
        return loadMoreRepliesArrowImage;
    }

    public ProgressBar getLoadMoreRepliesProgressBar() {

        return loadMoreRepliesProgressBar;
    }

    public LinearLayout getRepliesContainer() {

        return repliesContainer;
    }
}
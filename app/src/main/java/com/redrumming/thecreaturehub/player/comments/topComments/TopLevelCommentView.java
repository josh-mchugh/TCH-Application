package com.redrumming.thecreaturehub.player.comments.topComments;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.player.comments.CommentAsyncListener;
import com.redrumming.thecreaturehub.player.comments.CommentContainer;
import com.redrumming.thecreaturehub.player.comments.CommentItemType;
import com.redrumming.thecreaturehub.player.comments.replies.ReplyCommentAsync;
import com.redrumming.thecreaturehub.player.comments.replies.ReplyCommentContainer;
import com.redrumming.thecreaturehub.player.comments.replies.ReplyCommentView;
import com.redrumming.thecreaturehub.player.comments.replies.ReplyCommentItem;
import com.redrumming.thecreaturehub.util.TimePassedUtil;
import com.squareup.picasso.Picasso;

/**
 * Created by ME on 11/10/2015.
 */
public class TopLevelCommentView extends FrameLayout implements CommentAsyncListener{

    private ImageView profileImage;
    private TextView profileName;
    private TextView comment;
    private TextView publishedAt;

    private RelativeLayout loadMoreRepliesContainer;
    private TextView loadMoreRepliesText;
    private ProgressBar loadMoreRepliesProgressBar;

    private LinearLayout replyCommentsContainer;

    private TopLevelCommentItem topLevelCommentItem;
    private CommentAsyncListener listener;

    public TopLevelCommentView(Context context, TopLevelCommentItem topLevelCommentItem) {
        super(context);
        this.topLevelCommentItem = topLevelCommentItem;
        this.listener = this;
        init();
    }

    private void init(){

        inflate(getContext(), R.layout.item_top_level_comment, this);


        initLayoutVariables();
        initLayoutValues();
    }

    private void initLayoutVariables(){

        profileImage = (ImageView) findViewById(R.id.comment_user_profile_image);
        profileName = (TextView) findViewById(R.id.comment_user_display_name);
        comment = (TextView) findViewById(R.id.comment_display_comment);
        publishedAt = (TextView) findViewById(R.id.comment_published_date);
        replyCommentsContainer = (LinearLayout) findViewById(R.id.reply_comments_container);

        loadMoreRepliesContainer = (RelativeLayout) findViewById(R.id.comment_load_more_replies);
        loadMoreRepliesText = (TextView) findViewById(R.id.comment_load_more_replies_text);
        loadMoreRepliesProgressBar = (ProgressBar) findViewById(R.id.comment_load_more_replies_progress_bar);
    }

    private void initLayoutValues() {

        Picasso.with(super.getContext()).load(topLevelCommentItem.getProfileImageURL()).error(R.drawable.display_user_profile_image_default).noFade().into(profileImage);
        profileName.setText(topLevelCommentItem.getDisplayName());
        comment.setText(topLevelCommentItem.getTextDisplay());
        publishedAt.setText(TimePassedUtil.getTimeDifference(topLevelCommentItem.getPublishedAt()));

        if(topLevelCommentItem.hasReplies()){

            if(topLevelCommentItem.getTotalReplyCount() > topLevelCommentItem.getReplies().getCommentItems().size()) {

                loadMoreRepliesContainer.setVisibility(View.VISIBLE);
                loadMoreRepliesContainer.setOnClickListener(loadMoreRepliesClickListener);

                replyCommentsContainer.setVisibility(View.VISIBLE);
            }

            for(CommentItemType commentItemType : topLevelCommentItem.getReplies().getCommentItems()){

                if(commentItemType.getItemType() == CommentItemType.REPLY_COMMENT){

                    ReplyCommentItem replyCommentItem = (ReplyCommentItem) commentItemType;

                    replyCommentsContainer.addView(new ReplyCommentView(getContext(), replyCommentItem), 0);
                }
            }
        }
    }

    private void updateView(){

        for(int i = replyCommentsContainer.getChildCount(); i < topLevelCommentItem.getReplies().getCommentItems().size(); i++){

            CommentItemType comment = topLevelCommentItem.getReplies().getCommentItems().get(i);

            if(comment.getItemType() == CommentItemType.REPLY_COMMENT){

                ReplyCommentItem replyCommentItem = (ReplyCommentItem) comment;

                replyCommentsContainer.addView(new ReplyCommentView(getContext(), replyCommentItem), 0);
            }
        }

        if(topLevelCommentItem.getReplies().getPageToken() != null){

            loadMoreRepliesProgressBar.setVisibility(View.INVISIBLE);
            loadMoreRepliesText.setVisibility(View.VISIBLE);

        }else{

            loadMoreRepliesContainer.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onSuccess(CommentContainer container) {

        if(container.getCommentItems().size() > 0){

            ReplyCommentContainer replyCommentContainer = (ReplyCommentContainer) container;

            topLevelCommentItem.getReplies().getCommentItems().addAll(replyCommentContainer.getCommentItems());
            topLevelCommentItem.getReplies().setPageToken(replyCommentContainer.getPageToken());

            updateView();
        }
    }

    private OnClickListener loadMoreRepliesClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {

            if(topLevelCommentItem.getReplies().getPageToken() != null) {

                loadMoreRepliesText.setVisibility(View.GONE);
                loadMoreRepliesProgressBar.setVisibility(View.VISIBLE);

                ReplyCommentAsync async = new ReplyCommentAsync(getContext(), listener);
                async.execute(topLevelCommentItem.getReplies());

            }
        }
    };
}
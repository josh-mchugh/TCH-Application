package com.redrumming.thecreaturehub.view.viewholders.detail.comments.reply;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.models.detail.comments.reply.ReplyCommentItem;
import com.redrumming.thecreaturehub.util.TimePassedUtil;
import com.squareup.picasso.Picasso;

/**
 * Created by ME on 11/9/2015.
 */
public class ReplyCommentView extends RelativeLayout {

    private ReplyCommentItem replyComment;

    private ImageView profileImage;
    private TextView profileName;
    private TextView comment;
    private TextView publishedAt;

    public ReplyCommentView(Context context, ReplyCommentItem replyComment) {
        super(context);
        this.replyComment = replyComment;
        init();
    }

    private void init(){

        super.inflate(getContext(), R.layout.item_reply_comment, this);

        initLayoutVariables();
        initLayoutValues();
    }

    private void initLayoutVariables(){

        profileImage = (ImageView) findViewById(R.id.comment_user_profile_image);
        profileName = (TextView) findViewById(R.id.comment_user_display_name);
        comment = (TextView) findViewById(R.id.comment_display_comment);
        publishedAt = (TextView) findViewById(R.id.comment_published_date);
    }

    private void initLayoutValues(){

        Picasso.with(super.getContext()).load(replyComment.getProfileImageURL()).error(R.drawable.display_user_profile_image_default).noFade().into(profileImage);
        profileName.setText(replyComment.getDisplayName());
        comment.setText(replyComment.getTextDisplay());
        publishedAt.setText(TimePassedUtil.getTimeDifference(replyComment.getPublishedAt()));
    }
}

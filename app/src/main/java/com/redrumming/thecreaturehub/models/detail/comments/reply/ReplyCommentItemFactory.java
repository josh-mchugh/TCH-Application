package com.redrumming.thecreaturehub.models.detail.comments.reply;

import com.google.api.services.youtube.model.Comment;
import com.google.api.services.youtube.model.CommentListResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 1/11/2016.
 */
public class ReplyCommentItemFactory {

    public static List<ReplyCommentItem> createReplyCommentItems(CommentListResponse commentListResponse){

        List<ReplyCommentItem> replyCommentItems = new ArrayList<ReplyCommentItem>();
        List<Comment> comments = commentListResponse.getItems();

        for(int i = 0; i < comments.size(); i++){

            ReplyCommentItem replyCommentItem = new ReplyCommentItem();

            replyCommentItem.setId(comments.get(i).getId());
            replyCommentItem.setParentId(comments.get(i).getSnippet().getParentId());
            replyCommentItem.setDisplayName(comments.get(i).getSnippet().getAuthorDisplayName());
            replyCommentItem.setProfileImageURL(comments.get(i).getSnippet().getAuthorProfileImageUrl());
            replyCommentItem.setTextDisplay(comments.get(i).getSnippet().getTextDisplay());
            replyCommentItem.setChannelId(comments.get(i).getSnippet().getChannelId());
            replyCommentItem.setPublishedAt(comments.get(i).getSnippet().getPublishedAt().getValue());

            replyCommentItems.add(replyCommentItem);
        }

        return replyCommentItems;
    }
}

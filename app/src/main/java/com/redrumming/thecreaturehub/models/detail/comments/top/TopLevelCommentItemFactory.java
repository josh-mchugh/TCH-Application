package com.redrumming.thecreaturehub.models.detail.comments.top;

import com.google.api.services.youtube.model.Comment;
import com.google.api.services.youtube.model.CommentListResponse;
import com.google.api.services.youtube.model.CommentThread;
import com.redrumming.thecreaturehub.models.detail.comments.reply.ReplyCommentContainer;
import com.redrumming.thecreaturehub.models.detail.comments.reply.ReplyCommentItem;
import com.redrumming.thecreaturehub.youtube.YouTubeServiceCalls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 1/11/2016.
 */
public class TopLevelCommentItemFactory {

    public static List<TopLevelCommentItem> createTopLevelItems(List<CommentThread> videoComments) {

        List<TopLevelCommentItem> topLevelCommentItems = new ArrayList<TopLevelCommentItem>();

        //Loop throw the parent level comments and transfer the data to our models.
        for(int i = 0; i < videoComments.size(); i++){

            TopLevelCommentItem topLevelCommentItem = new TopLevelCommentItem();

            topLevelCommentItem.setId(videoComments.get(i).getSnippet().getTopLevelComment().getId());
            topLevelCommentItem.setChannelId(videoComments.get(i).getSnippet().getTopLevelComment().getSnippet().getAuthorChannelId().getValue());
            topLevelCommentItem.setDisplayName(videoComments.get(i).getSnippet().getTopLevelComment().getSnippet().getAuthorDisplayName());
            topLevelCommentItem.setProfileImageURL(videoComments.get(i).getSnippet().getTopLevelComment().getSnippet().getAuthorProfileImageUrl());
            topLevelCommentItem.setTextDisplay(videoComments.get(i).getSnippet().getTopLevelComment().getSnippet().getTextDisplay());
            topLevelCommentItem.setPublishedAt(videoComments.get(i).getSnippet().getTopLevelComment().getSnippet().getPublishedAt().getValue());
            topLevelCommentItem.setTotalReplyCount(videoComments.get(i).getSnippet().getTotalReplyCount());

            topLevelCommentItems.add(topLevelCommentItem);
        }

        return topLevelCommentItems;
    }


}

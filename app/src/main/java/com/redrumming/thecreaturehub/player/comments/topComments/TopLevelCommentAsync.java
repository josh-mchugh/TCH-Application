package com.redrumming.thecreaturehub.player.comments.topComments;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.model.Comment;
import com.google.api.services.youtube.model.CommentListResponse;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import com.redrumming.thecreaturehub.player.comments.replies.ReplyCommentContainer;
import com.redrumming.thecreaturehub.player.comments.CommentAsyncListener;
import com.redrumming.thecreaturehub.player.comments.CommentContainer;
import com.redrumming.thecreaturehub.player.comments.CommentItemType;
import com.redrumming.thecreaturehub.player.comments.replies.ReplyCommentItem;
import com.redrumming.thecreaturehub.youtube.YouTubeServiceCalls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ME on 10/28/2015.
 */
public class TopLevelCommentAsync extends AsyncTask<CommentContainer, Void, CommentContainer> {

    private Context context;
    private CommentAsyncListener listener;

    private CommentContainer commentContainer;

    public TopLevelCommentAsync(Context context, CommentAsyncListener listener){

        this.context = context;
        this.listener = listener;
    }

    @Override
    protected CommentContainer doInBackground(CommentContainer... params) {

        //Get the video ID from the parameter.
        CommentContainer tempContainer = params[0];

        //Create our return object.
        commentContainer = new CommentContainer(tempContainer.getVideoId());
        commentContainer.setPageToken(tempContainer.getPageToken());

        try {

            CommentThreadListResponse commentThreadListResponse = new YouTubeServiceCalls(context).getTopLevelComments(commentContainer.getVideoId(), commentContainer.getPageToken());

            List<TopLevelCommentItem> topLevelCommentItems = createTopLevelComments(commentThreadListResponse.getItems());

            commentContainer.getCommentItems().addAll(topLevelCommentItems);

            //set the page token for parent comments.
            commentContainer.setPageToken(commentThreadListResponse.getNextPageToken());

        }catch (GoogleJsonResponseException e){

            //Throw the error for displaying disabled comments

        }catch (Exception e){

            Log.e(this.getClass().getCanonicalName(), "Error retreiving comments: \n" + e);
        }

        return commentContainer;
    }

    @Override
    protected void onPostExecute(CommentContainer container) {
        super.onPostExecute(container);

        logger(container);
        listener.onSuccess(container);
    }

    private List<TopLevelCommentItem> createTopLevelComments(List<CommentThread> videoComments) throws Exception{

        List<TopLevelCommentItem> topLevelCommentItems = new ArrayList<TopLevelCommentItem>();

        //Loop throw the parent level comments and transfer the data to our models.
        for(CommentThread thread : videoComments){

            TopLevelCommentItem topLevelCommentItem = new TopLevelCommentItem();
            topLevelCommentItem.setId(thread.getSnippet().getTopLevelComment().getId());
            topLevelCommentItem.setChannelId(thread.getSnippet().getTopLevelComment().getSnippet().getAuthorChannelId().getValue());
            topLevelCommentItem.setDisplayName(thread.getSnippet().getTopLevelComment().getSnippet().getAuthorDisplayName());
            topLevelCommentItem.setProfileImageURL(thread.getSnippet().getTopLevelComment().getSnippet().getAuthorProfileImageUrl());
            topLevelCommentItem.setTextDisplay(thread.getSnippet().getTopLevelComment().getSnippet().getTextDisplay());
            topLevelCommentItem.setPublishedAt(thread.getSnippet().getTopLevelComment().getSnippet().getPublishedAt().getValue());
            topLevelCommentItem.setTotalReplyCount(thread.getSnippet().getTotalReplyCount());

            //If the parent comment has child comments, call the YouTube server for that data
            //and add them to a container within the comment item.
            if(topLevelCommentItem.getTotalReplyCount() > 0){

                topLevelCommentItem.setReplies(getReplies(topLevelCommentItem.getId()));
            }

            topLevelCommentItems.add(topLevelCommentItem);
        }

        return topLevelCommentItems;
    }

    private ReplyCommentContainer getReplies(String parentId) throws Exception {

        CommentListResponse commentListResponse = new YouTubeServiceCalls(context).getReplyComments(parentId, "");

        List<Comment> comments = commentListResponse.getItems();

        ReplyCommentContainer container = new ReplyCommentContainer(commentContainer.getVideoId());

        for(int i = 0; i < comments.size(); i++){

            ReplyCommentItem replyCommentItem = new ReplyCommentItem();

            replyCommentItem.setId(comments.get(i).getId());
            replyCommentItem.setParentId(parentId);
            replyCommentItem.setDisplayName(comments.get(i).getSnippet().getAuthorDisplayName());
            replyCommentItem.setProfileImageURL(comments.get(i).getSnippet().getAuthorProfileImageUrl());
            replyCommentItem.setTextDisplay(comments.get(i).getSnippet().getTextDisplay());
            replyCommentItem.setChannelId(comments.get(i).getSnippet().getChannelId());
            replyCommentItem.setPublishedAt(comments.get(i).getSnippet().getPublishedAt().getValue());

            container.getCommentItems().add(replyCommentItem);
        }

        container.setPageToken(commentListResponse.getNextPageToken());
        container.setParentId(parentId);

        return container;
    }

    //Logs the the values of the returned results within our models.
    private void logger(CommentContainer container){

        String className = this.getClass().getName();

        List<CommentItemType> parentComments =  container.getCommentItems();

        for(CommentItemType parentCommentItem : parentComments){

            if(parentCommentItem.getItemType() == CommentItemType.TOP_LEVEL_COMMENT) {

                TopLevelCommentItem parentComment = (TopLevelCommentItem) parentCommentItem;

                Log.d(className, "Parent Comment Id: " + parentComment.getId());
                Log.d(className, "Parent Display Name: " + parentComment.getDisplayName());
                Log.d(className, "Parent ChannelItem Id: " + parentComment.getChannelId());
                Log.d(className, "Parent Profile Image URL: " + parentComment.getProfileImageURL());
                Log.d(className, "Parent Comment Text: " + parentComment.getTextDisplay());
                Log.d(className, "Parent Published At: " + new Date(parentComment.getPublishedAt()));
                Log.d(className, "Parent children count: " + parentComment.getTotalReplyCount());

                if(parentComment.hasReplies()){

                    for(CommentItemType childCommentItem : parentComment.getReplies().getCommentItems()){

                        if(childCommentItem.getItemType() == CommentItemType.REPLY_COMMENT) {

                            ReplyCommentItem childComment = (ReplyCommentItem) childCommentItem;

                            Log.d(className, "     Child Comment Id: " + childComment.getId());
                            Log.d(className, "     Child Parent Id: " + childComment.getParentId());
                            Log.d(className, "     Child Display Name: " + childComment.getDisplayName());
                            Log.d(className, "     Child ChannelItem Id: " + childComment.getChannelId());
                            Log.d(className, "     Child Profile Image URL: " + childComment.getProfileImageURL());
                            Log.d(className, "     Child Comment Text: " + childComment.getTextDisplay());
                            Log.d(className, "     Child Published At: " + new Date(childComment.getPublishedAt()));
                        }
                    }

                    Log.d(className, "     Child Comment Next Page Token: " + parentComment.getReplies().getPageToken());
                }
            }
        }

        Log.d(className, "Parent Comments Next Page Token: " + container.getPageToken());
    }
}

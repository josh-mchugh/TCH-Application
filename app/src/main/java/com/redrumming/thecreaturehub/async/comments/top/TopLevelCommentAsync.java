package com.redrumming.thecreaturehub.async.comments.top;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.model.CommentListResponse;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import com.redrumming.thecreaturehub.models.detail.comments.CommentItemType;
import com.redrumming.thecreaturehub.models.detail.comments.reply.ReplyCommentItemFactory;
import com.redrumming.thecreaturehub.models.detail.comments.top.TopLevelCommentItem;
import com.redrumming.thecreaturehub.models.detail.comments.reply.ReplyCommentContainer;
import com.redrumming.thecreaturehub.async.comments.CommentAsyncListener;
import com.redrumming.thecreaturehub.models.detail.comments.CommentContainer;
import com.redrumming.thecreaturehub.models.detail.comments.reply.ReplyCommentItem;
import com.redrumming.thecreaturehub.models.detail.comments.top.TopLevelCommentItemFactory;
import com.redrumming.thecreaturehub.youtube.YouTubeServiceCalls;

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

            List<TopLevelCommentItem> topLevelCommentItems = TopLevelCommentItemFactory.createTopLevelItems(commentThreadListResponse.getItems());

            //Check for Replies
            topLevelCommentItems = getReplies(topLevelCommentItems);

            commentContainer.getCommentItems().addAll(topLevelCommentItems);

            //set the page token for parent comments.
            commentContainer.setPageToken(commentThreadListResponse.getNextPageToken());

        }catch (GoogleJsonResponseException e){

            //Throw the error for displaying disabled comments
            Log.e(this.getClass().getCanonicalName(), "Error retrieving top level comments.", e);

        }catch (Exception e){

            Log.e(this.getClass().getCanonicalName(), "Error retrieving top level comments.", e);
        }

        return commentContainer;
    }

    private List<TopLevelCommentItem> getReplies(List<TopLevelCommentItem> topLevelCommentItems) throws Exception{

        for(int i = 0; i < topLevelCommentItems.size(); i++) {
            //If the parent comment has child comments, call the YouTube server for that data
            //and add them to a container within the comment item.
            if (topLevelCommentItems.get(i).getTotalReplyCount() > 0) {

                CommentListResponse commentListResponse = new YouTubeServiceCalls(context).getReplyComments(topLevelCommentItems.get(i).getId(), "");

                ReplyCommentContainer replyCommentContainer = new ReplyCommentContainer(topLevelCommentItems.get(i).getId());
                replyCommentContainer.getCommentItems().addAll(ReplyCommentItemFactory.createReplyCommentItems(commentListResponse));

                topLevelCommentItems.get(i).setReplies(replyCommentContainer);
            }
        }

        return topLevelCommentItems;
    }

    @Override
    protected void onPostExecute(CommentContainer container) {
        super.onPostExecute(container);

        logger(container);
        listener.onSuccess(container);
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

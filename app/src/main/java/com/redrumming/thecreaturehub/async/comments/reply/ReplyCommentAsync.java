package com.redrumming.thecreaturehub.async.comments.reply;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.services.youtube.model.CommentListResponse;
import com.redrumming.thecreaturehub.models.detail.comments.CommentItemType;
import com.redrumming.thecreaturehub.models.detail.comments.reply.ReplyCommentContainer;
import com.redrumming.thecreaturehub.models.detail.comments.reply.ReplyCommentItem;
import com.redrumming.thecreaturehub.async.comments.CommentAsyncListener;
import com.redrumming.thecreaturehub.models.detail.comments.reply.ReplyCommentItemFactory;
import com.redrumming.thecreaturehub.youtube.YouTubeServiceCalls;

import java.util.Date;
import java.util.List;

/**
 * Created by ME on 11/11/2015.
 */
public class ReplyCommentAsync extends AsyncTask<ReplyCommentContainer, Void, ReplyCommentContainer> {

    private Context context;
    private CommentAsyncListener listener;

    public ReplyCommentAsync(Context context, CommentAsyncListener listener) {

        this.context = context;
        this.listener = listener;
    }

    @Override
    protected ReplyCommentContainer doInBackground(ReplyCommentContainer... params) {

        ReplyCommentContainer tempContainer = params[0];

        ReplyCommentContainer replyContainer = new ReplyCommentContainer(tempContainer.getVideoId());
        replyContainer.setParentId(tempContainer.getParentId());
        replyContainer.setPageToken(tempContainer.getPageToken());

        try{

            CommentListResponse commentListResponse = new YouTubeServiceCalls(context).getReplyComments(replyContainer.getParentId(), replyContainer.getPageToken());

            replyContainer.getCommentItems().addAll(ReplyCommentItemFactory.createReplyCommentItems(commentListResponse));
            replyContainer.setPageToken(commentListResponse.getNextPageToken());

        }catch(Exception e){

            String className = this.getClass().getName();
            Log.d(className, "Error retrieving More Reply Comments.");
        }

        return replyContainer;
    }

    @Override
    protected void onPostExecute(ReplyCommentContainer container) {
        super.onPostExecute(container);

        logger(container);
        listener.onSuccess(container);
    }

    private void logger(ReplyCommentContainer container){

        String className = this.getClass().getName();

        List<CommentItemType> commentItemTypes = container.getCommentItems();

        for(CommentItemType comment : commentItemTypes){

            if(comment.getItemType() == CommentItemType.REPLY_COMMENT){

                ReplyCommentItem replyCommentItem = (ReplyCommentItem) comment;

                Log.d(className, "Reply Comment Id: " + replyCommentItem.getId());
                Log.d(className, "Reply Comment Parent Id: " + replyCommentItem.getParentId());
                Log.d(className, "Reply Comment ChannelItem Id: " + replyCommentItem.getChannelId());
                Log.d(className, "Reply Comment Profile Name: " + replyCommentItem.getDisplayName());
                Log.d(className, "Reply Comment Profile Image URL: " + replyCommentItem.getProfileImageURL());
                Log.d(className, "Reply Comment Comment: " + replyCommentItem.getTextDisplay());
                Log.d(className, "Reply Comment Published At: " + new Date(replyCommentItem.getPublishedAt()));
            }
        }

        Log.d(className,"Reply Container Next Page Token: " + container.getPageToken());
    }
}

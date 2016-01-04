package com.redrumming.thecreaturehub.async.comments;

import com.redrumming.thecreaturehub.models.detail.comments.CommentContainer;

/**
 * Created by ME on 11/5/2015.
 */
public interface CommentAsyncListener {

    void onSuccess(CommentContainer container);
}

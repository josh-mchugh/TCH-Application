package com.redrumming.thecreaturehub.api.youtube.comment.reply;

import com.redrumming.thecreaturehub.api.youtube.comment.reply.model.ReplyComments;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by ME on 3/24/2016.
 */
public interface ReplyCommentsAPI {

    @GET("comments")
    Call<ReplyComments> getReplyComments(@QueryMap Map<String, String> queries);
}

package com.redrumming.thecreaturehub.api.youtube.comment.top;

import com.redrumming.thecreaturehub.api.youtube.comment.top.model.CommentThreads;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by ME on 3/24/2016.
 */
public interface CommentThreadsAPI {

    @GET("commentThreads")
    Call<CommentThreads> getCommentThreads(@QueryMap Map<String, String> queries);
}

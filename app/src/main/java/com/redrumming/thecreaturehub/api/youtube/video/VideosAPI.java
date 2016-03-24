package com.redrumming.thecreaturehub.api.youtube.video;

import com.redrumming.thecreaturehub.api.youtube.video.model.Videos;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by ME on 3/24/2016.
 */
public interface VideosAPI {

    @GET("videos")
    Call<Videos> getVideos(@QueryMap Map<String, String> queries);
}

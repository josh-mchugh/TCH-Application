package com.redrumming.thecreaturehub.api.youtube.channel;

import com.redrumming.thecreaturehub.api.youtube.channel.model.Channel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by ME on 3/23/2016.
 */
public interface ChannelsAPI {

    @GET("channels")
    Call<Channel> channels(@QueryMap Map<String, String> queries);
}

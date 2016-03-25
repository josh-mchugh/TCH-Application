package com.redrumming.thecreaturehub.api.youtube.channel;

import com.redrumming.thecreaturehub.api.youtube.channel.model.Channels;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by ME on 3/23/2016.
 */
public interface ChannelsAPI {

    @GET("channels")
    Observable<Channels> getChannels(@QueryMap Map<String, String> queries);
}

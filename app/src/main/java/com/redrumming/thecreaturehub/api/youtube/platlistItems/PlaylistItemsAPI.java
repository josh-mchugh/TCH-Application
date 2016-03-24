package com.redrumming.thecreaturehub.api.youtube.platlistItems;

import com.redrumming.thecreaturehub.api.youtube.platlistItems.model.PlaylistItems;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by ME on 3/24/2016.
 */
public interface PlaylistItemsAPI {

    @GET("playlistItems")
    Call<PlaylistItems> getPlaylistItems(@QueryMap Map<String, String> queries);
}

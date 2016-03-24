package com.redrumming.thecreaturehub.api.youtube.playlists;

import com.redrumming.thecreaturehub.api.youtube.playlists.model.Playlists;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by ME on 3/24/2016.
 */
public interface PlaylistsAPI {

    @GET("playlists")
    Call<Playlists> getPlaylists(@QueryMap Map<String, String> queries);
}

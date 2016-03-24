package com.redrumming.thecreaturehub.api.youtube.search;

import com.redrumming.thecreaturehub.api.youtube.search.model.Search;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by ME on 3/22/2016.
 */
public interface SearchAPI {

    @GET("search")
    Call<Search> search(@QueryMap Map<String, String> options);
}

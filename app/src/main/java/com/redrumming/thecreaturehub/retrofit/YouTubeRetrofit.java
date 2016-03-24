package com.redrumming.thecreaturehub.retrofit;

import com.redrumming.thecreaturehub.retrofit.utils.LoggingInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by ME on 3/23/2016.
 */
public class YouTubeRetrofit {

    public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";

    public static Retrofit build(){

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        return retrofit;
    }
}

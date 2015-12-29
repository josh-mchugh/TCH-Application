package com.redrumming.thecreaturehub.youtube;

import android.content.Context;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.redrumming.thecreaturehub.R;

import java.io.IOException;

/**
 * Created by ME on 7/30/2015.
 */
public class YouTubeInstance {

    private static YouTubeInstance INSTANCE = null;
    private static Context context = null;

    protected YouTubeInstance(){

    }

    public static YouTubeInstance get(Context context){

        if(INSTANCE == null){

            YouTubeInstance.context = context;
            INSTANCE = new YouTubeInstance();

            return INSTANCE;
        }

        return INSTANCE;
    }

    public YouTube getYouTube(){

        return initializeYouTube();
    }

    private YouTube initializeYouTube(){

        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        YouTube youtube = new YouTube.Builder(httpTransport, jsonFactory, httpRequestInitializer)
                        .setApplicationName(context.getResources().getString(R.string.app_name))
                        .build();

        return youtube;
    }

    private HttpRequestInitializer httpRequestInitializer = new HttpRequestInitializer() {
        @Override
        public void initialize(HttpRequest request) throws IOException {
            //No initialization actions required.
        }
    };
}

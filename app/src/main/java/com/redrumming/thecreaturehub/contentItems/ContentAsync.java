package com.redrumming.thecreaturehub.contentItems;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.services.youtube.YouTube;
import com.redrumming.thecreaturehub.util.YouTubeUtil;

/**
 * Created by ME on 10/24/2015.
 */
public class ContentAsync extends AsyncTask<ContentContainer, Void, ContentContainer>{

    private ContentAsyncListener listener;
    private YouTube youtube;
    private final Long MAX_RESULTS = 20l;

    public ContentAsync(Context context, ContentAsyncListener listener){

        this.listener = listener;
        this.youtube = YouTubeUtil.get(context).getYouTube();
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ContentContainer container) {

        super.onPostExecute(container);

        if(isCancelled() == false){

            listener.onSuccess(container);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {

        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled() {

        super.onCancelled();
    }

    @Override
    protected ContentContainer doInBackground(ContentContainer... container) {

        return null;
    }

    public ContentAsyncListener getListener() {

        return listener;
    }

    public YouTube getYoutube() {

        return youtube;
    }

    public Long getMaxResults() {

        return MAX_RESULTS;
    }
}

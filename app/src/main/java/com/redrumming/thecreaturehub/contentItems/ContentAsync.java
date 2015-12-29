package com.redrumming.thecreaturehub.contentItems;

import android.content.Context;
import android.os.AsyncTask;

import com.redrumming.thecreaturehub.util.NetworkUtil;

/**
 * Created by ME on 10/24/2015.
 */
public class ContentAsync extends AsyncTask<ContentContainer, Void, ContentContainer>{

    private ContentAsyncListener listener;
    private Context context;

    public ContentAsync(Context context, ContentAsyncListener listener){

        this.listener = listener;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ContentContainer container) {

        super.onPostExecute(container);

        if(isCancelled() == false){

            if(container.getItems() != null && container.getItems().size() > 0) {

                listener.onSuccess(container);

            }else{

                listener.onFailure();
            }

        }else{

            listener.onFailure();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {

        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled() {

        super.onCancelled();

        listener.onFailure();
    }

    @Override
    protected ContentContainer doInBackground(ContentContainer... container) {

        return null;
    }

    public void checkNetworkStatus(){

        NetworkUtil networkUtil = new NetworkUtil();

        if(networkUtil.hasConnection(getContext()) == false){

            this.cancel(true);
        }
    }

    public ContentAsyncListener getListener() {

        return listener;
    }

    public Context getContext() {

        return context;
    }
}

package com.redrumming.thecreaturehub.contentItems;

/**
 * Created by ME on 10/24/2015.
 */
public interface ContentAsyncListener {

    void onSuccess(ContentContainer container);
    void onFailure();
}

package com.redrumming.thecreaturehub.async.content;

import com.redrumming.thecreaturehub.models.content.ContentContainer;

/**
 * Created by ME on 10/24/2015.
 */
public interface ContentAsyncListener {

    void onSuccess(ContentContainer container);
    void onFailure();
}

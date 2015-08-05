package com.redrumming.thecreaturehub.video;

/**
 * Created by ME on 8/4/2015.
 */
public interface VideoAsyncListener {

    void onSuccess(VideoContainer container);
    void onFailure();
}

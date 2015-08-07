package com.redrumming.thecreaturehub.playlist;

/**
 * Created by ME on 8/6/2015.
 */
public interface PlaylistAsyncListener {

    void onSuccess(PlaylistContainer container);
    void onFailure();
}

package com.redrumming.thecreaturehub.youtube;

import java.io.Serializable;

/**
 * Created by ME on 12/12/2015.
 */
public interface YouTubePlayerListener {

    void onLoad(int playTime);
    void onNext();
    void onPrevious();
}

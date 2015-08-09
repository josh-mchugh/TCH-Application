package com.redrumming.thecreaturehub;

/**
 * Created by ME on 8/4/2015.
 */
public interface ContentItem {

    int LOADING_ITEM = 0;
    int VIDEO_ITEM = 1;
    int PLAYLIST_ITEM = 2;

    int getItemType();
}

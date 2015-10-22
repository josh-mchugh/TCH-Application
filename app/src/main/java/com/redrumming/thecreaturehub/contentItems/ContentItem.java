package com.redrumming.thecreaturehub.contentItems;

import java.io.Serializable;

/**
 * Created by ME on 8/4/2015.
 */
public interface ContentItem extends Serializable{

    int LOADING_ITEM = 0;
    int VIDEO_ITEM = 1;
    int PLAYLIST_ITEM = 2;
    int PLAYLIST_VIDEO_ITEM = 3;

    int getItemType();
}

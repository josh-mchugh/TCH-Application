package com.redrumming.thecreaturehub.models.detail;

/**
 * Created by ME on 12/5/2015.
 */
public interface DetailItem {

    int DESCRIPTION_ITEM = 0;
    int CHANNEL_SECTION_ITEM = 1;
    int COMMENT_HEADER_ITEM= 2;
    int COMMENT_TOP_LEVEL_ITEM = 3;
    int COMMENT_TOP_LEVEL_LOADING = 4;
    int COMMENT_TOP_LEVEL_LOAD_MORE_ITEM = 5;
    int COMMENT_NO_COMMENTS_FOUND = 6;
    int COMMENT_REPLY_ITEM = 7;

    int getType();
}

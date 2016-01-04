package com.redrumming.thecreaturehub.models.detail.comments;

import com.redrumming.thecreaturehub.models.detail.DetailItem;

/**
 * Created by ME on 12/18/2015.
 */
public class NoCommentsItem implements DetailItem {

    @Override
    public int getType() {

        return DetailItem.COMMENT_NO_COMMENTS_FOUND;
    }
}

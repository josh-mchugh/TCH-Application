package com.redrumming.thecreaturehub.models.detail.comments;

import com.redrumming.thecreaturehub.models.detail.DetailItem;

/**
 * Created by ME on 12/5/2015.
 */
public class CommentsHeaderItem implements DetailItem{


    @Override
    public int getType() {

        return DetailItem.COMMENT_HEADER_ITEM;
    }
}

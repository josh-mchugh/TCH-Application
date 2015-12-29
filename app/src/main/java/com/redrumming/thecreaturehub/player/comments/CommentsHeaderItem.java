package com.redrumming.thecreaturehub.player.comments;

import com.redrumming.thecreaturehub.player.details.DetailItem;

/**
 * Created by ME on 12/5/2015.
 */
public class CommentsHeaderItem implements DetailItem{


    @Override
    public int getType() {

        return DetailItem.COMMENT_HEADER_ITEM;
    }
}

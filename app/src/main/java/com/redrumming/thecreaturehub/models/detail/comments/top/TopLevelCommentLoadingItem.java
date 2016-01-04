package com.redrumming.thecreaturehub.models.detail.comments.top;

import com.redrumming.thecreaturehub.models.detail.DetailItem;

/**
 * Created by ME on 12/28/2015.
 */
public class TopLevelCommentLoadingItem implements DetailItem {

    @Override
    public int getType() {

        return DetailItem.COMMENT_TOP_LEVEL_LOADING;
    }
}

package com.redrumming.thecreaturehub.player.comments.topComments;

import com.redrumming.thecreaturehub.player.details.DetailItem;

/**
 * Created by ME on 12/28/2015.
 */
public class TopLevelCommentLoadingItem implements DetailItem {

    @Override
    public int getType() {

        return DetailItem.COMMENT_TOP_LEVEL_LOADING;
    }
}

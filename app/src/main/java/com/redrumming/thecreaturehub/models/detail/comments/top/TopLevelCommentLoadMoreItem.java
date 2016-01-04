package com.redrumming.thecreaturehub.models.detail.comments.top;

import android.app.Fragment;

import com.redrumming.thecreaturehub.models.detail.DetailItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopLevelCommentLoadMoreItem implements DetailItem{

    @Override
    public int getType() {

        return DetailItem.COMMENT_TOP_LEVEL_LOAD_MORE_ITEM;
    }
}

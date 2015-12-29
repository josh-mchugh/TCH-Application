package com.redrumming.thecreaturehub.player.comments.topComments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.player.details.DetailItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopLevelCommentLoadMoreItem implements DetailItem{

    @Override
    public int getType() {

        return DetailItem.COMMENT_TOP_LEVEL_LOAD_MORE_ITEM;
    }
}

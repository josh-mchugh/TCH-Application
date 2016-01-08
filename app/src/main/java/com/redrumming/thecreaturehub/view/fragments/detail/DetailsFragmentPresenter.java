package com.redrumming.thecreaturehub.view.fragments.detail;

import android.os.Bundle;

import com.redrumming.thecreaturehub.models.channel.ChannelItem;
import com.redrumming.thecreaturehub.models.content.video.VideoItem;
import com.redrumming.thecreaturehub.models.detail.DetailItem;
import com.redrumming.thecreaturehub.models.detail.comments.CommentContainer;
import com.redrumming.thecreaturehub.view.viewholders.detail.DetailRecyclerAdapterListener;

import java.util.List;

/**
 * Created by ME on 1/8/2016.
 */
public interface DetailsFragmentPresenter {

    void onCreate(Bundle savedInstanceState, Bundle arguments);
    void onCreateView();

    VideoItem getVideoItem();
    ChannelItem getChannelItem();
    CommentContainer getCommentContainer();
    List<DetailItem> getItems();

    DetailRecyclerAdapterListener getDetailRecyclerAdapterListener();
}

package com.redrumming.thecreaturehub.models.detail.description;

import com.redrumming.thecreaturehub.models.content.video.VideoItem;
import com.redrumming.thecreaturehub.models.detail.DetailItem;

/**
 * Created by ME on 12/5/2015.
 */
public class DescriptionItem implements DetailItem {

    private VideoItem videoItem;

    public VideoItem getVideoItem() {

        return videoItem;
    }

    public void setVideoItem(VideoItem videoItem) {

        this.videoItem = videoItem;
    }

    @Override
    public int getType() {

        return DetailItem.DESCRIPTION_ITEM;
    }
}

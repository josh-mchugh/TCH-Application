package com.redrumming.thecreaturehub.player.description;

import com.redrumming.thecreaturehub.contentItems.video.VideoItem;
import com.redrumming.thecreaturehub.player.details.DetailItem;

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

package com.redrumming.thecreaturehub.view.fragments.content.playlistvideo;

import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoContainer;
import com.redrumming.thecreaturehub.view.fragments.content.ContentFragmentPresenter;

/**
 * Created by ME on 1/8/2016.
 */
public interface PlaylistVideoFragmentPresenter extends ContentFragmentPresenter {

    void setContainer(PlaylistVideoContainer container);
}

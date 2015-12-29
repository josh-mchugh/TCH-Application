package com.redrumming.thecreaturehub.player.details;

import android.content.Context;
import android.widget.RelativeLayout;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 11/18/2015.
 */
public class DetailLoadingView extends RelativeLayout {

    public DetailLoadingView(Context context) {
        super(context);
        init();
    }

    private void init(){

        inflate(getContext(), R.layout.details_loading_view, this);
    }
}

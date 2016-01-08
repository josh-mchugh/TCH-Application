package com.redrumming.thecreaturehub.view.fragments.content;


import android.content.Context;

/**
 * Created by ME on 1/8/2016.
 */
public interface ContentFragmentView {

    void displayErrorMsg();
    void disableSwipeRefreshLoading();
    void notifyAdapterChange();

    Context getContext();
}

package com.redrumming.thecreaturehub.contentItems;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoadingItem extends Fragment implements ContentItem {


    public LoadingItem() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.loading_item, container, false);
    }

    @Override
    public int getItemType() {
        return LOADING_ITEM;
    }
}

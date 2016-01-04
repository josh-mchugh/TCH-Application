package com.redrumming.thecreaturehub.view.viewholders.content.loading;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 10/14/2015.
 */
public class LoadingViewHolder extends RecyclerView.ViewHolder{

    private ProgressBar loadingProgressBar;
    private TextView loadingText;

    public LoadingViewHolder(View itemView){
        super(itemView);

        loadingProgressBar = (ProgressBar) itemView.findViewById(R.id.loading_item_progress_bar);
        loadingText = (TextView) itemView.findViewById(R.id.loading_item_loading_text);
    }
}

package com.redrumming.thecreaturehub.view.viewholders.detail.description;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;

/**
 * Created by ME on 12/5/2015.
 */
public class DescriptionViewHolder extends RecyclerView.ViewHolder {

    private RelativeLayout headerContainer;
    private RelativeLayout contentContainer;
    private RelativeLayout footerContainer;

    private ImageView contentExpander;
    private TextView title;
    private TextView viewCount;

    private TextView publishedAt;
    private TextView description;
    private TextView categoryValue;
    private TextView licenseValue;

    private TextView likeCount;
    private TextView dislikeCount;

    public DescriptionViewHolder(View itemView) {
        super(itemView);

        headerContainer = (RelativeLayout) itemView.findViewById(R.id.video_description_header);
        contentContainer = (RelativeLayout) itemView.findViewById(R.id.video_description_content);
        footerContainer = (RelativeLayout) itemView.findViewById(R.id.video_description_footer);

        contentExpander = (ImageView) itemView.findViewById(R.id.video_content_expander);
        title = (TextView) itemView.findViewById(R.id.video_title);
        viewCount = (TextView) itemView.findViewById(R.id.video_view_count);

        publishedAt = (TextView) itemView.findViewById(R.id.video_published_date);
        description = (TextView) itemView.findViewById(R.id.video_description);
        categoryValue = (TextView) itemView.findViewById(R.id.video_category_value);
        licenseValue = (TextView) itemView.findViewById(R.id.video_license_value);

        likeCount = (TextView) itemView.findViewById(R.id.video_like_text);
        dislikeCount = (TextView) itemView.findViewById(R.id.video_dislike_text);
    }

    public RelativeLayout getHeaderContainer() {

        return headerContainer;
    }

    public RelativeLayout getContentContainer() {

        return contentContainer;
    }

    public RelativeLayout getFooterContainer() {

        return footerContainer;
    }

    public ImageView getContentExpander() {

        return contentExpander;
    }

    public TextView getTitle() {

        return title;
    }

    public TextView getViewCount() {

        return viewCount;
    }

    public TextView getPublishedAt() {

        return publishedAt;
    }

    public TextView getDescription() {

        return description;
    }

    public TextView getCategoryValue() {

        return categoryValue;
    }

    public TextView getLicenseValue() {

        return licenseValue;
    }

    public TextView getLikeCount() {

        return likeCount;
    }

    public TextView getDislikeCount() {

        return dislikeCount;
    }
}

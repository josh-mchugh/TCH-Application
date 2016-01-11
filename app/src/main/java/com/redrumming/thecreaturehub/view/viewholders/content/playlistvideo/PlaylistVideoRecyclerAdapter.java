package com.redrumming.thecreaturehub.view.viewholders.content.playlistvideo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.models.channel.ChannelItem;
import com.redrumming.thecreaturehub.models.content.ContentType;
import com.redrumming.thecreaturehub.view.viewholders.content.ContentRecyclerAdapter;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoContainer;
import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoItem;
import com.redrumming.thecreaturehub.util.NumberFormatterUtil;
import com.redrumming.thecreaturehub.util.TimePassedUtil;
import com.squareup.picasso.Picasso;

/**
 * Created by ME on 10/18/2015.
 */
public class PlaylistVideoRecyclerAdapter extends ContentRecyclerAdapter{

    public PlaylistVideoRecyclerAdapter(PlaylistVideoContainer container){

        super(container);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        if(viewType == ContentType.PLAYLIST_VIDEO_ITEM){

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_playlist_video, parent, false);
            
            super.setContext(parent.getContext());
            PlaylistVideoViewHolder viewHolder = new PlaylistVideoViewHolder(view);
            
            return viewHolder;
            
        }
        
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ContentType contentType = super.getContainer().getItems().get(position);

        if(contentType.getItemType() == ContentType.PLAYLIST_VIDEO_ITEM){

            PlaylistVideoItem video = (PlaylistVideoItem) contentType;
            ChannelItem channelItem = super.getContainer().getChannelItem();
            PlaylistVideoViewHolder viewHolder = (PlaylistVideoViewHolder) holder;

            Picasso.with(super.getContext()).load(video.getThumbnailURL()).into(viewHolder.getThumbnail());
            viewHolder.getTitle().setText(video.getTitle());

            Picasso.with(super.getContext())
                    .load(channelItem.getDisplayIconURL())
                    .error(R.drawable.display_user_profile_image_default)
                    .noFade()
                    .into(viewHolder.getChannelIcon());

            viewHolder.getPublishDate().setText(TimePassedUtil.getTimeDifference(video.getPublishedAt()));
            viewHolder.getViewCountSpacer().setText(" \u00B7 ");
            viewHolder.getViewCount().setText(video.getViewCount());
        }
    }
}

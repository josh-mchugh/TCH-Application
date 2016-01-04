package com.redrumming.thecreaturehub.models.content.playlist;

import android.os.Parcel;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.models.content.ContentItem;
import com.redrumming.thecreaturehub.models.content.ContentType;

/**
 * Created by ME on 7/30/2015.
 */
public class PlaylistItem extends ContentItem {

    private long videoCount;
    private boolean viewable;

    public PlaylistItem(){

    }

    public long getVideoCount() {

        return videoCount;
    }

    public void setVideoCount(long videoCount) {

        this.videoCount = videoCount;
    }

    public boolean isViewable() {

        return viewable;
    }

    public void setViewable(boolean viewable) {

        this.viewable = viewable;
    }

    @Override
    public int getItemType() {

        return ContentType.PLAYLIST_ITEM;
    }

    /**
     * Constructor used to un-flatten this object via Parcelable.
     *
     * @param parcel
     */
    public PlaylistItem(Parcel parcel){
        super(parcel);

        videoCount = parcel.readLong();
        viewable = parcel.readByte() != 0;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeLong(videoCount);
        dest.writeByte( (byte) (viewable ? 1 : 0));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PlaylistItem> CREATOR = new Parcelable.Creator<PlaylistItem>() {

        @Override
        public PlaylistItem createFromParcel(Parcel parcel) {

            return new PlaylistItem(parcel);
        }

        @Override
        public PlaylistItem[] newArray(int size) {

            return new PlaylistItem[size];
        }
    };
}

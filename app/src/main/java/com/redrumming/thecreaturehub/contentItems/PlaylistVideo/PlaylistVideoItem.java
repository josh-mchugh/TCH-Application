package com.redrumming.thecreaturehub.contentItems.PlaylistVideo;

import android.os.Parcel;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.contentItems.ContentType;
import com.redrumming.thecreaturehub.contentItems.video.VideoItem;

/**
 * Created by ME on 10/18/2015.
 */
public class PlaylistVideoItem extends VideoItem {

    private Long position;
    private boolean isPublic;
    private String playlistId;

    public PlaylistVideoItem(){

    }

    public String getPlaylistId() {

        return playlistId;
    }

    public void setPlaylistId(String playlistId) {

        this.playlistId = playlistId;
    }

    public Long getPosition() {

        return position;
    }

    public void setPosition(Long position) {

        this.position = position;
    }

    public boolean isPublic() {

        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {

        this.isPublic = isPublic;
    }

    @Override
    public int getItemType() {

        return ContentType.PLAYLIST_VIDEO_ITEM;
    }

    /**
     * Constructor used to un-flatten this object via Parcelable.
     *
     * @param parcel
     */
    public PlaylistVideoItem(Parcel parcel){
        super(parcel);

        position = parcel.readLong();
        isPublic = parcel.readByte() != 0;
        playlistId = parcel.readString();
    }

    @Override
    public int describeContents() {

        return ContentType.PLAYLIST_VIDEO_ITEM;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeLong(position);
        dest.writeByte((byte) (isPublic ? 1 : 0));
        dest.writeString(playlistId);
    }

    @SuppressWarnings("unused")
    public static Parcelable.Creator<PlaylistVideoItem> CREATOR = new Parcelable.Creator<PlaylistVideoItem>() {

        @Override
        public PlaylistVideoItem createFromParcel(Parcel parcel) {

            return new PlaylistVideoItem(parcel);
        }

        @Override
        public PlaylistVideoItem[] newArray(int size) {

            return new PlaylistVideoItem[size];
        }
    };
}

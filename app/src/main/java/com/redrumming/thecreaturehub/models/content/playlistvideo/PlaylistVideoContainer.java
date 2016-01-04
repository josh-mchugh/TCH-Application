package com.redrumming.thecreaturehub.models.content.playlistvideo;

import android.os.Parcel;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.models.content.ContentContainerType;

/**
 * Created by ME on 10/18/2015.
 */
public class PlaylistVideoContainer extends ContentContainer {

    private String playlistId;

    public PlaylistVideoContainer(){

    }

    public String getPlaylistId() {

        return playlistId;
    }

    public void setPlaylistId(String playlistId) {

        this.playlistId = playlistId;
    }

    @Override
    public int getType() {

        return ContentContainerType.PLAYLIST_VIDEO_CONTAINER;
    }

    /**
     * Constructor used to un-flatten this object via Parcelable.
     *
     * @param parcel
     */
    public PlaylistVideoContainer(Parcel parcel){
        super(parcel);

        playlistId = parcel.readString();
    }

    @Override
    public int describeContents() {

        return ContentContainerType.PLAYLIST_VIDEO_CONTAINER;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeString(playlistId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PlaylistVideoContainer> CREATOR = new Parcelable.Creator<PlaylistVideoContainer>() {

        @Override
        public PlaylistVideoContainer createFromParcel(Parcel parcel) {

            return new PlaylistVideoContainer(parcel);
        }

        @Override
        public PlaylistVideoContainer[] newArray(int size) {

            return new PlaylistVideoContainer[size];
        }
    };
}

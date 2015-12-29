package com.redrumming.thecreaturehub.contentItems.playlist;

import android.os.Parcel;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.contentItems.ContentContainer;
import com.redrumming.thecreaturehub.contentItems.ContentContainerType;

/**
 * Created by ME on 7/30/2015.
 */
public class PlaylistContainer extends ContentContainer {

    public PlaylistContainer() {

        super();
    }

    @Override
    public int getType() {

        return ContentContainerType.PLAYLIST_CONTAINER;
    }

    /**
     * Constructor used to un-flatten this object via the Parcelable.
     *
     * @param parcel
     */
    public PlaylistContainer(Parcel parcel){

        super(parcel);
    }

    @Override
    public int describeContents() {

        return ContentContainerType.PLAYLIST_CONTAINER;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PlaylistContainer> CREATOR = new Parcelable.Creator<PlaylistContainer>() {

        @Override
        public PlaylistContainer createFromParcel(Parcel parcel) {

            return new PlaylistContainer(parcel);
        }

        @Override
        public PlaylistContainer[] newArray(int size) {

            return new PlaylistContainer[size];
        }
    };
}

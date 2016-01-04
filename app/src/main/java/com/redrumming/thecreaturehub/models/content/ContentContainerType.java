package com.redrumming.thecreaturehub.models.content;

import android.os.Parcel;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.models.content.playlistvideo.PlaylistVideoContainer;
import com.redrumming.thecreaturehub.models.content.playlist.PlaylistContainer;
import com.redrumming.thecreaturehub.models.content.video.VideoContainer;

/**
 * Created by ME on 12/25/2015.
 */
public interface ContentContainerType extends Parcelable {

    int CONTENT_CONTAINER = 0;
    int VIDEO_CONTAINER = 1;
    int PLAYLIST_CONTAINER = 2;
    int PLAYLIST_VIDEO_CONTAINER = 3;

    int getType();

    @SuppressWarnings("unused")
    Parcelable.Creator<ContentContainerType> CREATOR = new Parcelable.Creator<ContentContainerType>() {

        @Override
        public ContentContainerType createFromParcel(Parcel parcel) {

            switch (parcel.readInt()){

                case CONTENT_CONTAINER:

                    return new ContentContainer(parcel);

                case VIDEO_CONTAINER:

                    return new VideoContainer(parcel);

                case PLAYLIST_CONTAINER:

                    return new PlaylistContainer(parcel);

                case PLAYLIST_VIDEO_CONTAINER:

                    return new PlaylistVideoContainer(parcel);
            }

            return null;
        }

        @Override
        public ContentContainerType[] newArray(int size) {

            return new ContentContainerType[size];
        }
    };
}

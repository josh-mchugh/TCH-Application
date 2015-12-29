package com.redrumming.thecreaturehub.contentItems;


import android.os.Parcel;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.contentItems.PlaylistVideo.PlaylistVideoItem;
import com.redrumming.thecreaturehub.contentItems.playlist.PlaylistItem;
import com.redrumming.thecreaturehub.contentItems.video.VideoItem;

/**
 * Created by ME on 8/4/2015.
 */
public interface ContentType extends Parcelable{

    int CONTENT_ITEM = 0;
    int LOADING_ITEM = 1;
    int VIDEO_ITEM = 2;
    int PLAYLIST_ITEM = 3;
    int PLAYLIST_VIDEO_ITEM = 4;

    int getItemType();

    @SuppressWarnings("unused")
    Parcelable.Creator<ContentType> CREATOR = new Parcelable.Creator<ContentType>() {

        @Override
        public ContentType createFromParcel(Parcel parcel) {

            switch (parcel.readInt()){

                case ContentType.VIDEO_ITEM:

                    return new VideoItem(parcel);

                case ContentType.PLAYLIST_ITEM:

                    return new PlaylistItem(parcel);

                case ContentType.PLAYLIST_VIDEO_ITEM:

                    return new PlaylistVideoItem(parcel);

                case ContentType.LOADING_ITEM:

                    return new LoadingItem(parcel);
            }

            return null;
        }

        @Override
        public ContentType[] newArray(int size) {

            return new ContentType[size];
        }
    };
}

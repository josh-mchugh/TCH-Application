package com.redrumming.thecreaturehub.models.content.video;

import android.os.Parcel;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.models.content.ContentContainerType;

/**
 * Created by ME on 7/26/2015.
 */
public class VideoContainer extends ContentContainer {

    public VideoContainer(){

        super();
    }

    @Override
    public int getType() {

        return ContentContainer.VIDEO_CONTAINER;
    }

    /**
     * Constructor used to un-flatten this object via Parcelable.
     *
     * @param parcel
     */
    public VideoContainer(Parcel parcel){

        super(parcel);
    }

    @Override
    public int describeContents() {

        return ContentContainerType.VIDEO_CONTAINER;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<VideoContainer> CREATOR = new Parcelable.Creator<VideoContainer>() {

        @Override
        public VideoContainer createFromParcel(Parcel parcel) {

            return new VideoContainer(parcel);
        }

        @Override
        public VideoContainer[] newArray(int size) {

            return new VideoContainer[size];
        }
    };
}


package com.redrumming.thecreaturehub.api.youtube.platlistItems.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ResourceId implements Parcelable{

    @SerializedName("videoId")
    @Expose
    private String videoId;


    public String getVideoId() {

        return videoId;
    }


    public void setVideoId(String videoId) {

        this.videoId = videoId;
    }

    /**
     *
     *
     * Parcelable Interface for this object below
     *
     *
     */

    protected ResourceId(Parcel parcel){

        videoId = parcel.readString();
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(videoId);
    }

    public static Parcelable.Creator<ResourceId> CREATOR = new Parcelable.Creator<ResourceId>(){

        @Override
        public ResourceId createFromParcel(Parcel parcel) {

            return new ResourceId(parcel);
        }

        @Override
        public ResourceId[] newArray(int size) {

            return new ResourceId[size];
        }
    };
}

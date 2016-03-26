
package com.redrumming.thecreaturehub.api.youtube.search.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Id implements Parcelable{

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

    protected Id(Parcel parcel){

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

    public static Parcelable.Creator<Id> CREATOR = new Parcelable.Creator<Id>(){

        @Override
        public Id createFromParcel(Parcel parcel) {

            return new Id(parcel);
        }

        @Override
        public Id[] newArray(int size) {

            return new Id[size];
        }
    };
}

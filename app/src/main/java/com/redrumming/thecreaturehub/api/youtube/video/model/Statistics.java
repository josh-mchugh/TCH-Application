
package com.redrumming.thecreaturehub.api.youtube.video.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Statistics implements Parcelable{

    @SerializedName("viewCount")
    @Expose
    private String viewCount;

    @SerializedName("likeCount")
    @Expose
    private String likeCount;

    @SerializedName("dislikeCount")
    @Expose
    private String dislikeCount;

    public String getViewCount() {

        return viewCount;
    }

    public void setViewCount(String viewCount) {

        this.viewCount = viewCount;
    }

    public String getLikeCount() {

        return likeCount;
    }

    public void setLikeCount(String likeCount) {

        this.likeCount = likeCount;
    }

    public String getDislikeCount() {

        return dislikeCount;
    }

    public void setDislikeCount(String dislikeCount) {

        this.dislikeCount = dislikeCount;
    }

    /**
     *
     *
     * Parcelable Interface for this object below
     *
     *
     *
     */

    protected Statistics(Parcel parcel){

        viewCount = parcel.readString();
        likeCount = parcel.readString();
        dislikeCount = parcel.readString();
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(viewCount);
        dest.writeString(likeCount);
        dest.writeString(dislikeCount);
    }

    public static Parcelable.Creator<Statistics> CREATOR = new Parcelable.Creator<Statistics>(){

        @Override
        public Statistics createFromParcel(Parcel parcel) {

            return new Statistics(parcel);
        }

        @Override
        public Statistics[] newArray(int size) {

            return new Statistics[size];
        }
    };
}

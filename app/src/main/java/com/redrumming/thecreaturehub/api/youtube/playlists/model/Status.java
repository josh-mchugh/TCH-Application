
package com.redrumming.thecreaturehub.api.youtube.playlists.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Status implements Parcelable{

    @SerializedName("privacyStatus")
    @Expose
    private String privacyStatus;


    public String getPrivacyStatus() {

        return privacyStatus;
    }


    public void setPrivacyStatus(String privacyStatus) {

        this.privacyStatus = privacyStatus;
    }

    /**
     *
     *
     * Parcelable Interface for this object below.
     *
     *
     */

    protected Status(Parcel parcel){

        privacyStatus = parcel.readString();
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(privacyStatus);
    }

    public static Parcelable.Creator<Status> CREATOR = new Parcelable.Creator<Status>(){

        @Override
        public Status createFromParcel(Parcel parcel) {

            return new Status(parcel);
        }

        @Override
        public Status[] newArray(int size) {

            return new Status[size];
        }
    };
}

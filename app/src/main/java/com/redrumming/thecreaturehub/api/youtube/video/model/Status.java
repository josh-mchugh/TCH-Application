
package com.redrumming.thecreaturehub.api.youtube.video.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Status implements Parcelable{

    @SerializedName("license")
    @Expose
    private String license;


    public String getLicense() {

        return license;
    }

    public void setLicense(String license) {

        this.license = license;
    }

    /**
     *
     *
     * Parcelable Interface for this object below
     *
     *
     */

    protected Status(Parcel parcel){

        license = parcel.readString();
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(license);
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

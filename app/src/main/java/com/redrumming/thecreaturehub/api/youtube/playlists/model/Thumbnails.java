
package com.redrumming.thecreaturehub.api.youtube.playlists.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Thumbnails implements Parcelable{

    @SerializedName("medium")
    @Expose
    private Medium medium;

    public Medium getMedium() {

        return medium;
    }

    public void setMedium(Medium medium) {

        this.medium = medium;
    }

    /**
     *
     *
     * Parcelable Interface for this object below
     *
     *
     */

    protected Thumbnails(Parcel parcel){

        medium = parcel.readParcelable(Medium.class.getClassLoader());
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeParcelable(medium, flags);
    }

    public static Parcelable.Creator<Thumbnails> CREATOR = new Parcelable.Creator<Thumbnails>(){

        @Override
        public Thumbnails createFromParcel(Parcel parcel) {

            return new Thumbnails(parcel);
        }

        @Override
        public Thumbnails[] newArray(int size) {

            return new Thumbnails[size];
        }
    };
}

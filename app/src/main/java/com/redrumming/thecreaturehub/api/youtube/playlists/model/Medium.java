
package com.redrumming.thecreaturehub.api.youtube.playlists.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Medium implements Parcelable{

    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }

    /**
     *
     *
     * Parcelable Interface fort this object below
     *
     *
     */

    protected Medium(Parcel parcel){

        url = parcel.readString();
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(url);
    }

    public static Parcelable.Creator<Medium> CREATOR = new Parcelable.Creator<Medium>(){

        @Override
        public Medium createFromParcel(Parcel parcel) {

            return new Medium(parcel);
        }

        @Override
        public Medium[] newArray(int size) {

            return new Medium[size];
        }
    };
}

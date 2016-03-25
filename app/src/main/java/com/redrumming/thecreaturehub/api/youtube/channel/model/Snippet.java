
package com.redrumming.thecreaturehub.api.youtube.channel.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Snippet implements Parcelable{

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("thumbnails")
    @Expose
    private Thumbnails thumbnails;


    public String getTitle() {

        return title;
    }


    public void setTitle(String title) {

        this.title = title;
    }


    public Thumbnails getThumbnails() {

        return thumbnails;
    }


    public void setThumbnails(Thumbnails thumbnails) {

        this.thumbnails = thumbnails;
    }

    /**
     *
     *
     *
     * Parcelable interface for this object below
     *
     *
     */

    protected Snippet(Parcel parcel){

        title = parcel.readString();
        thumbnails = parcel.readParcelable(Thumbnails.class.getClassLoader());
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeParcelable(thumbnails, flags);
    }

    public static final Parcelable.Creator<Snippet> CREATOR = new Parcelable.Creator<Snippet>(){

        @Override
        public Snippet createFromParcel(Parcel parcel) {

            return new Snippet(parcel);
        }

        @Override
        public Snippet[] newArray(int size) {

            return new Snippet[size];
        }
    };
}

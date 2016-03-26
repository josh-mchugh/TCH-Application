
package com.redrumming.thecreaturehub.api.youtube.platlistItems.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Snippet implements Parcelable{

    @SerializedName("playlistId")
    @Expose
    private String playlistId;

    @SerializedName("position")
    @Expose
    private int position;

    @SerializedName("resourceId")
    @Expose
    private ResourceId resourceId;


    public String getPlaylistId() {

        return playlistId;
    }


    public void setPlaylistId(String playlistId) {

        this.playlistId = playlistId;
    }


    public int getPosition() {

        return position;
    }


    public void setPosition(int position) {

        this.position = position;
    }


    public ResourceId getResourceId() {

        return resourceId;
    }


    public void setResourceId(ResourceId resourceId) {

        this.resourceId = resourceId;
    }

    /**
     *
     *
     * Parcelable Interface for this object below
     *
     *
     */

    protected Snippet(Parcel parcel){

        playlistId = parcel.readString();
        position = parcel.readInt();
        resourceId = parcel.readParcelable(ResourceId.class.getClassLoader());
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(playlistId);
        dest.writeInt(position);
        dest.writeParcelable(resourceId, flags);
    }

    public static Parcelable.Creator<Snippet> CREATOR = new Parcelable.Creator<Snippet>(){

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

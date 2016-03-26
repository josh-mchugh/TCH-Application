
package com.redrumming.thecreaturehub.api.youtube.playlists.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ContentDetails implements Parcelable{

    @SerializedName("itemCount")
    @Expose
    private int itemCount;

    public int getItemCount() {

        return itemCount;
    }

    public void setItemCount(int itemCount) {

        this.itemCount = itemCount;
    }

    /**
     *
     *
     * Parcelable Interface for this object below
     *
     *
     *
     */

    protected ContentDetails(Parcel parcel){

        itemCount = parcel.readInt();
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(itemCount);
    }

    public static Parcelable.Creator<ContentDetails> CREATOR = new Parcelable.Creator<ContentDetails>(){

        @Override
        public ContentDetails createFromParcel(Parcel parcel) {

            return new ContentDetails(parcel);
        }

        @Override
        public ContentDetails[] newArray(int size) {

            return new ContentDetails[size];
        }
    };
}

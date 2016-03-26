
package com.redrumming.thecreaturehub.api.youtube.search.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Item implements Parcelable{

    @SerializedName("id")
    @Expose
    private Id id;

    public Id getId() {

        return id;
    }

    public void setId(Id id) {

        this.id = id;
    }

    /**
     *
     *
     * Parcelable interface for this object below
     *
     *
     */

    protected Item(Parcel parcel){

        id = parcel.readParcelable(Id.class.getClassLoader());
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeParcelable(id, flags);
    }

    public static Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>(){

        @Override
        public Item createFromParcel(Parcel parcel) {

            return new Item(parcel);
        }

        @Override
        public Item[] newArray(int size) {

            return new Item[size];
        }
    };
}

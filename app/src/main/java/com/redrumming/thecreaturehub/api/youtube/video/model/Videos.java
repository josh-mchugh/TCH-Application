
package com.redrumming.thecreaturehub.api.youtube.video.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Videos implements Parcelable{

    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();

    public List<Item> getItems() {

        return items;
    }

    public void setItems(List<Item> items) {

        this.items = items;
    }

    /**
     *
     *
     * Parcelable Interface for this object below
     *
     *
     */

    protected Videos(Parcel parcel){

        parcel.readTypedList(items, Item.CREATOR);
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeTypedList(items);
    }

    public static Parcelable.Creator<Videos> CREATOR = new Parcelable.Creator<Videos>(){

        @Override
        public Videos createFromParcel(Parcel parcel) {

            return new Videos(parcel);
        }

        @Override
        public Videos[] newArray(int size) {

            return new Videos[size];
        }
    };
}

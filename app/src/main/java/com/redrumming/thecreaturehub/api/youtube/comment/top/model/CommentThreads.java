
package com.redrumming.thecreaturehub.api.youtube.comment.top.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class CommentThreads implements Parcelable{

    @SerializedName("nextPageToken")
    @Expose
    private String nextPageToken;

    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();


    public String getNextPageToken() {

        return nextPageToken;
    }


    public void setNextPageToken(String nextPageToken) {

        this.nextPageToken = nextPageToken;
    }


    public List<Item> getItems() {

        return items;
    }

    public void setItems(List<Item> items) {

        this.items = items;
    }


    /**
     *
     *
     * Parcelable Interface for this object below.
     *
     *
     */

    protected CommentThreads(Parcel parcel){

        nextPageToken = parcel.readString();
        parcel.readTypedList(items, Item.CREATOR);
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(nextPageToken);
        dest.writeTypedList(items);
    }

    public static Parcelable.Creator<CommentThreads> CREATOR = new Parcelable.Creator<CommentThreads>(){

        @Override
        public CommentThreads createFromParcel(Parcel parcel) {

            return new CommentThreads(parcel);
        }

        @Override
        public CommentThreads[] newArray(int size) {

            return new CommentThreads[size];
        }
    };
}

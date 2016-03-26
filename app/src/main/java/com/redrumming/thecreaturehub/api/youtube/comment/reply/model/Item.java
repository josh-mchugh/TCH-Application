
package com.redrumming.thecreaturehub.api.youtube.comment.reply.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Item implements Parcelable{

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("snippet")
    @Expose
    private Snippet snippet;


    public String getId() {

        return id;
    }


    public void setId(String id) {

        this.id = id;
    }


    public Snippet getSnippet() {

        return snippet;
    }


    public void setSnippet(Snippet snippet) {

        this.snippet = snippet;
    }


    /**
     *
     *
     * Parcelable Interface for this object below.
     *
     *
     */
    protected Item(Parcel parcel){

        id = parcel.readString();
        snippet = parcel.readParcelable(Snippet.class.getClassLoader());
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeParcelable(snippet, flags);
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

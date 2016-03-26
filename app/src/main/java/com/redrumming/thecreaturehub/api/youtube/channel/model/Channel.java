
package com.redrumming.thecreaturehub.api.youtube.channel.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Channel implements Parcelable{

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("snippet")
    @Expose
    private Snippet snippet;

    @SerializedName("statistics")
    @Expose
    private Statistics statistics;


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

    public Statistics getStatistics() {

        return statistics;
    }

    public void setStatistics(Statistics statistics) {

        this.statistics = statistics;
    }

    /**
     *
     *
     * Parcelable Interface for this item below.
     *
     *
     */

    protected Channel(Parcel parcel){

        id = parcel.readString();
        snippet = parcel.readParcelable(Snippet.class.getClassLoader());
        statistics = parcel.readParcelable(Statistics.class.getClassLoader());
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeParcelable(snippet, flags);
        dest.writeParcelable(statistics, flags);
    }

    public static Parcelable.Creator<Channel> CREATOR = new Parcelable.Creator<Channel>() {

        @Override
        public Channel createFromParcel(Parcel parcel) {

            return new Channel(parcel);
        }

        @Override
        public Channel[] newArray(int size) {

            return new Channel[size];
        }
    };
}

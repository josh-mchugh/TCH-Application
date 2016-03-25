
package com.redrumming.thecreaturehub.api.youtube.channel.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Channels implements Parcelable{

    public Channels(){


    }

    @SerializedName("items")
    @Expose
    private List<Channel> channels = new ArrayList<Channel>();


    public List<Channel> getChannels() {

        return channels;
    }

    public void setChannels(List<Channel> channels) {

        this.channels = channels;
    }


    /**
     *
     *
     *Parcelable interface below for this object.
     *
     *
     */

    protected Channels(Parcel parcel){

        parcel.readTypedList(channels, Channel.CREATOR);
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeList(channels);
    }

    public static final Parcelable.Creator<Channels> CREATOR = new Parcelable.Creator<Channels>(){

        @Override
        public Channels createFromParcel(Parcel parcel) {

            return new Channels(parcel);
        }

        @Override
        public Channels[] newArray(int size) {

            return new Channels[size];
        }
    };
}

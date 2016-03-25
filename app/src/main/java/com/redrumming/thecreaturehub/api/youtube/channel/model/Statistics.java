
package com.redrumming.thecreaturehub.api.youtube.channel.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Statistics implements Parcelable{

    @SerializedName("subscriberCount")
    @Expose
    private String subscriberCount;


    public String getSubscriberCount() {

        return subscriberCount;
    }

    public void setSubscriberCount(String subscriberCount) {

        this.subscriberCount = subscriberCount;
    }

    /**
     *
     *
     * Parcelable interface for this object below.
     *
     *
     */

    protected Statistics(Parcel parcel){

        subscriberCount = parcel.readString();
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(subscriberCount);
    }

    public static Parcelable.Creator<Statistics> CREATOR = new Parcelable.Creator<Statistics>(){

        @Override
        public Statistics createFromParcel(Parcel parcel) {

            return new Statistics(parcel);
        }

        @Override
        public Statistics[] newArray(int size) {

            return new Statistics[size];
        }
    };
}

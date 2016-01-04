package com.redrumming.thecreaturehub.models.channel;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigInteger;

/**
 * Created by ME on 7/19/2015.
 */
public class ChannelItem implements Parcelable {

    private String channelName;
    private String channelId;
    private BigInteger subscriberCount;
    private Bitmap displayIcon;

    public ChannelItem(){

    }

    public String getChannelName() {

        return channelName;
    }

    public void setChannelName(String channelName) {

        this.channelName = channelName;
    }

    public String getChannelId() {

        return channelId;
    }

    public void setChannelId(String channelId) {

        this.channelId = channelId;
    }

    public BigInteger getSubscriberCount() {

        return subscriberCount;
    }

    public void setSubscriberCount(BigInteger subscriberCount) {

        this.subscriberCount = subscriberCount;
    }

    public Bitmap getDisplayIcon() {

        return displayIcon;
    }

    public void setDisplayIcon(Bitmap displayIcon){

        this.displayIcon = displayIcon;
    }

    /**
     * Constructor used to un-flatten this object via Parcelable.
     *
     * @param parcel
     */
    protected ChannelItem(Parcel parcel) {

        channelName = parcel.readString();
        channelId = parcel.readString();
        subscriberCount = (BigInteger) parcel.readValue(BigInteger.class.getClassLoader());
        displayIcon = (Bitmap) parcel.readValue(Bitmap.class.getClassLoader());
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(channelName);
        dest.writeString(channelId);
        dest.writeValue(subscriberCount);
        dest.writeValue(displayIcon);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ChannelItem> CREATOR = new Parcelable.Creator<ChannelItem>() {

        @Override
        public ChannelItem createFromParcel(Parcel in) {

            return new ChannelItem(in);
        }

        @Override
        public ChannelItem[] newArray(int size) {

            return new ChannelItem[size];
        }
    };
}

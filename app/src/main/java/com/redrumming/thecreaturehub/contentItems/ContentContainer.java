package com.redrumming.thecreaturehub.contentItems;

import android.os.Parcel;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.channel.ChannelItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 10/23/2015.
 */public class ContentContainer implements ContentContainerType {

    private ChannelItem channelItem;
    private List<ContentType> items;
    private String pageToken;

    public ContentContainer(){

        items = new ArrayList<ContentType>();
        pageToken = "";
    }

    public ChannelItem getChannelItem() {

        return channelItem;
    }

    public void setChannelItem(ChannelItem channelItem) {

        this.channelItem = channelItem;
    }

    public List<ContentType> getItems() {

        return items;
    }

    public String getPageToken() {

        return pageToken;
    }

    public void setPageToken(String pageToken) {

        this.pageToken = pageToken;
    }

    @Override
    public int getType() {

        return ContentContainer.CONTENT_CONTAINER;
    }

    /**
     * Constructor used to un-flatten this object via Parcelable.
     *
     * @param parcel
     */
    public ContentContainer(Parcel parcel){
        this();

        parcel.readInt();

        channelItem = parcel.readParcelable(ChannelItem.class.getClassLoader());
        parcel.readTypedList(items, ContentType.CREATOR);
        pageToken = parcel.readString();
    }

    @Override
    public int describeContents() {

        return ContentContainer.CONTENT_CONTAINER;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(describeContents());

        dest.writeParcelable(channelItem, flags);
        dest.writeTypedList(items);
        dest.writeString(pageToken);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ContentContainer> CREATOR = new Parcelable.Creator<ContentContainer>() {

        @Override
        public ContentContainer createFromParcel(Parcel parcel) {

            return new ContentContainer(parcel);
        }

        @Override
        public ContentContainer[] newArray(int size) {

            return new ContentContainer[size];
        }
    };
}

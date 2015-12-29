package com.redrumming.thecreaturehub.contentItems;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ME on 12/23/2015.
 */
public abstract class ContentItem implements ContentType{

    private String id;
    private String title;
    private String thumbnailURL;
    private long publishedAt;

    public ContentItem(){

    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getThumbnailURL() {

        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {

        this.thumbnailURL = thumbnailURL;
    }

    public long getPublishedAt() {

        return publishedAt;
    }

    public void setPublishedAt(long publishedAt) {

        this.publishedAt = publishedAt;
    }

    @Override
    public int getItemType() {

        return ContentType.CONTENT_ITEM;
    }

    /**
     * Constructor used to un-flatten this object via Parcelable.
     *
     * @param parcel
     */
    public ContentItem(Parcel parcel){

        parcel.readInt();
        id = parcel.readString();
        title = parcel.readString();
        thumbnailURL = parcel.readString();
        publishedAt = parcel.readLong();
    }

    @Override
    public int describeContents() {

        return ContentType.CONTENT_ITEM;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(describeContents());
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(thumbnailURL);
        dest.writeLong(publishedAt);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ContentItem> CREATOR = new Parcelable.Creator<ContentItem>() {

        @Override
        public ContentItem createFromParcel(Parcel parcel) {

            return new ContentItem(parcel) {};
        }

        @Override
        public ContentItem[] newArray(int size) {

            return new ContentItem[size];
        }
    };
}

package com.redrumming.thecreaturehub.contentItems.video;

import android.os.Parcel;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.contentItems.ContentItem;
import com.redrumming.thecreaturehub.contentItems.ContentType;

import java.math.BigInteger;

/**
 * Created by ME on 7/26/2015.
 */
public class VideoItem extends ContentItem {

    private BigInteger viewCount;
    private BigInteger likeCount;
    private BigInteger dislikeCount;
    private String license;
    private String categoryId;
    private String description;

    public VideoItem(){

    }

    public BigInteger getViewCount() {

        return viewCount;
    }

    public void setViewCount(BigInteger viewCount) {

        this.viewCount = viewCount;
    }

    public BigInteger getLikeCount() {

        return likeCount;
    }

    public void setLikeCount(BigInteger likeCount) {

        this.likeCount = likeCount;
    }

    public BigInteger getDislikeCount() {

        return dislikeCount;
    }

    public void setDislikeCount(BigInteger dislikeCount) {

        this.dislikeCount = dislikeCount;
    }

    public String getLicense() {

        return license;
    }

    public void setLicense(String license) {

        this.license = license;
    }

    public String getCategoryId() {

        return categoryId;
    }

    public void setCategoryId(String categoryId) {

        this.categoryId = categoryId;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    @Override
    public int getItemType() {

        return ContentType.VIDEO_ITEM;
    }

    /**
     * Constructor used to un-flatten this object via Parcelable.
     *
     * @param parcel
     */
    public VideoItem(Parcel parcel){
        super(parcel);

        viewCount = (BigInteger) parcel.readValue(BigInteger.class.getClassLoader());
        likeCount = (BigInteger) parcel.readValue(BigInteger.class.getClassLoader());
        dislikeCount = (BigInteger) parcel.readValue(BigInteger.class.getClassLoader());
        license = parcel.readString();
        categoryId = parcel.readString();
        description = parcel.readString();

    }

    @Override
    public int describeContents() {

        return ContentType.VIDEO_ITEM;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeValue(viewCount);
        dest.writeValue(likeCount);
        dest.writeValue(dislikeCount);
        dest.writeString(license);
        dest.writeString(categoryId);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<VideoItem> CREATOR = new Parcelable.Creator<VideoItem>() {

        @Override
        public VideoItem createFromParcel(Parcel parcel) {

            return new VideoItem(parcel);
        }

        @Override
        public VideoItem[] newArray(int size) {

            return new VideoItem[size];
        }
    };
}

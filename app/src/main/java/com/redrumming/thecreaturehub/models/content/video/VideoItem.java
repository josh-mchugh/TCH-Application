package com.redrumming.thecreaturehub.models.content.video;

import android.os.Parcel;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.models.content.ContentItem;
import com.redrumming.thecreaturehub.models.content.ContentType;

/**
 * Created by ME on 7/26/2015.
 */
public class VideoItem extends ContentItem {

    private String viewCount;
    private String likeCount;
    private String dislikeCount;
    private String license;
    private String categoryId;
    private String description;

    public VideoItem(){

    }

    public String getViewCount() {

        return viewCount;
    }

    public void setViewCount(String viewCount) {

        this.viewCount = viewCount;
    }

    public String getLikeCount() {

        return likeCount;
    }

    public void setLikeCount(String likeCount) {

        this.likeCount = likeCount;
    }

    public String getDislikeCount() {

        return dislikeCount;
    }

    public void setDislikeCount(String dislikeCount) {

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

        viewCount = parcel.readString();
        likeCount = parcel.readString();
        dislikeCount = parcel.readString();
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

        dest.writeString(viewCount);
        dest.writeString(likeCount);
        dest.writeString(dislikeCount);
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

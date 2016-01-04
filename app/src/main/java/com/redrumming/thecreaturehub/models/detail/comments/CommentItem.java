package com.redrumming.thecreaturehub.models.detail.comments;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ME on 11/4/2015.
 */
public class CommentItem implements CommentItemType {

    private String id;
    private String displayName;
    private String channelId;
    private Long publishedAt;

    private String profileImageURL;
    private String textDisplay;

    public CommentItem(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Long getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Long publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public String getTextDisplay() {
        return textDisplay;
    }

    public void setTextDisplay(String textDisplay) {
        this.textDisplay = textDisplay;
    }

    @Override
    public int getItemType() {

        return CommentItemType.COMMENT_ITEM;
    }

    /**
     * Constructor for the un-flatting of this object via Parcelable.
     *
     * @param parcel
     */
    public CommentItem(Parcel parcel){

        parcel.readInt();
        id = parcel.readString();
        displayName = parcel.readString();
        channelId = parcel.readString();
        publishedAt = parcel.readLong();
        profileImageURL = parcel.readString();
        textDisplay = parcel.readString();
    }

    @Override
    public int describeContents() {

        return CommentItemType.COMMENT_ITEM;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(describeContents());

        dest.writeString(id);
        dest.writeString(displayName);
        dest.writeString(channelId);
        dest.writeLong(publishedAt);
        dest.writeString(profileImageURL);
        dest.writeString(textDisplay);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CommentItem> CREATOR = new Parcelable.Creator<CommentItem>(){

        @Override
        public CommentItem createFromParcel(Parcel parcel) {

            return new CommentItem(parcel);
        }

        @Override
        public CommentItem[] newArray(int size) {

            return new CommentItem[size];
        }
    };
}

package com.redrumming.thecreaturehub.player.comments;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 11/4/2015.
 */
public class CommentContainer implements CommentContainerType{

    private List<CommentItemType> commentItems;
    private String pageToken;
    private String videoId;

    public CommentContainer(){

        commentItems = new ArrayList<CommentItemType>();
        pageToken = "";
        videoId = "";
    }

    public CommentContainer(String videoId){

        commentItems = new ArrayList<CommentItemType>();
        pageToken = "";
        this.videoId = videoId;
    }

    public List<CommentItemType> getCommentItems() {

        return commentItems;
    }

    public String getPageToken() {

        return pageToken;
    }

    public void setPageToken(String pageToken) {

        this.pageToken = pageToken;
    }

    public String getVideoId() {

        return videoId;
    }

    @Override
    public int getType() {

        return CommentContainerType.COMMENT_CONTAINER;
    }

    /**
     * Constructor for the un-flatting of this object via Parcelable.
     *
     * @param parcel
     */
    public CommentContainer(Parcel parcel) {
        this();

        parcel.readInt();
        parcel.readTypedList(commentItems, CommentItemType.CREATOR);
        pageToken = parcel.readString();
        videoId = parcel.readString();
    }

    @Override
    public int describeContents() {

        return CommentContainerType.COMMENT_CONTAINER;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(describeContents());

        dest.writeTypedList(commentItems);
        dest.writeString(pageToken);
        dest.writeString(videoId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CommentContainer> CREATOR = new Parcelable.Creator<CommentContainer>() {

        @Override
        public CommentContainer createFromParcel(Parcel parcel) {

            return new CommentContainer(parcel);
        }

        @Override
        public CommentContainer[] newArray(int size) {

            return new CommentContainer[size];
        }
    };
}

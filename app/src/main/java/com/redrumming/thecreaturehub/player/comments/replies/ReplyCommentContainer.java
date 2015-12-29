package com.redrumming.thecreaturehub.player.comments.replies;

import android.os.Parcel;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.contentItems.ContentType;
import com.redrumming.thecreaturehub.player.comments.CommentContainer;
import com.redrumming.thecreaturehub.player.comments.CommentContainerType;
import com.redrumming.thecreaturehub.player.comments.CommentItemType;

import java.util.List;

/**
 * Created by ME on 11/9/2015.
 */
public class ReplyCommentContainer extends CommentContainer {

    private String parentId;

    private Creator creator = ContentType.CREATOR;

    public ReplyCommentContainer(){
        super();
        parentId = "";
    }

    public ReplyCommentContainer(String videoId) {
        super(videoId);
        parentId = "";
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public int getType() {

        return CommentContainerType.REPLY_COMMENT_CONTAINER;
    }

    /**
     * Constructor for the un-flattening of this object via Parcelable.
     *
     * @param parcel
     */
    public ReplyCommentContainer(Parcel parcel){
        super(parcel);

        parentId = parcel.readString();
    }

    @Override
    public int describeContents() {

        return CommentContainerType.REPLY_COMMENT_CONTAINER;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeString(parentId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ReplyCommentContainer> CREATOR = new Parcelable.Creator<ReplyCommentContainer>(){

        @Override
        public ReplyCommentContainer createFromParcel(Parcel parcel) {

            return new ReplyCommentContainer(parcel);
        }

        @Override
        public ReplyCommentContainer[] newArray(int size) {

            return new ReplyCommentContainer[size];
        }
    };
}

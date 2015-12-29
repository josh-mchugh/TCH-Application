package com.redrumming.thecreaturehub.player.comments.replies;

import android.os.Parcel;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.player.comments.CommentItem;
import com.redrumming.thecreaturehub.player.comments.CommentItemType;

/**
 * Created by ME on 11/9/2015.
 */
public class ReplyCommentItem extends CommentItem {

    private String parentId;

    public ReplyCommentItem(){

    }

    public String getParentId() {

        return parentId;
    }

    public void setParentId(String parentId) {

        this.parentId = parentId;
    }

    @Override
    public int getItemType() {

        return REPLY_COMMENT;
    }

    /**
     * Constructor for the un-flatting of this object via Parcelable.
     *
     * @param parcel
     */
    public ReplyCommentItem(Parcel parcel){
        super(parcel);

        parentId = parcel.readString();
    }

    @Override
    public int describeContents() {

        return CommentItemType.REPLY_COMMENT;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeString(parentId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ReplyCommentItem> CREATOR = new Parcelable.Creator<ReplyCommentItem>() {

        @Override
        public ReplyCommentItem createFromParcel(Parcel parcel) {

            return new ReplyCommentItem(parcel);
        }

        @Override
        public ReplyCommentItem[] newArray(int size) {

            return new ReplyCommentItem[size];
        }
    };
}

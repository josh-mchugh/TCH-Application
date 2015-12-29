package com.redrumming.thecreaturehub.player.comments.topComments;

import android.os.Parcel;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.player.comments.CommentItem;
import com.redrumming.thecreaturehub.player.comments.CommentItemType;
import com.redrumming.thecreaturehub.player.comments.replies.ReplyCommentContainer;
import com.redrumming.thecreaturehub.player.details.DetailItem;

/**
 * Created by ME on 11/9/2015.
 */
public class TopLevelCommentItem extends CommentItem implements DetailItem {

    private ReplyCommentContainer replies;
    private Long totalReplyCount;

    public TopLevelCommentItem(){

    }

    public ReplyCommentContainer getReplies() {

        return replies;
    }

    public void setReplies(ReplyCommentContainer replies) {

        this.replies = replies;
    }

    public Long getTotalReplyCount() {

        return totalReplyCount;
    }

    public void setTotalReplyCount(Long totalReplyCount) {

        this.totalReplyCount = totalReplyCount;
    }

    public boolean hasReplies(){

        if(replies != null) {

            if (replies.getCommentItems().size() > 0) {

                return true;
            }
        }

        return false;
    }

    @Override
    public int getItemType() {

        return CommentItemType.TOP_LEVEL_COMMENT;
    }

    @Override
    public int getType() {

        return DetailItem.COMMENT_TOP_LEVEL_ITEM;
    }

    /**
     * Constructor for the un-flatting of this object via Parcelable.
     *
     * @param parcel
     */
    public TopLevelCommentItem(Parcel parcel){
        super(parcel);

        replies = parcel.readParcelable(ReplyCommentContainer.class.getClassLoader());
        totalReplyCount = parcel.readLong();
    }

    @Override
    public int describeContents() {

        return CommentItemType.TOP_LEVEL_COMMENT;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeParcelable(replies, flags);
        dest.writeLong(totalReplyCount);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TopLevelCommentItem> CREATOR = new Parcelable.Creator<TopLevelCommentItem>() {

        @Override
        public TopLevelCommentItem createFromParcel(Parcel parcel) {

            return new TopLevelCommentItem(parcel);
        }

        @Override
        public TopLevelCommentItem[] newArray(int size) {

            return new TopLevelCommentItem[size];
        }
    };
}

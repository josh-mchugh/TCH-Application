package com.redrumming.thecreaturehub.models.detail.comments;

import android.os.Parcel;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.models.detail.comments.reply.ReplyCommentItem;
import com.redrumming.thecreaturehub.models.detail.comments.top.TopLevelCommentItem;

/**
 * Created by ME on 11/8/2015.
 */
public interface CommentItemType extends Parcelable{

    int COMMENT_ITEM = 1;
    int TOP_LEVEL_COMMENT = 2;
    int REPLY_COMMENT = 3;

    int getItemType();

    @SuppressWarnings("unused")
    Parcelable.Creator<CommentItemType> CREATOR = new Creator<CommentItemType>() {

        @Override
        public CommentItemType createFromParcel(Parcel parcel) {

            switch (parcel.readInt()){

                case COMMENT_ITEM:

                    return new CommentItem(parcel);

                case TOP_LEVEL_COMMENT:

                    return new TopLevelCommentItem(parcel);

                case REPLY_COMMENT:

                    return new ReplyCommentItem(parcel);
            }

            return null;
        }

        @Override
        public CommentItemType[] newArray(int size) {

            return new CommentItemType[size];
        }
    };
}

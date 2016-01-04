package com.redrumming.thecreaturehub.models.detail.comments;

import android.os.Parcel;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.models.detail.comments.reply.ReplyCommentContainer;

/**
 * Created by ME on 12/25/2015.
 */
public interface CommentContainerType extends Parcelable {

    int COMMENT_CONTAINER = 0;
    int REPLY_COMMENT_CONTAINER = 1;

    int getType();

    @SuppressWarnings("unused")
    Parcelable.Creator<CommentContainerType> CREATOR = new Parcelable.Creator<CommentContainerType>() {

        @Override
        public CommentContainer createFromParcel(Parcel parcel) {

            switch (parcel.readInt()){

                case COMMENT_CONTAINER:

                    return new CommentContainer(parcel);

                case REPLY_COMMENT_CONTAINER:

                    return new ReplyCommentContainer(parcel);
            }

            return null;
        }

        @Override
        public CommentContainer[] newArray(int size) {

            return new CommentContainer[size];
        }
    };
}

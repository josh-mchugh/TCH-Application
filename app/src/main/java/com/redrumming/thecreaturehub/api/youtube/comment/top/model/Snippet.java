
package com.redrumming.thecreaturehub.api.youtube.comment.top.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.redrumming.thecreaturehub.api.youtube.comment.top.model.topLevelComment.TopLevelComment;

@Generated("org.jsonschema2pojo")
public class Snippet implements Parcelable {

    @SerializedName("topLevelComment")
    @Expose
    private TopLevelComment topLevelComment;

    @SerializedName("totalReplyCount")
    @Expose
    private int totalReplyCount;

    public TopLevelComment getTopLevelComment() {

        return topLevelComment;
    }

    public void setTopLevelComment(TopLevelComment topLevelComment) {

        this.topLevelComment = topLevelComment;
    }

    public int getTotalReplyCount() {

        return totalReplyCount;
    }

    public void setTotalReplyCount(int totalReplyCount) {

        this.totalReplyCount = totalReplyCount;
    }

    /**
     *
     *
     * Parcelable Interface for this object below.
     *
     */

    protected Snippet(Parcel parcel){

        totalReplyCount = parcel.readInt();
        topLevelComment = parcel.readParcelable(TopLevelComment.class.getClassLoader());
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(totalReplyCount);
        dest.writeParcelable(topLevelComment, flags);

    }

    public static Parcelable.Creator<Snippet> CREATOR = new Parcelable.Creator<Snippet>(){

        @Override
        public Snippet createFromParcel(Parcel parcel) {

            return new Snippet(parcel);
        }

        @Override
        public Snippet[] newArray(int size) {

            return new Snippet[size];
        }
    };
}

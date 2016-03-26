
package com.redrumming.thecreaturehub.api.youtube.comment.top.model.topLevelComment;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Snippet implements Parcelable{

    @SerializedName("authorDisplayName")
    @Expose
    private String authorDisplayName;

    @SerializedName("authorProfileImageUrl")
    @Expose
    private String authorProfileImageUrl;

    @SerializedName("textDisplay")
    @Expose
    private String textDisplay;

    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;


    public String getAuthorDisplayName() {

        return authorDisplayName;
    }


    public void setAuthorDisplayName(String authorDisplayName) {

        this.authorDisplayName = authorDisplayName;
    }


    public String getAuthorProfileImageUrl() {

        return authorProfileImageUrl;
    }


    public void setAuthorProfileImageUrl(String authorProfileImageUrl) {

        this.authorProfileImageUrl = authorProfileImageUrl;
    }


    public String getTextDisplay() {

        return textDisplay;
    }


    public void setTextDisplay(String textDisplay) {

        this.textDisplay = textDisplay;
    }


    public String getPublishedAt() {

        return publishedAt;
    }


    public void setPublishedAt(String publishedAt) {

        this.publishedAt = publishedAt;
    }

    /***
     *
     *
     * Parcelable interface for this object below
     *
     *
     */

    protected Snippet(Parcel parcel){

        authorDisplayName = parcel.readString();
        authorProfileImageUrl = parcel.readString();
        textDisplay = parcel.readString();
        publishedAt = parcel.readString();
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(authorDisplayName);
        dest.writeString(authorProfileImageUrl);
        dest.writeString(textDisplay);
        dest.writeString(publishedAt);
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

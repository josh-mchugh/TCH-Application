
package com.redrumming.thecreaturehub.api.youtube.video.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Snippet implements Parcelable{

    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("thumbnails")
    @Expose
    private Thumbnails thumbnails;

    @SerializedName("categoryId")
    @Expose
    private String categoryId;

    public String getPublishedAt() {

        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {

        this.publishedAt = publishedAt;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public Thumbnails getThumbnails() {

        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {

        this.thumbnails = thumbnails;
    }

    public String getCategoryId() {

        return categoryId;
    }

    public void setCategoryId(String categoryId) {

        this.categoryId = categoryId;
    }

    /***
     *
     *
     *
     * Parcelable Interface for this object below
     *
     *
     *
     */

    protected Snippet(Parcel parcel){

        publishedAt = parcel.readString();
        title = parcel.readString();
        description = parcel.readString();
        thumbnails = parcel.readParcelable(Thumbnails.class.getClassLoader());
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(publishedAt);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeParcelable(thumbnails, flags);
    }

    public static Parcelable.Creator<Snippet> CREATOR = new Parcelable.Creator<Snippet>() {

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

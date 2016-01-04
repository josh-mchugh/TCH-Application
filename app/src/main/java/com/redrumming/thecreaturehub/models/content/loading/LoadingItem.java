package com.redrumming.thecreaturehub.models.content.loading;

import android.os.Parcel;
import android.os.Parcelable;

import com.redrumming.thecreaturehub.models.content.ContentType;

public class LoadingItem implements ContentType {


    public LoadingItem() {
    }

    @Override
    public int getItemType() {

        return ContentType.LOADING_ITEM;
    }

    /**
     * Constructor used to un-flatten this object via Parcelable.
     *
     * @param parcel
     */
    public LoadingItem(Parcel parcel){

    }

    @Override
    public int describeContents() {

        return ContentType.LOADING_ITEM;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(getItemType());
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<LoadingItem> CREATOR = new Parcelable.Creator<LoadingItem>() {

        @Override
        public LoadingItem createFromParcel(Parcel parcel) {

            return new LoadingItem(parcel);
        }

        @Override
        public LoadingItem[] newArray(int size) {

            return new LoadingItem[size];
        }
    };
}

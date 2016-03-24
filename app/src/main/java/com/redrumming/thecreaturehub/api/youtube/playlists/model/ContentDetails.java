
package com.redrumming.thecreaturehub.api.youtube.playlists.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ContentDetails {

    @SerializedName("itemCount")
    @Expose
    private int itemCount;

    /**
     * 
     * @return
     *     The itemCount
     */
    public int getItemCount() {
        return itemCount;
    }

    /**
     * 
     * @param itemCount
     *     The itemCount
     */
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

}

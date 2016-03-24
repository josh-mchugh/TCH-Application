
package com.redrumming.thecreaturehub.api.youtube.channel.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Thumbnails {

    @SerializedName("medium")
    @Expose
    private Medium medium;

    /**
     * 
     * @return
     *     The medium
     */
    public Medium getMedium() {
        return medium;
    }

    /**
     * 
     * @param medium
     *     The medium
     */
    public void setMedium(Medium medium) {
        this.medium = medium;
    }

}

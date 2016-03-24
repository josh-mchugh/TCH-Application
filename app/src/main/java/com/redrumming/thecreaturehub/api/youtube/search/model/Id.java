
package com.redrumming.thecreaturehub.api.youtube.search.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Id {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("videoId")
    @Expose
    private String videoId;

    /**
     * 
     * @return
     *     The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * 
     * @param kind
     *     The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * 
     * @return
     *     The videoId
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * 
     * @param videoId
     *     The videoId
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

}

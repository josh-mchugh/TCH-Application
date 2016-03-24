
package com.redrumming.thecreaturehub.api.youtube.video.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Statistics {

    @SerializedName("viewCount")
    @Expose
    private String viewCount;
    @SerializedName("likeCount")
    @Expose
    private String likeCount;
    @SerializedName("dislikeCount")
    @Expose
    private String dislikeCount;

    /**
     * 
     * @return
     *     The viewCount
     */
    public String getViewCount() {
        return viewCount;
    }

    /**
     * 
     * @param viewCount
     *     The viewCount
     */
    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * 
     * @return
     *     The likeCount
     */
    public String getLikeCount() {
        return likeCount;
    }

    /**
     * 
     * @param likeCount
     *     The likeCount
     */
    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * 
     * @return
     *     The dislikeCount
     */
    public String getDislikeCount() {
        return dislikeCount;
    }

    /**
     * 
     * @param dislikeCount
     *     The dislikeCount
     */
    public void setDislikeCount(String dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

}

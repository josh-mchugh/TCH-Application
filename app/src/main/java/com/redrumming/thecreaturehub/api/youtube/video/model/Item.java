
package com.redrumming.thecreaturehub.api.youtube.video.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Item {

    @SerializedName("snippet")
    @Expose
    private Snippet snippet;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("statistics")
    @Expose
    private Statistics statistics;

    /**
     * 
     * @return
     *     The snippet
     */
    public Snippet getSnippet() {
        return snippet;
    }

    /**
     * 
     * @param snippet
     *     The snippet
     */
    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The statistics
     */
    public Statistics getStatistics() {
        return statistics;
    }

    /**
     * 
     * @param statistics
     *     The statistics
     */
    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

}

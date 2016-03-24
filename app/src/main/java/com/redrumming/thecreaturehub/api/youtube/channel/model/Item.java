
package com.redrumming.thecreaturehub.api.youtube.channel.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Item {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("snippet")
    @Expose
    private Snippet snippet;
    @SerializedName("statistics")
    @Expose
    private Statistics statistics;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

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


package com.redrumming.thecreaturehub.api.youtube.playlists.model;

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
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("contentDetails")
    @Expose
    private ContentDetails contentDetails;

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
     *     The contentDetails
     */
    public ContentDetails getContentDetails() {
        return contentDetails;
    }

    /**
     * 
     * @param contentDetails
     *     The contentDetails
     */
    public void setContentDetails(ContentDetails contentDetails) {
        this.contentDetails = contentDetails;
    }

}

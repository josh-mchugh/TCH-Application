
package com.redrumming.thecreaturehub.api.youtube.comment.reply.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Snippet {

    @SerializedName("authorDisplayName")
    @Expose
    private String authorDisplayName;
    @SerializedName("authorProfileImageUrl")
    @Expose
    private String authorProfileImageUrl;
    @SerializedName("textDisplay")
    @Expose
    private String textDisplay;
    @SerializedName("parentId")
    @Expose
    private String parentId;
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;

    /**
     * 
     * @return
     *     The authorDisplayName
     */
    public String getAuthorDisplayName() {
        return authorDisplayName;
    }

    /**
     * 
     * @param authorDisplayName
     *     The authorDisplayName
     */
    public void setAuthorDisplayName(String authorDisplayName) {
        this.authorDisplayName = authorDisplayName;
    }

    /**
     * 
     * @return
     *     The authorProfileImageUrl
     */
    public String getAuthorProfileImageUrl() {
        return authorProfileImageUrl;
    }

    /**
     * 
     * @param authorProfileImageUrl
     *     The authorProfileImageUrl
     */
    public void setAuthorProfileImageUrl(String authorProfileImageUrl) {
        this.authorProfileImageUrl = authorProfileImageUrl;
    }

    /**
     * 
     * @return
     *     The textDisplay
     */
    public String getTextDisplay() {
        return textDisplay;
    }

    /**
     * 
     * @param textDisplay
     *     The textDisplay
     */
    public void setTextDisplay(String textDisplay) {
        this.textDisplay = textDisplay;
    }

    /**
     * 
     * @return
     *     The parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 
     * @param parentId
     *     The parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 
     * @return
     *     The publishedAt
     */
    public String getPublishedAt() {
        return publishedAt;
    }

    /**
     * 
     * @param publishedAt
     *     The publishedAt
     */
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

}

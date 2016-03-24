
package com.redrumming.thecreaturehub.api.youtube.video.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Snippet {

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

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The thumbnails
     */
    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    /**
     * 
     * @param thumbnails
     *     The thumbnails
     */
    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    /**
     * 
     * @return
     *     The categoryId
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * 
     * @param categoryId
     *     The categoryId
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

}


package com.redrumming.thecreaturehub.api.youtube.platlistItems.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Snippet {

    @SerializedName("playlistId")
    @Expose
    private String playlistId;
    @SerializedName("position")
    @Expose
    private int position;
    @SerializedName("resourceId")
    @Expose
    private ResourceId resourceId;

    /**
     * 
     * @return
     *     The playlistId
     */
    public String getPlaylistId() {
        return playlistId;
    }

    /**
     * 
     * @param playlistId
     *     The playlistId
     */
    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    /**
     * 
     * @return
     *     The position
     */
    public int getPosition() {
        return position;
    }

    /**
     * 
     * @param position
     *     The position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * 
     * @return
     *     The resourceId
     */
    public ResourceId getResourceId() {
        return resourceId;
    }

    /**
     * 
     * @param resourceId
     *     The resourceId
     */
    public void setResourceId(ResourceId resourceId) {
        this.resourceId = resourceId;
    }

}

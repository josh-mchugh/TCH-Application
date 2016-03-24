
package com.redrumming.thecreaturehub.api.youtube.playlists.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Status {

    @SerializedName("privacyStatus")
    @Expose
    private String privacyStatus;

    /**
     * 
     * @return
     *     The privacyStatus
     */
    public String getPrivacyStatus() {
        return privacyStatus;
    }

    /**
     * 
     * @param privacyStatus
     *     The privacyStatus
     */
    public void setPrivacyStatus(String privacyStatus) {
        this.privacyStatus = privacyStatus;
    }

}

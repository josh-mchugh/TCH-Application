
package com.redrumming.thecreaturehub.api.youtube.video.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Status {

    @SerializedName("license")
    @Expose
    private String license;

    /**
     * 
     * @return
     *     The license
     */
    public String getLicense() {
        return license;
    }

    /**
     * 
     * @param license
     *     The license
     */
    public void setLicense(String license) {
        this.license = license;
    }

}


package com.redrumming.thecreaturehub.api.youtube.channel.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Statistics {

    @SerializedName("subscriberCount")
    @Expose
    private String subscriberCount;

    /**
     * 
     * @return
     *     The subscriberCount
     */
    public String getSubscriberCount() {
        return subscriberCount;
    }

    /**
     * 
     * @param subscriberCount
     *     The subscriberCount
     */
    public void setSubscriberCount(String subscriberCount) {
        this.subscriberCount = subscriberCount;
    }

}

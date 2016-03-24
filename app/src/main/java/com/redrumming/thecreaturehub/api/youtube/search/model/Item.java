
package com.redrumming.thecreaturehub.api.youtube.search.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Item {

    @SerializedName("id")
    @Expose
    private Id id;

    /**
     * 
     * @return
     *     The id
     */
    public Id getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Id id) {
        this.id = id;
    }

}

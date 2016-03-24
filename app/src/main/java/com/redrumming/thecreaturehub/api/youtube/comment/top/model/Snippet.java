
package com.redrumming.thecreaturehub.api.youtube.comment.top.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Snippet {

    @SerializedName("topLevelComment")
    @Expose
    private TopLevelComment topLevelComment;
    @SerializedName("totalReplyCount")
    @Expose
    private int totalReplyCount;

    /**
     * 
     * @return
     *     The topLevelComment
     */
    public TopLevelComment getTopLevelComment() {
        return topLevelComment;
    }

    /**
     * 
     * @param topLevelComment
     *     The topLevelComment
     */
    public void setTopLevelComment(TopLevelComment topLevelComment) {
        this.topLevelComment = topLevelComment;
    }

    /**
     * 
     * @return
     *     The totalReplyCount
     */
    public int getTotalReplyCount() {
        return totalReplyCount;
    }

    /**
     * 
     * @param totalReplyCount
     *     The totalReplyCount
     */
    public void setTotalReplyCount(int totalReplyCount) {
        this.totalReplyCount = totalReplyCount;
    }

}

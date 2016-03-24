package com.redrumming.thecreaturehub.api.youtube.comment.reply;

import com.redrumming.thecreaturehub.api.youtube.comment.CommentsParams;

/**
 * Created by ME on 3/24/2016.
 */
public class ReplyCommentsParams extends CommentsParams {

    public static final String PARAM_PARENT_ID = "parentId";
    public static String PARAM_TEXT_FORMAT = "textFormat";

    public static final String VALUE_PART = "id, snippet";
    public static final String VALUE_FIELDS = "items( "
            + "id, "
            + "snippet/authorDisplayName, "
            + "snippet/authorProfileImageUrl, "
            + "snippet/textDisplay, "
            + "snippet/publishedAt, "
            + "snippet/parentId "
            + "), nextPageToken";
    public static final String VALUE_TEXT_FORMAT = "plainText";
    public static final String VALUE_REPLY_COMMENT_MAX_RESULTS = "2";

}

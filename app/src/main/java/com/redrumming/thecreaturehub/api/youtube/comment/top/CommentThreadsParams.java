package com.redrumming.thecreaturehub.api.youtube.comment.top;

import com.redrumming.thecreaturehub.api.youtube.comment.CommentsParams;

/**
 * Created by ME on 3/24/2016.
 */
public class CommentThreadsParams extends CommentsParams {

    public static final String PARAM_VIDEO_ID = "videoId";
    public static final String PARAM_ORDER = "order";

    public static final String VALUE_PART = "id, snippet";
    public static final String VALUE_FIELDS = "items("
            + "id, "
            + "snippet/totalReplyCount, "
            + "snippet/topLevelComment/id, "
            + "snippet/topLevelComment/snippet/authorDisplayName, "
            + "snippet/topLevelComment/snippet/authorProfileImageUrl, "
            + "snippet/topLevelComment/snippet/textDisplay, "
            + "snippet/topLevelComment/snippet/publishedAt"
            + "), nextPageToken";
    public static final String VALUE_ORDER = "relevance";
    public static final String VALUE_TOP_COMMENT_MAX_RESULTS = "5";

    /*parameters.put(CommentThreadsParams.PARAM_PART, CommentThreadsParams.VALUE_PART);
    parameters.put(CommentThreadsParams.PARAM_FIELDS, CommentThreadsParams.VALUE_FIELDS);
    parameters.put(CommentThreadsParams.PARAM_VIDEO_ID, "n6vXyqKpIZ0");
    parameters.put(CommentThreadsParams.PARAM_TEXT_FORMAT, CommentThreadsParams.VALUE_TEXT_FORMAT);
    parameters.put(CommentThreadsParams.PARAM_MAX_RESULTS, CommentThreadsParams.VALUE_TOP_COMMENT_MAX_RESULTS);
    parameters.put(CommentThreadsParams.PARAM_ORDER, CommentThreadsParams.VALUE_ORDER);
    parameters.put(CommentThreadsParams.PARAM_API_KEY, CommentThreadsParams.VALUE_API_KEY);*/
}

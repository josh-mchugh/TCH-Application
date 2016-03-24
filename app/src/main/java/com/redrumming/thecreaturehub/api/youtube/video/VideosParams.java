package com.redrumming.thecreaturehub.api.youtube.video;

import com.redrumming.thecreaturehub.api.youtube.YouTubeParams;

/**
 * Created by ME on 3/24/2016.
 */
public class VideosParams extends YouTubeParams {

    public static final String PARAM_ID = "id";

    public static final String VALUE_PART = "snippet, statistics, status";
    public static final String VALUE_FIELDS = "items(" +
            "snippet/title," +
            " snippet/thumbnails/medium/url," +
            " snippet/description," +
            " snippet/publishedAt," +
            " snippet/categoryId,statistics/viewCount," +
            " statistics/dislikeCount, " +
            "statistics/likeCount,status/license" +
            "),nextPageToken";


    /*Map<String, String> parameters = new HashMap<>();
    parameters.put(VideosParams.PARAM_PART, VideosParams.VALUE_PART);
    parameters.put(VideosParams.PARAM_FIELDS, VideosParams.VALUE_FIELDS);
    parameters.put(VideosParams.PARAM_MAX_RESULTS, VideosParams.VALUE_MAX_RESULTS);
    parameters.put(VideosParams.PARAM_ID, "M1OlSuMlXMo, vrYhhGaa5mU");
    parameters.put(VideosParams.PARAM_API_KEY, VideosParams.VALUE_API_KEY);*/

}

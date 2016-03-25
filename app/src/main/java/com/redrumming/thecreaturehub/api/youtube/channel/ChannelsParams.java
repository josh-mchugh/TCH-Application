package com.redrumming.thecreaturehub.api.youtube.channel;

import com.redrumming.thecreaturehub.api.youtube.YouTubeParams;
import com.redrumming.thecreaturehub.retrofit.utils.SimpleJoiner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ME on 3/23/2016.
 */
public class ChannelsParams extends YouTubeParams{

    private static final String PARAM_ID = "id";

    private static final String VALUE_PART = "id, snippet, statistics";
    private static final String VALUE_FIELDS = "items(" +
            "id, " +
            "snippet/title, " +
            "snippet/thumbnails/medium/url, " +
            "statistics/subscriberCount)";

    public static Map<String, String> createParams(String[] channelIds){

        Map<String, String> parameters = new HashMap<>();

        parameters.put(PARAM_PART, VALUE_PART);
        parameters.put(PARAM_FIELDS, VALUE_FIELDS);
        parameters.put(PARAM_ID, SimpleJoiner.join(channelIds));
        parameters.put(PARAM_API_KEY, VALUE_API_KEY);

        return parameters;
    }
}

package com.redrumming.thecreaturehub.api.youtube.channel;

import com.redrumming.thecreaturehub.api.youtube.YouTubeParams;

/**
 * Created by ME on 3/23/2016.
 */
public class ChannelParams extends YouTubeParams{

    public static final String PARAM_ID = "id";

    public static final String VALUE_PART = "id, snippet, statistics";
    public static final String VALUE_FIELDS = "items(id,snippet/title, snippet/thumbnails/medium/url,statistics/subscriberCount)";
}

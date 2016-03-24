package com.redrumming.thecreaturehub.api.youtube.playlists;

import com.redrumming.thecreaturehub.api.youtube.YouTubeParams;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ME on 3/24/2016.
 */
public class PlaylistsParams extends YouTubeParams {

    public static final String PARAM_CHANNEL_ID = "channelId";

    public static final String VALUE_PART = "id, snippet, contentDetails, status";
    public static final String VALUE_FIELDS = "items("
            + "id,"
            + "snippet/title,"
            + "snippet/thumbnails/medium/url,"
            + "snippet/publishedAt,"
            + "status/privacyStatus,"
            + "contentDetails/itemCount), nextPageToken";

    /*Map<String, String> parameters = new HashMap<>();
    parameters.put(PlaylistsParams.PARAM_PART, PlaylistsParams.VALUE_PART);
    parameters.put(PlaylistsParams.PARAM_CHANNEL_ID, "UC6JmDMC2x3vafwH5QRB1khA");
    parameters.put(PlaylistsParams.PARAM_PAGE_TOKEN, "CBQQAA");
    parameters.put(PlaylistsParams.PARAM_FIELDS, PlaylistsParams.VALUE_FIELDS);
    parameters.put(PlaylistsParams.PARAM_MAX_RESULTS, PlaylistsParams.VALUE_MAX_RESULTS);
    parameters.put(PlaylistsParams.PARAM_API_KEY, PlaylistsParams.VALUE_API_KEY);*/
}

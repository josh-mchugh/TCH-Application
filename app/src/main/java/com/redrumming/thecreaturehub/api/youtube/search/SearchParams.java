package com.redrumming.thecreaturehub.api.youtube.search;

import com.redrumming.thecreaturehub.api.youtube.YouTubeParams;

/**
 * Created by ME on 3/23/2016.
 */
public class SearchParams extends YouTubeParams {

    public static final String PARAM_CHANNEL_ID = "channelId";
    public static final String PARAM_ORDER = "order";

    public static final String VALUE_PART = "id";
    public static final String VALUE_FIELDS = "items/id,nextPageToken";
    public static final String VALUE_ORDER = "date";

    /*Map<String, String> parameters = new HashMap<String, String>();
    parameters.put(SearchParameters.PARAM_PART, SearchParameters.VALUE_PART);
    parameters.put(SearchParameters.PARAM_CHANNEL_ID, "UC6JmDMC2x3vafwH5QRB1khA");
    parameters.put(SearchParameters.PARAM_FIELDS, SearchParameters.VALUE_FIELDS);
    parameters.put(SearchParameters.PARAM_MAX_RESULTS, SearchParameters.VALUE_MAX_RESULTS);
    parameters.put(SearchParameters.PARAM_ORDER, SearchParameters.VALUE_ORDER);
    parameters.put(SearchParameters.PARAM_API_KEY, YouTubeApiKey.API_KEY);*/

}

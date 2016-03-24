package com.redrumming.thecreaturehub.api.youtube.platlistItems;

import com.redrumming.thecreaturehub.api.youtube.YouTubeParams;

/**
 * Created by ME on 3/24/2016.
 */
public class PlaylistItemsParams extends YouTubeParams {

    public static final String PARAMS_PLAYLIST_ID = "playlistId";

    public static final String VALUE_PART = "id,snippet,status";
    public static final String VALUE_FIELDS = "items( "
            + "id, "
            + "snippet/playlistId, "
            + "snippet/position, "
            + "snippet/resourceId/videoId, "
            + "status/privacyStatus "
            + "), "
            + "nextPageToken";

    /*parameters.put(PlaylistItemsParams.PARAM_PART, PlaylistItemsParams.VALUE_PART);
    parameters.put(PlaylistItemsParams.PARAMS_PLAYLIST_ID, "PL5OhtVfausvY5LkfB6dv8drsveYBBd3lZ");
    parameters.put(PlaylistItemsParams.PARAM_FIELDS, PlaylistItemsParams.VALUE_FIELDS);
    parameters.put(PlaylistItemsParams.PARAM_MAX_RESULTS, PlaylistItemsParams.VALUE_MAX_RESULTS);
    parameters.put(PlaylistItemsParams.PARAM_API_KEY, PlaylistItemsParams.VALUE_API_KEY);*/
}

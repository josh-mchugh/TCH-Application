package com.redrumming.thecreaturehub.youtube;

import android.content.Context;

import com.google.api.client.util.Joiner;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.CommentListResponse;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.VideoListResponse;

import java.util.List;

/**
 * Created by ME on 11/17/2015.
 */
public class YouTubeServiceCalls {

    private final String VIDEO_TYPE = "video";
    private final String CONTENT_ORDER_TYPE = "date";
    private final Long CONTENT_MAX_RESULT = 20l;

    private final String TOP_COMMENT_ORDER_TYPE = "relevance";
    private final long TOP_COMMENT_MAX_RESULT = 5l;
    private final String COMMENT_FORMAT = "plainText";
    private final long COMMENT_MAX_RESULT = 2l;


    public YouTube youtube;

    public YouTubeServiceCalls(Context context) {

        youtube = YouTubeInstance.get(context).getYouTube();
    }

    public SearchListResponse getVideoIds(String channelId, String pageToken) throws Exception {

        SearchListResponse response = youtube.search().list("id")
                    .setChannelId(channelId)
                    .setType(VIDEO_TYPE)
                    .setFields("items( "
                            + "id/videoId"
                            + "), nextPageToken")
                    .setOrder(CONTENT_ORDER_TYPE)
                    .setMaxResults(CONTENT_MAX_RESULT)
                    .setPageToken(pageToken)
                    .setKey(YouTubeApiKey.API_KEY)
                    .execute();

        return response;
    }

    public VideoListResponse getVideoItems(List<String> videoIds) throws Exception {

        String ids = joinIds(videoIds);

        VideoListResponse response = youtube.videos().list("id, snippet, statistics, status")
                .setId(ids)
                .setFields("items("
                        + "id,"
                        + "snippet/title,"
                        + "snippet/thumbnails/medium/url,"
                        + "snippet/description,"
                        + "snippet/publishedAt, "
                        + "snippet/categoryId, "
                        + "status/license, "
                        + "statistics/viewCount, "
                        + "statistics/dislikeCount, "
                        + "statistics/likeCount)")
                .setMaxResults((long) videoIds.size())
                .setKey(YouTubeApiKey.API_KEY)
                .execute();

        return response;
    }

    public PlaylistListResponse getPlaylists(String channelId, String pageToken) throws Exception {

        PlaylistListResponse response = youtube.playlists().list("id, snippet, contentDetails, status")
                .setChannelId(channelId)
                .setPageToken(pageToken)
                .setFields("items("
                        + "id,"
                        + "snippet/title,"
                        + "snippet/thumbnails/medium/url,"
                        + "snippet/publishedAt,"
                        + "status/privacyStatus,"
                        + "contentDetails/itemCount), nextPageToken")
                .setMaxResults(CONTENT_MAX_RESULT)
                .setKey(YouTubeApiKey.API_KEY)
                .execute();

        return response;
    }

    public PlaylistItemListResponse getPlaylistItems(String playlistId, String pageToken) throws Exception {

        PlaylistItemListResponse response = youtube.playlistItems().list("id,snippet,status")
                .setPlaylistId(playlistId)
                .setFields("items( "
                        + "id, "
                        + "snippet/playlistId, "
                        + "snippet/position, "
                        + "snippet/resourceId/videoId, "
                        + "status/privacyStatus "
                        + "), "
                        + "nextPageToken")
                .setMaxResults(CONTENT_MAX_RESULT)
                .setPageToken(pageToken)
                .setKey(YouTubeApiKey.API_KEY)
                .execute();

        return response;
    }

    public ChannelListResponse getChannels(List<String> ids) throws Exception {

        String channelIds = joinIds(ids);

        ChannelListResponse response = youtube.channels().list("id, snippet, statistics")
                .setFields("items( "
                        + "id, "
                        + "snippet/title, "
                        + "snippet/thumbnails/medium/url, "
                        + "statistics/subscriberCount"
                        + ")")
                .setId(channelIds)
                .setKey(YouTubeApiKey.API_KEY)
                .execute();

        return response;
    }

    public CommentThreadListResponse getTopLevelComments(String videoId, String pageToken)throws Exception {

        CommentThreadListResponse response = youtube.commentThreads().list("id, snippet")
                .setFields("items("
                        + "id, "
                        + "snippet/totalReplyCount, "
                        + "snippet/topLevelComment/id, "
                        + "snippet/topLevelComment/snippet/authorDisplayName, "
                        + "snippet/topLevelComment/snippet/authorProfileImageUrl, "
                        + "snippet/topLevelComment/snippet/authorChannelId/value, "
                        + "snippet/topLevelComment/snippet/textDisplay, "
                        + "snippet/topLevelComment/snippet/publishedAt"
                        + "), nextPageToken")
                .setVideoId(videoId)
                .setPageToken(pageToken)
                .setTextFormat(COMMENT_FORMAT)
                .setMaxResults(TOP_COMMENT_MAX_RESULT)
                .setOrder(TOP_COMMENT_ORDER_TYPE)
                .setKey(YouTubeApiKey.API_KEY)
                .execute();

        return response;
    }

    public CommentListResponse getReplyComments(String parentId, String pageToken) throws Exception {

        CommentListResponse response = youtube.comments().list("id, snippet")
                .setFields("items( "
                        + "id, "
                        + "snippet/authorDisplayName, "
                        + "snippet/authorProfileImageUrl, "
                        + "snippet/authorChannelId/value, "
                        + "snippet/textDisplay, "
                        + "snippet/publishedAt "
                        + "), nextPageToken")
                .setParentId(parentId)
                .setTextFormat(COMMENT_FORMAT)
                .setMaxResults(COMMENT_MAX_RESULT)
                .setPageToken(pageToken)
                .setKey(YouTubeApiKey.API_KEY)
                .execute();

        return response;
    }

    private String joinIds(List<String> ids){

        Joiner videoIdJoiner = Joiner.on(',');
        String joinedIds = videoIdJoiner.join(ids);

        return joinedIds;
    }
}
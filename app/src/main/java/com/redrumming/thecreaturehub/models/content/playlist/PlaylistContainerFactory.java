package com.redrumming.thecreaturehub.models.content.playlist;

import android.content.Context;
import android.util.Log;

import com.google.api.services.youtube.model.PlaylistListResponse;
import com.redrumming.thecreaturehub.models.content.ContentContainer;
import com.redrumming.thecreaturehub.youtube.YouTubeServiceCalls;

import java.util.Date;

/**
 * Created by ME on 3/19/2016.
 */
public class PlaylistContainerFactory {

    public static PlaylistContainer createPlaylistContainer(Context context, PlaylistContainer container){

        PlaylistContainer updateContainer = new PlaylistContainer();

        updateContainer.setChannelItem(container.getChannelItem());
        updateContainer.setPageToken(container.getPageToken());

        updateContainer = getPlaylists(context, updateContainer);

        logger(updateContainer);

        return updateContainer;
    }

    private static PlaylistContainer getPlaylists(Context context, PlaylistContainer container){

        try{

            PlaylistListResponse searchResponse = new YouTubeServiceCalls(context).getPlaylists(container.getChannelItem().getChannelId(), container.getPageToken());

            container.getItems().addAll(PlaylistItemFactory.createPlaylistItems(searchResponse));
            container.setPageToken(searchResponse.getNextPageToken());

        }catch(Exception e){

            Log.e(PlaylistContainer.class.getName(), "Error retrieve search results: " + e);
        }

        return container;
    }

    private static void logger(ContentContainer container){

        String className = PlaylistContainer.class.getName();

        for(int i = 0; i < container.getItems().size(); i++){

            PlaylistItem playlist = (PlaylistItem) container.getItems().get(i);

            Log.d(className, "Playlist Id: " + playlist.getId());
            Log.d(className, "Playlist Title: " + playlist.getTitle());
            Log.d(className, "Playlist Thumbnail URL: " + playlist.getThumbnailURL());
            Log.d(className, "Playlist Published At: " + new Date(playlist.getPublishedAt()));
            Log.d(className, "Playlist Public: " + playlist.isViewable());
            Log.d(className, "Playlist Item Count: " + playlist.getVideoCount());
        }

        Log.d(className, "Next Page Token: " + container.getPageToken());
    }
}

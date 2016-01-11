package com.redrumming.thecreaturehub.models.content.playlist;

import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.redrumming.thecreaturehub.models.content.ContentItemFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 1/10/2016.
 */
public class PlaylistItemFactory extends ContentItemFactory{

    public static List<PlaylistItem> createPlaylistItems(PlaylistListResponse searchResponse){

        List<Playlist> searchResults = searchResponse.getItems();

        List<PlaylistItem> playlistItems = new ArrayList<PlaylistItem>();

        for(int i = 0; i < searchResults.size(); i++){

            Playlist playlist = searchResults.get(i);
            com.redrumming.thecreaturehub.models.content.playlist.PlaylistItem playlistItem = new com.redrumming.thecreaturehub.models.content.playlist.PlaylistItem();

            playlistItem.setId(playlist.getId());
            playlistItem.setTitle(playlist.getSnippet().getTitle());
            playlistItem.setThumbnailURL(playlist.getSnippet().getThumbnails().getMedium().getUrl());
            playlistItem.setPublishedAt(playlist.getSnippet().getPublishedAt().getValue());
            playlistItem.setVideoCount(playlist.getContentDetails().getItemCount());
            playlistItem.setViewable(playlist.getStatus().getPrivacyStatus().equalsIgnoreCase("public"));

            playlistItems.add(playlistItem);
        }

        return playlistItems;
    }
}

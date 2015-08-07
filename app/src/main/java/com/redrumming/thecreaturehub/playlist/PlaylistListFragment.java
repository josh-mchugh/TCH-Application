package com.redrumming.thecreaturehub.playlist;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.Channel;


/**
 *
 */
public class PlaylistListFragment extends Fragment implements PlaylistAsyncListener{

    private RecyclerView recyclerView;
    private PlaylistContainer playlistContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.playlist_list_fragment, container, false);

        Channel channel = new Channel();
        channel.setChannelName(getActivity().getResources().getStringArray(R.array.channel_names)[0]);
        channel.setChannelId(getActivity().getResources().getStringArray(R.array.channel_ids)[0]);

        int imageResource = this.getActivity().getResources().getIdentifier(
                getActivity().getResources().getStringArray(R.array.channel_display_icons)[0],
                "drawable", this.getActivity().getApplicationContext().getPackageName());
        Drawable displayIcon = this.getActivity().getResources().getDrawable(imageResource);
        channel.setDisplayIcon(displayIcon);

        setup(channel);

        recyclerView = (RecyclerView) view.findViewById(R.id.playlist_recycler_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        playlistContainer = new PlaylistContainer();
        playlistContainer.setChannel(channel);
        PlaylistRecyclerAdapter adapter = new PlaylistRecyclerAdapter(playlistContainer);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void setup(Channel channel){

        PlaylistContainer container = new PlaylistContainer();
        container.setChannel(channel);

        PlaylistAsync async = new PlaylistAsync(getActivity(), this);
        async.execute(container);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSuccess(PlaylistContainer container) {

        this.playlistContainer.getPlaylists().addAll(container.getPlaylists());
        this.playlistContainer.setPageToken(container.getPageToken());
        this.recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onFailure() {

    }
}

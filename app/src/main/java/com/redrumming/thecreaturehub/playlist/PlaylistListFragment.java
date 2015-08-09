package com.redrumming.thecreaturehub.playlist;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.EndlessScrollListener;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.Channel;


/**
 *
 */
public class PlaylistListFragment extends Fragment implements PlaylistAsyncListener{

    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity() );

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
        recyclerView.setLayoutManager(linearLayoutManager);

        playlistContainer = new PlaylistContainer();
        playlistContainer.setChannel(channel);
        PlaylistRecyclerAdapter adapter = new PlaylistRecyclerAdapter(playlistContainer);
        recyclerView.setAdapter(adapter);

        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.playlist_swipe_refresh);
        swipeRefresh.setOnRefreshListener(onRefreshListener);

        recyclerView.addOnScrollListener(endlessScrollListener);

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

        this.playlistContainer.getPlaylistWrappers().addAll(container.getPlaylistWrappers());
        this.playlistContainer.setPageToken(container.getPageToken());
        this.recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onFailure() {

    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {

        @Override
        public void onRefresh() {
            Log.d(this.getClass().getName(), "On Refresh called.");
            swipeRefresh.setRefreshing(false);
        }
    };

    private EndlessScrollListener endlessScrollListener = new EndlessScrollListener(linearLayoutManager) {

        @Override
        public void onLoadMore() {
            Log.d(this.getClass().getName(), "On Load More.");
        }
    };
}

package com.redrumming.thecreaturehub.contentItems.playlist;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.redrumming.thecreaturehub.contentItems.ContentItem;
import com.redrumming.thecreaturehub.contentItems.LoadingItem;
import com.redrumming.thecreaturehub.RecyclerOnItemClickListener;
import com.redrumming.thecreaturehub.contentItems.PlaylistVideo.PlaylistVideoAsync;
import com.redrumming.thecreaturehub.contentItems.PlaylistVideo.PlaylistVideoContainer;
import com.redrumming.thecreaturehub.contentItems.PlaylistVideo.PlaylistVideoFragment;
import com.redrumming.thecreaturehub.util.EndlessScrollListener;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.Channel;


/**
 *
 */
public class PlaylistListFragment extends Fragment implements PlaylistAsyncListener{

    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;

    private PlaylistContainer playlistContainer = new PlaylistContainer();
    private PlaylistRecyclerAdapter adapter = new PlaylistRecyclerAdapter(playlistContainer);
    private EndlessScrollListener scrollListener;

    private PlaylistAsync async;

    private boolean isLoading = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.playlist_list_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.playlist_recycler_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity() );
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.playlist_swipe_refresh);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                PlaylistListFragment.this.onRefresh();
            }
        });

        scrollListener = new EndlessScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore() {

                PlaylistListFragment.this.onLoadMore();
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.addOnItemTouchListener(new RecyclerOnItemClickListener(getActivity(), onItemClickListener));

        return view;
    }

    public void setup(Context context, Channel channel){

        playlistContainer.setChannel(channel);

        playlistContainer.setPageToken("");
        playlistContainer.getPlaylistWrappers().clear();

        checkAsyncStatus();
        async = new PlaylistAsync(context, this);
        async.execute(playlistContainer);

        playlistContainer.getPlaylistWrappers().add(new LoadingItem());
        adapter.notifyDataSetChanged();
        isLoading = true;
    }

    private void onLoadMore(){

        if(!isLoading){

            checkAsyncStatus();
            async = new PlaylistAsync(getActivity(), this);
            async.execute(playlistContainer);

            playlistContainer.getPlaylistWrappers().add(new LoadingItem());
            adapter.notifyDataSetChanged();
            isLoading = true;
        }
    }

    private void onRefresh(){

        if(!isLoading){

            playlistContainer.setPageToken("");
            playlistContainer.getPlaylistWrappers().clear();

            checkAsyncStatus();
            async = new PlaylistAsync(getActivity(), this);
            async.execute(playlistContainer);

            isLoading = true;
        }
    }

    private void updateView(PlaylistContainer container){

        if(isLoading){

            if(swipeRefresh != null && swipeRefresh.isRefreshing()){

                swipeRefresh.setRefreshing(false);
            }

            if(playlistContainer.getPlaylistWrappers().size() > 0){

                int lastItem = (playlistContainer.getPlaylistWrappers().size() - 1);
                ContentItem contentItem = playlistContainer.getPlaylistWrappers().get(lastItem);

                if(contentItem.getItemType() == ContentItem.LOADING_ITEM){

                    playlistContainer.getPlaylistWrappers().remove(lastItem);
                    adapter.notifyDataSetChanged();
                }
            }

            isLoading = false;
        }

        playlistContainer.getPlaylistWrappers().addAll(container.getPlaylistWrappers());
        playlistContainer.setPageToken(container.getPageToken());
        adapter.notifyDataSetChanged();
    }

    private void checkAsyncStatus(){

        if(async != null){

            if(async.getStatus() == AsyncTask.Status.RUNNING){

                async.cancel(true);
            }

            async = null;
        }
    }

    private void onSelect(int position){

        PlaylistWrapper playlist = null;

        if(playlistContainer.getPlaylistWrappers().get(position).getItemType() == ContentItem.PLAYLIST_ITEM){

            playlist = (PlaylistWrapper) playlistContainer.getPlaylistWrappers().get(position);
        }

        if(playlist != null){

            Toast.makeText(getActivity(), "Playlist Id: " + playlist.getId(), Toast.LENGTH_SHORT).show();

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            PlaylistVideoFragment playlistVideoFragment = new PlaylistVideoFragment();

            PlaylistVideoContainer container = new PlaylistVideoContainer();
            container.setChannel(playlistContainer.getChannel());
            container.setPageToken("");
            container.setPlaylistId(playlist.getId());

            playlistVideoFragment.setArguments(new Bundle());
            playlistVideoFragment.getArguments().putSerializable("playlist video container", container);

            ft.replace(R.id.content, playlistVideoFragment);
            ft.addToBackStack("tabbedFragment");
            ft.commit();
        }
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

        if(container.getChannel() != null && container.getPlaylistWrappers() != null) {

            updateView(container);
        }
    }

    @Override
    public void onFailure() {

    }

    private RecyclerOnItemClickListener.OnItemClickListener onItemClickListener = new RecyclerOnItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

            PlaylistListFragment.this.onSelect(position);
        }
    };
}

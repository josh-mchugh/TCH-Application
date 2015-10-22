package com.redrumming.thecreaturehub.contentItems.PlaylistVideo;

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

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.RecyclerOnItemClickListener;
import com.redrumming.thecreaturehub.channel.Channel;
import com.redrumming.thecreaturehub.contentItems.ContentItem;
import com.redrumming.thecreaturehub.contentItems.LoadingItem;
import com.redrumming.thecreaturehub.player.VideoPlayer;
import com.redrumming.thecreaturehub.util.EndlessScrollListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistVideoFragment extends Fragment implements PlaylistVideoAsyncListener{

    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;

    private PlaylistVideoContainer container;
    private PlaylistVideoRecyclerAdapter adapter;
    private EndlessScrollListener scrollListener;

    private PlaylistVideoAsync async;

    private boolean isLoading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if(bundle != null){

            this.container = (PlaylistVideoContainer) getArguments().getSerializable("playlist video container");

        }else{

            this.container = new PlaylistVideoContainer();
        }

        View view = inflater.inflate(R.layout.playlist_video_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.playlist_video_recycler_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new PlaylistVideoRecyclerAdapter(this.container);
        recyclerView.setAdapter(adapter);

        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.playlist_video_swipe_refresh);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                PlaylistVideoFragment.this.onRefresh();
            }
        });

        scrollListener = new EndlessScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore() {

                PlaylistVideoFragment.this.onLoadMore();
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.addOnItemTouchListener(new RecyclerOnItemClickListener(getActivity(), onItemClickListener));

        setup(getActivity(), this.container);

        return view;
    }

    public void setup(Context context, PlaylistVideoContainer container){

        this.container = container;

        checkAsyncStatus();
        async = new PlaylistVideoAsync(context, this);
        async.execute(container);

        isLoading = true;
    }

    private void onLoadMore(){

        if(container.getPageToken() != null) {

            if (!isLoading) {

                checkAsyncStatus();
                async = new PlaylistVideoAsync(getActivity(), this);
                async.execute(container);

                container.getVideoWrappers().add(new LoadingItem());
                adapter.notifyDataSetChanged();
                isLoading = true;
            }
        }
    }

    private void onRefresh(){

        if(!isLoading){

            container.setPageToken("");
            container.getVideoWrappers().clear();
            container.getVideoWrappers().add(new LoadingItem());
            adapter.notifyDataSetChanged();

            checkAsyncStatus();
            async = new PlaylistVideoAsync(getActivity(), this);
            async.execute(container);

            isLoading = true;
        }
    }

    private void updateView(PlaylistVideoContainer container){

        if(isLoading){

            if(swipeRefresh != null && swipeRefresh.isRefreshing()){

                swipeRefresh.setRefreshing(false);
            }

            if(this.container.getVideoWrappers().size() > 0){

                int lastItem = (this.container.getVideoWrappers().size() - 1);
                ContentItem contentItem = this.container.getVideoWrappers().get(lastItem);

                if(contentItem.getItemType() == ContentItem.LOADING_ITEM){

                    this.container.getVideoWrappers().remove(lastItem);
                    adapter.notifyDataSetChanged();
                }
            }

            isLoading = false;
        }

        this.container.getVideoWrappers().addAll(container.getVideoWrappers());
        this.container.setPageToken(container.getPageToken());
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

        PlaylistVideoWrapper video = null;
        Channel channel = null;

        if(container.getVideoWrappers().get(position).getItemType() == ContentItem.PLAYLIST_VIDEO_ITEM){

            video = (PlaylistVideoWrapper) container.getVideoWrappers().get(position);
            channel = container.getChannel();
        }

        if(video != null && channel != null){

            Toast.makeText(getActivity(), "Playlist Video Id: " + video.getVideoId(), Toast.LENGTH_SHORT).show();

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            VideoPlayer videoPlayer = new VideoPlayer();

            videoPlayer.setArguments(new Bundle());
            videoPlayer.getArguments().putSerializable("video", video);
            videoPlayer.getArguments().putSerializable("channel", channel);

            ft.replace(R.id.content, videoPlayer);
            ft.addToBackStack("tabbedFragment");
            ft.commit();
        }
    }

    @Override
    public void onSuccess(PlaylistVideoContainer container) {

        if(container.getChannel() != null && container.getVideoWrappers() != null){

            updateView(container);
        }
    }

    @Override
    public void onFailure() {

    }

    private RecyclerOnItemClickListener.OnItemClickListener onItemClickListener = new RecyclerOnItemClickListener.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {

            PlaylistVideoFragment.this.onSelect(position);
        }
    };
}

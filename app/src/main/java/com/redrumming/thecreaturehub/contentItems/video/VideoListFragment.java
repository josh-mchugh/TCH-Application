package com.redrumming.thecreaturehub.contentItems.video;

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
import com.redrumming.thecreaturehub.player.VideoPlayer;
import com.redrumming.thecreaturehub.util.EndlessScrollListener;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.Channel;


/**
 *
 */
public class VideoListFragment extends Fragment implements VideoAsyncListener{

    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;

    private VideoContainer videoContainer = new VideoContainer();
    private VideoRecyclerAdapter adapter = new VideoRecyclerAdapter(videoContainer);
    private EndlessScrollListener scrollListener;

    private VideoAsync async;

    private boolean isLoading = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.video_list_fragment, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.video_recycler_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

        swipeRefresh = (SwipeRefreshLayout) v.findViewById(R.id.video_swipe_refresh);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                VideoListFragment.this.onRefresh();
            }
        });

        scrollListener = new EndlessScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore() {

                VideoListFragment.this.onLoadMore();
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.addOnItemTouchListener(new RecyclerOnItemClickListener(getActivity(), onItemClickListener));

        return v;
    }

    public void setup(Context context, Channel channel){

        videoContainer.setChannel(channel);

        videoContainer.setPageToken("");
        videoContainer.getVideoWrappers().clear();

        checkAsyncStatus();
        async = new VideoAsync(context, this);
        async.execute(videoContainer);

        videoContainer.getVideoWrappers().add(new LoadingItem());
        adapter.notifyDataSetChanged();
        isLoading = true;
    }

    private void onLoadMore(){

        if(!isLoading) {

            checkAsyncStatus();
            async = new VideoAsync(getActivity(), this);
            async.execute(videoContainer);

            videoContainer.getVideoWrappers().add(new LoadingItem());
            adapter.notifyDataSetChanged();
            isLoading = true;
        }
    }

    private void onRefresh(){

        if(!isLoading) {

            videoContainer.setPageToken("");
            videoContainer.getVideoWrappers().clear();

            checkAsyncStatus();
            async = new VideoAsync(getActivity(), this);
            async.execute(videoContainer);

            isLoading = true;
        }
    }

    private void updateView(VideoContainer container){

        if(isLoading){

            if(swipeRefresh != null && swipeRefresh.isRefreshing()){

                swipeRefresh.setRefreshing(false);
            }

            if(videoContainer.getVideoWrappers().size() > 0) {

                int lastItem = (videoContainer.getVideoWrappers().size() - 1);
                ContentItem contentItem = videoContainer.getVideoWrappers().get(lastItem);

                if (contentItem.getItemType() == ContentItem.LOADING_ITEM) {

                    videoContainer.getVideoWrappers().remove(lastItem);
                    adapter.notifyDataSetChanged();
                }
            }

            isLoading = false;
        }

        videoContainer.getVideoWrappers().addAll(container.getVideoWrappers());
        videoContainer.setPageToken(container.getPageToken());
        adapter.notifyDataSetChanged();
    }

    private void checkAsyncStatus(){

        if(async != null) {

            if (async.getStatus() == AsyncTask.Status.RUNNING) {

                async.cancel(true);
            }

            async = null;
        }
    }

    private void onSelect(int position){

        VideoWrapper video = null;
        Channel channel = null;

        if(videoContainer.getVideoWrappers().get(position).getItemType() == ContentItem.VIDEO_ITEM){

            video = (VideoWrapper) videoContainer.getVideoWrappers().get(position);
            channel = videoContainer.getChannel();
        }

        if(video != null && channel != null) {

            Toast.makeText(getActivity(), "Video Id: " + video.getVideoId(), Toast.LENGTH_SHORT).show();

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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSuccess(VideoContainer container) {

        if(container.getChannel() != null && container.getVideoWrappers() != null) {

            updateView(container);
        }
    }

    @Override
    public void onFailure() {

    }

    private RecyclerOnItemClickListener.OnItemClickListener onItemClickListener = new RecyclerOnItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

            VideoListFragment.this.onSelect(position);
        }
    };
}

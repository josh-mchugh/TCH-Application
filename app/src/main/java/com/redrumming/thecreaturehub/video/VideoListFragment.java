package com.redrumming.thecreaturehub.video;

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
public class VideoListFragment extends Fragment implements VideoAsyncListener{

    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

    private VideoContainer videoContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.video_list_fragment, container, false);

        Channel channel = new Channel();
        channel.setChannelName(getActivity().getResources().getStringArray(R.array.channel_names)[0]);
        channel.setChannelId(getActivity().getResources().getStringArray(R.array.channel_ids)[0]);

        int imageResource = this.getActivity().getResources().getIdentifier(
                getActivity().getResources().getStringArray(R.array.channel_display_icons)[0],
                "drawable", this.getActivity().getApplicationContext().getPackageName());
        Drawable displayIcon = this.getActivity().getResources().getDrawable(imageResource);
        channel.setDisplayIcon(displayIcon);

        setup(channel);

        recyclerView = (RecyclerView) v.findViewById(R.id.video_recycler_list);
        recyclerView.setLayoutManager(linearLayoutManager);

        videoContainer = new VideoContainer();
        videoContainer.setChannel(channel);
        VideoRecyclerAdapter adapter = new VideoRecyclerAdapter(videoContainer);
        recyclerView.setAdapter(adapter);

        swipeRefresh = (SwipeRefreshLayout) v.findViewById(R.id.video_swipe_refresh);
        swipeRefresh.setOnRefreshListener(onRefreshListener);

        recyclerView.addOnScrollListener(endlessScrollListener);


        return v;
    }

    public void setup(Channel channel){

        VideoContainer container = new VideoContainer();
        container.setChannel(channel);

        VideoAsync async = new VideoAsync(getActivity(), this);
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
    public void onSuccess(VideoContainer container) {

        this.videoContainer.getVideoWrappers().addAll(container.getVideoWrappers());
        this.videoContainer.setPageToken(container.getPageToken());
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
            Log.d(this.getClass().getName(), "On load more.");
        }
    };
}

package com.redrumming.thecreaturehub.video;

import android.app.Activity;
import android.graphics.drawable.Drawable;
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
public class VideoListFragment extends Fragment implements VideoAsyncListener{

    private RecyclerView recyclerView;
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        videoContainer = new VideoContainer();
        videoContainer.setChannel(channel);
        VideoRecyclerAdapter adapter = new VideoRecyclerAdapter(videoContainer);
        recyclerView.setAdapter(adapter);

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

        this.videoContainer.getVideos().addAll(container.getVideos());
        this.recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onFailure() {

    }
}

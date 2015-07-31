package com.redrumming.thecreaturehub.playlist;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.Channel;


/**
 *
 */
public class PlaylistListFragment extends Fragment {

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

        return view;
    }

    private void setup(Channel channel){

        PlaylistContainer container = new PlaylistContainer();
        container.setChannel(channel);

        PlaylistAsync async = new PlaylistAsync(getActivity());
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
}

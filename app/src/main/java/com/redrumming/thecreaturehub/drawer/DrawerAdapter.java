package com.redrumming.thecreaturehub.drawer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Created by RedRumming on 7/19/2015.
 */
public class DrawerAdapter extends ArrayAdapter<Channel> {

    private Activity context;
    private List<Channel> channels = new ArrayList<Channel>();

    public DrawerAdapter(Activity context, List<Channel> channels){

        super(context, R.layout.drawer_fragment, channels);

        this.context = context;
        this.channels = channels;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder viewHolder;
        Channel channel = channels.get(position);

        if (row == null) {

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.drawer_channel_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.displayIcon = (ImageView) row.findViewById(R.id.channel_display_icon);
            viewHolder.channelName = (TextView) row.findViewById(R.id.channel_display_name);

            row.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder) row.getTag();
        }

        viewHolder.displayIcon.setImageDrawable(channel.getDisplayIcon());
        viewHolder.channelName.setText(channel.getChannelName());

        return row;
    }

    private static class ViewHolder{
        ImageView displayIcon;
        TextView channelName;
    }
}

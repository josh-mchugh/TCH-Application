package com.redrumming.thecreaturehub;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.redrumming.thecreaturehub.channel.ChannelItem;
import com.redrumming.thecreaturehub.channel.ChannelsAsync;
import com.redrumming.thecreaturehub.channel.ChannelsAsyncListener;
import com.redrumming.thecreaturehub.channel.ChannelsContainer;

import java.util.List;

/**
 * Created by ME on 11/13/2015.
 */
public class SplashScreenActivity extends Activity implements ChannelsAsyncListener{

    private String[] channelIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        channelIds = getResources().getStringArray(R.array.channel_ids);

        ChannelsAsync async = new ChannelsAsync(this, this);
        async.execute(channelIds);
    }

    @Override
    public void onSuccess(List<ChannelItem> channels) {

        ChannelsContainer.getInstance().setChannels(channels);

        Intent intent = new Intent(SplashScreenActivity.this, EntryActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailure() {

        AlertDialog.Builder retryDialog = new AlertDialog.Builder(this);
        retryDialog.setTitle("Unable to retrieve data.");
        retryDialog.setMessage("Please check your internet connection and retry.");
        retryDialog.setCancelable(false);

        retryDialog.setPositiveButton("Retry",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ChannelsAsync async = new ChannelsAsync(SplashScreenActivity.this, SplashScreenActivity.this);
                        async.execute(channelIds);
                    }
                });

        retryDialog.setNegativeButton("Close",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }
                });

        retryDialog.create();
        retryDialog.show();
    }
}

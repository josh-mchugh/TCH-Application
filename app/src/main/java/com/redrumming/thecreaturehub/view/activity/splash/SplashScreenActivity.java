package com.redrumming.thecreaturehub.view.activity.splash;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.redrumming.thecreaturehub.view.activity.main.EntryActivity;
import com.redrumming.thecreaturehub.R;


/**
 * Created by ME on 11/13/2015.
 */
public class SplashScreenActivity extends Activity implements SplashScreenView {

    private SplashScreenPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        presenter = new SplashScreenPresenterImpl(this);
        presenter.fetchChannelData(getResources().getStringArray(R.array.channel_ids));
    }

    @Override
    public void displayNextActivity() {

        Intent intent = new Intent(SplashScreenActivity.this, EntryActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void displayFailureAlert() {

        AlertDialog.Builder retryDialog = new AlertDialog.Builder(this);
        retryDialog.setTitle(R.string.splash_screen_alert_title);
        retryDialog.setMessage(R.string.splash_screen_alert_message);
        retryDialog.setCancelable(false);

        retryDialog.setPositiveButton(R.string.splash_screen_alert_positive,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        presenter.fetchChannelData(getResources().getStringArray(R.array.channel_ids));
                    }
                });

        retryDialog.setNegativeButton(R.string.splash_screen_alert_negative,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }
                });

        retryDialog.create();
        retryDialog.show();
    }

    @Override
    public Context getContext() {

        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }
}

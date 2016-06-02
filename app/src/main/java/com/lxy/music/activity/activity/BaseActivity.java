package com.lxy.music.activity.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.ConnectionService;

import com.lxy.music.R;
import com.lxy.music.activity.service.PlayService;

public class BaseActivity extends FragmentActivity {

    public PlayService mPlayService;
    public PlayService.MyBinder binder;
    public boolean isBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (PlayService.MyBinder) service;
            mPlayService = binder.getPlayService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mPlayService = null;
            isBind = false;
        }
    };


    public void bindPlayService() {
        if (!isBind) {
            Intent intent = new Intent(BaseActivity.this, PlayService.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
            isBind = true;
        }

    }

    public void nuBindPlayService() {
        if (isBind) {
            unbindService(connection);
            isBind = false;
        }
    }

}

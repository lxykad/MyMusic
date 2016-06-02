package com.lxy.music.activity.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import com.lxy.music.R;
import com.lxy.music.activity.activity.MainActivity;
import com.lxy.music.activity.bean.MusicBean;
import com.lxy.music.activity.utils.Tool;

import java.io.IOException;
import java.util.ArrayList;

public class PlayService extends Service {

    private MediaPlayer mPlayer;
    private int mCurrentPosition;//当前播放的哪首
    private ArrayList<MusicBean> mList;

    public PlayService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = new MediaPlayer();
        mList = Tool.getMusicList(this);
        Notification notification = new Notification.Builder(getApplicationContext()).setContentTitle("我是你的音乐播放器").setSmallIcon(R.mipmap.splash_01).setContentText("歌曲").build();
        Notification notice=new Notification();
        notice.iconLevel=R.mipmap.splash_01;
        notice.tickerText="220";


        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, 0);


        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //nm.notify(R.mipmap.splash_01,intent);//通知栏 可滑动删除

        startForeground(1, notification);//开启前台服务
    }


    public class MyBinder extends Binder {

        public PlayService getPlayService() {
            return PlayService.this;
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public void play(int index) {
        MusicBean bean = mList.get(index);
        mPlayer.reset();
        try {
            mPlayer.setDataSource(this, Uri.parse(bean.url));
            mPlayer.prepare();
            start();
            mCurrentPosition = index;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause() {

    }

    public void next() {

    }

    public void pre() {

    }

    //重新播放
    public void start() {
        if (!mPlayer.isPlaying() && mPlayer != null) {
            System.out.println("858585666666666666666");
            mPlayer.start();
        }
    }

}

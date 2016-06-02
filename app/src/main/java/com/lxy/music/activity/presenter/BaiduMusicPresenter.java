package com.lxy.music.activity.presenter;

import android.os.Handler;
import android.os.Message;

import com.lxy.music.activity.Response.BaiduMusicResponse;
import com.lxy.music.activity.bean.MusicBean;
import com.lxy.music.activity.bean.OnLineMusicBean;
import com.lxy.music.activity.httpHelper.Constant;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/12/16 0016.
 */
public class BaiduMusicPresenter {

    private ArrayList<OnLineMusicBean> musicList = new ArrayList<OnLineMusicBean>();
    private final int SUCCESS = 0;
    private final int FAILURE = 1;

    public void getMusicList(final String url, final BaiduMusicResponse response) {



        final Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                switch (msg.what) {
                    case FAILURE:
                        response.onFailure();
                        break;
                    case SUCCESS:
                        ArrayList list= (ArrayList) msg.obj;

                        //获取音乐成功
                        response.onSuccess(list);
                        break;
                }
                //获取音乐完成
                response.onFinish();
            }

        };

        //开始获取音乐
        response.onStart();

        new Thread() {
            @Override
            public void run() {

                Message message = handler.obtainMessage();


                Document document = null;
                try {
                    document = Jsoup.connect(url).userAgent(Constant.USER_AGENT).timeout(5000).get();
                    Elements songTitles = document.select("span.song-title");
                    Elements singerNames = document.select("span.author_list");

                    for(int i=0;i<songTitles.size();i++){
                        OnLineMusicBean bean = new OnLineMusicBean();
                        Elements urls = songTitles.get(i).getElementsByTag("a");
                        bean.url=urls.get(0).attr("href");
                        bean.music_name=urls.get(0).toString();

                        Elements singerEl = singerNames.get(i).getElementsByTag("a");
                        bean.singer_name=singerEl.get(0).toString();

                        musicList.add(bean);
                    }
                    message.what = SUCCESS;
                    message.obj = musicList;

                } catch (IOException e) {
                    e.printStackTrace();
                    message.what = FAILURE;
                }
                handler.sendMessage(message);
            }
        }.start();

    }

}

package com.lxy.music.activity.Response;

import com.lxy.music.activity.bean.MusicBean;
import com.lxy.music.activity.bean.OnLineMusicBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/12/16 0016.
 */
public interface BaiduMusicResponse {

    public void onStart();
    public void onSuccess(ArrayList<OnLineMusicBean> list);
    public void onFailure();
    public void onFinish();
}

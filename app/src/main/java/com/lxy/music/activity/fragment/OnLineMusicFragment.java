package com.lxy.music.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.music.R;
import com.lxy.music.activity.Response.BaiduMusicResponse;
import com.lxy.music.activity.bean.MusicBean;
import com.lxy.music.activity.bean.OnLineMusicBean;
import com.lxy.music.activity.presenter.BaiduMusicPresenter;

import java.util.ArrayList;

public class OnLineMusicFragment extends Fragment {

    private BaiduMusicPresenter mPresenter;
    private String mBaiduUrl="http://music.baidu.com/top/dayhot/?pst=shouyeTop";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_on_line_music, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter=new BaiduMusicPresenter();
        mPresenter.getMusicList(mBaiduUrl,new BaiduMusicResponse() {
            @Override
            public void onStart() {
                System.out.println("获取音乐开始==========");
            }

            @Override
            public void onSuccess(ArrayList<OnLineMusicBean> list) {
                System.out.println("获取音乐成功==========");
                for (int i=0;i<list.size();i++){
                    OnLineMusicBean bean=list.get(i);


                    System.out.println("第"+i+"首音乐====="+bean.music_name);
                }
            }

            @Override
            public void onFailure() {
                System.out.println("获取音乐失败==========");
            }

            @Override
            public void onFinish() {
                System.out.println("获取音乐完成==========");
            }
        });

    }
}

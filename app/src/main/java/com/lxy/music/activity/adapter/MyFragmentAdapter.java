package com.lxy.music.activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by LXY on 2015/12/13.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mList;

    public MyFragmentAdapter(FragmentManager fm,ArrayList<Fragment> list) {
        super(fm);
        mList=list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}

package com.lxy.music.activity.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxy.music.R;
import com.lxy.music.activity.adapter.MyFragmentAdapter;
import com.lxy.music.activity.fragment.MyCollectMusicFragment;
import com.lxy.music.activity.fragment.MyMusicFragment;
import com.lxy.music.activity.fragment.OnLineMusicFragment;
import com.lxy.music.activity.utils.Tool;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragmentList;
    private MyMusicFragment mMyMusicFragment;
    private OnLineMusicFragment mOnLineMusicFragment;
    private MyCollectMusicFragment mMyCollectMusicFragment;
    private MyFragmentAdapter mAdapter;
    private FragmentManager mFm;
    private View mIndicator;
    private RelativeLayout.LayoutParams mParams;
    private int mIndicatorWidth;
    private TextView mTvLeft;
    private TextView mTvMid;
    private TextView mTvRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mIndicator = findViewById(R.id.indicator);
        mParams = (RelativeLayout.LayoutParams) mIndicator.getLayoutParams();

        mFragmentList = new ArrayList<>();

        mMyMusicFragment = new MyMusicFragment();
        mOnLineMusicFragment = new OnLineMusicFragment();
        mMyCollectMusicFragment = new MyCollectMusicFragment();
        mFm = getSupportFragmentManager();
        mFragmentList.add(mMyMusicFragment);
        mFragmentList.add(mOnLineMusicFragment);
        mFragmentList.add(mMyCollectMusicFragment);

        mAdapter = new MyFragmentAdapter(mFm, mFragmentList);
        mTvLeft= (TextView) findViewById(R.id.tv_left);
        mTvMid= (TextView) findViewById(R.id.tv_mid);
        mTvRight= (TextView) findViewById(R.id.tv_rigint);
        mTvLeft.setOnClickListener(this);
        mTvMid.setOnClickListener(this);
        mTvRight.setOnClickListener(this);
    }

    private void initData() {
        mViewPager.setAdapter(mAdapter);
        mIndicatorWidth = Tool.getScreenWidth(getApplicationContext()) / 3;
        mParams.height=5;
        mParams.width=mIndicatorWidth;
        mIndicator.setLayoutParams(mParams);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mParams.leftMargin = (int) (position * mIndicatorWidth + positionOffset * mIndicatorWidth);
                mIndicator.setLayoutParams(mParams);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_left:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.tv_mid:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.tv_rigint:
                mViewPager.setCurrentItem(2);
                break;
        }

    }
}

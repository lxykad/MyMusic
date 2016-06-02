package com.lxy.music.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lxy.music.R;
import com.lxy.music.activity.activity.MainActivity;
import com.lxy.music.activity.adapter.MusicAdapter;
import com.lxy.music.activity.bean.MusicBean;
import com.lxy.music.activity.ui.SearchIndex;
import com.lxy.music.activity.utils.Tool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MyMusicFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private MusicAdapter mAdapter;
    private ArrayList<MusicBean> mList;
    private MainActivity mActivity;
    private SearchIndex mIndex;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mActivity = (MainActivity) getActivity();
        mActivity.bindPlayService();

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_my_music, null);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIndex= (SearchIndex) view.findViewById(R.id.letter_index);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView = getListView();
        mListView.setOnItemClickListener(this);
        mList = Tool.getMusicList(getActivity());
        Collections.sort(mList);

        for (int i=0;i<mList.size();i++){
            System.out.println("mlist=========="+mList.get(i).singer_name);
        }
        mAdapter = new MusicAdapter(getContext(), mList);

        setListAdapter(mAdapter);

        mIndex.setOnLetterChangeListener(new SearchIndex.OnLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {


            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mActivity.nuBindPlayService();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mActivity.mPlayService.play(position);

    }
}

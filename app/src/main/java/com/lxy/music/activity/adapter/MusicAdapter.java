package com.lxy.music.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lxy.music.R;
import com.lxy.music.activity.bean.MusicBean;

import java.util.ArrayList;

/**
 * Created by LXY on 2015/12/13.
 */
public class MusicAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<MusicBean> mList = new ArrayList<>();

    public MusicAdapter(Context context, ArrayList<MusicBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public MusicBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_my_music, null);
            holder.singerName = (TextView) convertView.findViewById(R.id.singer_name);
            holder.musicName = (TextView) convertView.findViewById(R.id.music_name);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MusicBean bean=mList.get(i);
        holder.musicName.setText(bean.music_name);
        holder.singerName.setText(bean.singer_name);

        return convertView;
    }


    class ViewHolder {

        public TextView singerName;
        public TextView musicName;
    }
}

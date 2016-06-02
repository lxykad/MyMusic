package com.lxy.music.activity.bean;

import com.lxy.music.activity.utils.PinYinUtil;

/**
 * Created by LXY on 2015/12/13.
 */
public class MusicBean implements Comparable<MusicBean> {

    public long id;
    public String file_name;
    public String music_name;//歌曲名
    public String singer_name;//歌手
    public String singer_pinyin;
    public String zhuanji;//专辑
    public long zhuanji_id;
    public long time;//时长
    public String size;//大小
    public String url;//歌曲路径
    public boolean isMusic;//是否是音乐
    public String type;//音乐格式
    public String year;//年代

    public MusicBean(String singer_name){
        singer_pinyin= PinYinUtil.toPinyin(singer_name);
    }

    @Override
    public int compareTo(MusicBean another) {

        return this.singer_pinyin.compareTo(another.singer_pinyin);
    }
}

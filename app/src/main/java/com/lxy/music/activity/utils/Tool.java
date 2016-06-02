package com.lxy.music.activity.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.DisplayMetrics;

import com.lxy.music.activity.bean.MusicBean;

import java.util.ArrayList;

/**
 * Created by LXY on 2015/12/13.
 */
public class Tool {

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 获取sd卡歌曲列表
     */
    public static ArrayList<MusicBean> getMusicList(Context context) {

        ArrayList<MusicBean> list = null;

        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.TITLE,
                        MediaStore.Audio.Media.DURATION,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.YEAR,
                        MediaStore.Audio.Media.MIME_TYPE,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.DATA},
                MediaStore.Audio.Media.MIME_TYPE + "=? or "
                        + MediaStore.Audio.Media.MIME_TYPE + "=?",
                new String[]{"audio/mpeg", "audio/x-ms-wma"}, null);

        list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            MusicBean bean = null;
            do {
                bean = new MusicBean(cursor.getString(2));
                // 文件名
                bean.file_name = cursor.getString(1);
                // 歌曲名
                bean.music_name = cursor.getString(2);
                // 时长
                bean.time = cursor.getInt(3);
                // 歌手名
                bean.singer_name = cursor.getString(4);
                // 专辑名
                bean.zhuanji = cursor.getString(5);
                // 年代
                if (cursor.getString(6) != null) {
                    bean.year = cursor.getString(6);
                } else {
                    bean.year = "未知";
                }
                // 歌曲格式
                if ("audio/mpeg".equals(cursor.getString(7).trim())) {
                    bean.type = "mp3";
                } else if ("audio/x-ms-wma".equals(cursor.getString(7).trim())) {
                    bean.type = "wma";
                }
                // 文件大小
                if (cursor.getString(8) != null) {
                    float size = cursor.getInt(8) / 1024f / 1024f;
                    bean.size = ((size + "").substring(0, 4) + "M");
                } else {
                    bean.size = "未知";
                }
                // 文件路径
                if (cursor.getString(9) != null) {
                    bean.url = cursor.getString(9);
                }
                list.add(bean);


            } while (cursor.moveToNext());

            cursor.close();

        }

        return list;
    }


}

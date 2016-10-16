package com.kongshanxinyu.cloudreading.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.kongshanxinyu.cloudreading.bean.Music;

import java.util.List;

/**
 * Created by Steve on 16/10/8.
 * E-mail: singleframe@aliyun.com
 */
public class MusicUtil {

    public static void scanLocalMusic(Context context, List<Music> list){
        list.clear();
        Cursor cursor=context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (cursor!=null){
            while (cursor.moveToNext()){
                if (cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC))==0){
                    continue;
                }
                //The unique ID for a row.
                long id=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                //The title of the content
                String title=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                //The artist who created the audio file, if any
                String artist=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                //The album the audio file is from, if any
                String album=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                //The id of the album the audio file is from, if any
                long album_id=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                //The duration of the audio file, in ms
                long duration=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                //The display name of the file
                String display_name=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                //The data stream for the file
                String uri=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                //The size of the file in bytes
                long size =cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
                //The year the audio file was recorded, if any
                int year=cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.YEAR));

                Music music=new Music(Music.Type.LOCAL,id,title,artist,album,duration,uri,getCoverUri(context,album_id),null,display_name,size,year);
                list.add(music);
            }
            cursor.close();
        }

    }

    public static String getCoverUri(Context context,long albumId){
        Cursor cursor=context.getContentResolver().query(Uri.parse("content://media/external/audio/albums"+albumId),new String[]{"album_art"},null,null,null);
        String uri="";
        if (cursor!=null){
            cursor.moveToNext();
            uri=cursor.getString(0);
            cursor.close();
        }
        return uri;
    }

}

package com.kongshanxinyu.cloudreading.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Steve on 16/10/11.
 * E-mail: singleframe@aliyun.com
 */
public class PrefUtil {

    public static long getCurrentPlayingSongId(){
        return getPreference().getLong(ConstantUtil.CURRENT_PLAYING_SONG_ID,0);
    }

    public static void setCurrentPlayingSongId(long id){
        getEditor().putLong(ConstantUtil.CURRENT_PLAYING_SONG_ID,id).apply();
    }

    public static int getPlayingMode(){
        return getPreference().getInt(ConstantUtil.PLAYING_MODE,0);
    }
    public static void setPlayingMode(int mode){
        getEditor().putInt(ConstantUtil.PLAYING_MODE,mode).apply();
    }

    public static SharedPreferences.Editor getEditor(){
        SharedPreferences preferences=ContextUtil.getAppContext().getSharedPreferences("default", Context.MODE_PRIVATE);
        return preferences.edit();
    }
    public static SharedPreferences getPreference(){
        SharedPreferences preferences=ContextUtil.getAppContext().getSharedPreferences("default", Context.MODE_PRIVATE);
        return preferences;
    }

}

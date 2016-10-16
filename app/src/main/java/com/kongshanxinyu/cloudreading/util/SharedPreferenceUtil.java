package com.kongshanxinyu.cloudreading.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Steve on 16/9/13.
 * E-mail: singleframe@aliyun.com
 */
public class SharedPreferenceUtil {

    private static final String MUSIC_ID = "music_id";
    private static final String PLAY_MODE = "play_mode";
    private static final String SPLASH_URL = "splash_url";
    private static final String NIGHT_MODE = "night_mode";
    private static final String GPRS_PLAY = "gprs_play";
    private static final String GPRS_DOWNLOAD = "gprs_download";

    private static Context context=ContextUtil.getAppContext();

    public static long getCurrSongID(){
        return getPreference().getLong(MUSIC_ID,-1);
    }
    public static void saveCurrSongID(long id){
        getPreference().edit().putLong(MUSIC_ID,id).apply();
    }

    public static int getPlayMode(){
        return getPreference().getInt(PLAY_MODE,0);
    }
    public static void savePlayMode(int mode){
        getPreference().edit().putInt(PLAY_MODE,mode).apply();
    }

    public static String getSplashUrl(){
        return getPreference().getString(SPLASH_URL,"");
    }
    public static void saveSplashUrl(String url){
        getPreference().edit().putString(SPLASH_URL,url).apply();
    }

    public static boolean getNightModeState(){
        return getPreference().getBoolean(NIGHT_MODE,false);
    }
    public static void saveNightModeState(boolean nightMode){
        getPreference().edit().putBoolean(NIGHT_MODE,nightMode).apply();
    }

    public static boolean getGPRSPlayState(){
        return getPreference().getBoolean(GPRS_PLAY,false);
    }
    public static void saveGPRSPlayState(boolean state){
        getPreference().edit().putBoolean(GPRS_PLAY,state).apply();
    }

    public static boolean getGPRSDownloadState(){
        return getPreference().getBoolean(GPRS_DOWNLOAD,false);
    }
    public static void saveGPRSDownloadState(boolean state){
        getPreference().edit().putBoolean(GPRS_DOWNLOAD,state).apply();
    }

    private static SharedPreferences getPreference(){
        return context.getSharedPreferences("AppPref",Context.MODE_APPEND);
    }

}

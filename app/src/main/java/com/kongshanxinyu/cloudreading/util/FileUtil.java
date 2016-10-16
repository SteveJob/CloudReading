package com.kongshanxinyu.cloudreading.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by Steve on 16/9/13.
 * E-mail: singleframe@aliyun.com
 */
public class FileUtil {

    public static final String ROOT=Environment.getExternalStorageDirectory().getAbsolutePath();

    public static String getAppRoot(){
        String appRoot=ROOT+"/CloudReading/";
        File appRootFile=new File(appRoot);
        mkdir(appRootFile);
        return appRoot;
    }

    public static String getSplashPicDir(){
        String splashPic=getAppRoot()+"Picture/splash";
        File splashPicFile=new File(splashPic);
        mkdir(splashPicFile);
        return splashPic;
    }

    public static String getSongPicDir(){
        String songDir=getAppRoot()+"Music/";
        File songFile=new File(songDir);
        mkdir(songFile);
        return songDir;
    }

    public static String getLyricDir(){
        String lyricDir=getAppRoot()+"Lyric/";
        File lyricFile=new File(lyricDir);
        mkdir(lyricFile);
        return lyricDir;
    }

    public static String getLogDir(){
        String logDir=getAppRoot()+"log/";
        File file=new File(logDir);
        mkdir(file);
        return logDir;
    }

    public static void mkdir(File file){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            if (!file.exists()){
                file.mkdirs();
            }
        }
    }

    public static void writeToFile(File file,String content){

    }

}

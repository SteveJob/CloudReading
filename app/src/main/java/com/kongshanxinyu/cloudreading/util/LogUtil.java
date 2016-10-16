package com.kongshanxinyu.cloudreading.util;

import android.util.Log;

import java.io.File;

/**
 * Created by Steve on 16/10/16.
 * E-mail: singleframe@aliyun.com
 */
public class LogUtil {

    private static final int VERBOSE=5;
    private static final int DEBUG=5;
    private static final int INFO=5;
    private static final int WARN=5;
    private static final int ERROR=5;

    private static final boolean isDebug=DebugUtil.isDebugMode();

    private static final String LOG_DIR=FileUtil.getLogDir();

    public static void v(String tag,String msg){
        if (isDebug){
            Log.v(tag,msg);
            write(tag,msg);
        }
    }
    public static void d(String tag,String msg){
        if (isDebug){
            Log.d(tag,msg);
            write(tag,msg);
        }
    }
    public static void i(String tag,String msg){
        if (isDebug){
            Log.i(tag,msg);
            write(tag,msg);
        }
    }
    public static void w(String tag,String msg){
        if (isDebug){
            Log.w(tag,msg);
            write(tag,msg);
        }
    }
    public static void e(String tag,String msg){
        if (isDebug){
            Log.e(tag,msg);
            write(tag,msg);
        }
    }

    private static void write(String tag,String msg){
        File file=new File(LOG_DIR,"log.txt");
        String content=SysUtil.getCurrentTime()+"("+tag+"):"+msg+"\n";
        FileUtil.writeToFile(file,content);
    }


}

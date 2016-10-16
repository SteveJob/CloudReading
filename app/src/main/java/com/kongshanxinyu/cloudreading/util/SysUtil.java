package com.kongshanxinyu.cloudreading.util;

import android.app.ActivityManager;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Steve on 16/9/11.
 * E-mail: singleframe@aliyun.com
 */
public class SysUtil {

    private static final String DATE_FORMATE="yyyy年MM月dd日HH时mm分ss秒";

    public static boolean hasServiceRunning(Context context,Class service){
        ActivityManager manager= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> list=manager.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo info:list){
            String serviceName=info.service.getClassName();
            if (service.getName().equals(serviceName)){
                return true;
            }
        }
        return false;
    }

    public static boolean hasActivityRunning(Context context,Class activity){

        return false;
    }

    public static long getMaxMemory(){
        return Runtime.getRuntime().maxMemory();
    }

    public static String getCurrentTime(){
        SimpleDateFormat format=new SimpleDateFormat(DATE_FORMATE, Locale.CHINA);
        return format.format(new Date());
    }

}

package com.kongshanxinyu.cloudreading.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Created by Steve on 16/10/16.
 * E-mail: singleframe@aliyun.com
 */
public class DebugUtil {

    public static boolean isDebugMode(){
        boolean isDebug=false;
        Context context=ContextUtil.getAppContext();
        try {
            ApplicationInfo info=context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            isDebug=info.metaData.getBoolean("debug");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return isDebug;
    }
}

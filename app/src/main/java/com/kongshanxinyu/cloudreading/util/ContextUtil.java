package com.kongshanxinyu.cloudreading.util;

import android.content.Context;

/**
 * Created by Steve on 16/9/11.
 * E-mail: singleframe@aliyun.com
 */
public class ContextUtil {
    private static Context context;

    public static Context getAppContext(){
        if (context==null) {
            throw new IllegalAccessError("应用尚未初始化完毕");
        }

        return context;
    }

    public static void setContext(Context context) {
        ContextUtil.context = context;
    }
}

package com.kongshanxinyu.cloudreading;

import android.app.Application;

import com.kongshanxinyu.cloudreading.util.ContextUtil;
import com.kongshanxinyu.cloudreading.util.SharedPreferenceUtil;

/**
 * Created by Steve on 16/9/10.
 * E-mail: singleframe@aliyun.com
 */
public class CloudApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ContextUtil.setContext(getApplicationContext());
        com.kongshanxinyu.multithread.util.ContextUtil.setContext(getApplicationContext());
        initOkHttpUtil();
//        SharedPreferenceUtil.init(getApplicationContext());
    }

    private void initOkHttpUtil() {
    }


}

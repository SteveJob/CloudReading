package com.kongshanxinyu.multithread.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kongshanxinyu.multithread.bean.FileBean;

/**
 * Created by Steve on 16/10/16.
 * E-mail: singleframe@aliyun.com
 */
public class DownloadService extends Service {

    public static final String ACTION_DOWNLOAD="action_download";
    public static final String ACTION_PAUSE="action_pause";

    private static final String TAG = "DownloadService";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action =intent.getAction();
        FileBean fileBean= (FileBean) intent.getSerializableExtra("file");
        switch (action){
            case ACTION_DOWNLOAD:
                Log.i(TAG,fileBean.getName());
                break ;
            case ACTION_PAUSE:
                Log.i(TAG,fileBean.getName());
                break ;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

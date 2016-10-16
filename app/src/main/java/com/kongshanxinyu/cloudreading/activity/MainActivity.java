package com.kongshanxinyu.cloudreading.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.widget.ImageView;

import com.kongshanxinyu.cloudreading.R;
import com.kongshanxinyu.cloudreading.debug.DebugActivity;
import com.kongshanxinyu.cloudreading.service.PlayService;
import com.kongshanxinyu.cloudreading.util.ContextUtil;
import com.kongshanxinyu.cloudreading.util.DebugUtil;
import com.kongshanxinyu.cloudreading.util.FileUtil;
import com.kongshanxinyu.cloudreading.util.SysUtil;

import java.io.File;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.ivSplash)
    private ImageView ivSplash;
    private PlayServiceConnection serviceConnection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (DebugUtil.isDebugMode()){
            startActivity(new Intent(this,DebugActivity.class));
        }else {
            checkService();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void setListener() {

    }

    private void checkService(){
        if(SysUtil.hasServiceRunning(ContextUtil.getAppContext(), PlayService.class)){
            startHomeActivity();
            finish();
        }else {
            setSplashImage();
            updateSplashImage();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(MainActivity.this,PlayService.class);
                    serviceConnection=new PlayServiceConnection();
                    bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
                }
            },1000);
        }
    }

    private void setSplashImage() {
        File file=new File(FileUtil.getSplashPicDir(),"splash.jpg");
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            ivSplash.setImageBitmap(bitmap);
        }
    }

    private void updateSplashImage() {
        //TODO

    }


    private void startHomeActivity() {
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    private class PlayServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PlayService playService=((PlayService.PBinder)service).getService();
            playService.scanMusicList();
            startHomeActivity();
            finish();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }


}

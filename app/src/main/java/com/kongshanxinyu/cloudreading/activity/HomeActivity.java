package com.kongshanxinyu.cloudreading.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.kongshanxinyu.cloudreading.R;
import com.kongshanxinyu.cloudreading.bean.Music;
import com.kongshanxinyu.cloudreading.listener.OnPlayingListener;
import com.kongshanxinyu.cloudreading.service.PlayService;
import com.kongshanxinyu.cloudreading.util.ConstantUtil;

/**
 * Created by Steve on 16/9/13.
 * E-mail: singleframe@aliyun.com
 */
public class HomeActivity extends BaseActivity implements OnPlayingListener {

    private PlayService playService;
    private PlayServiceConnection playServiceConnection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bindService(new Intent(this,PlayService.class),playServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.hasExtra(ConstantUtil.FROM_NOTIFICATION_TO_HOMEACTIVITY)){
            showPlayFragment();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(playServiceConnection);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onPlayingProgress(int progress) {

    }

    @Override
    public void onChangeMusic(Music music) {

    }

    @Override
    public void onPausePlaying() {

    }

    @Override
    public void onStartPlaying() {

    }

    @Override
    public void onContinuePlaying() {

    }







    private void showPlayFragment() {

    }

    private class PlayServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }


}

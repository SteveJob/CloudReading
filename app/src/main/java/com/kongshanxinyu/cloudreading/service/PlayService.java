package com.kongshanxinyu.cloudreading.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.kongshanxinyu.cloudreading.R;
import com.kongshanxinyu.cloudreading.activity.BaseActivity;
import com.kongshanxinyu.cloudreading.activity.MainActivity;
import com.kongshanxinyu.cloudreading.bean.Music;
import com.kongshanxinyu.cloudreading.cache.PicLoader;
import com.kongshanxinyu.cloudreading.enumpkg.PlayMode;
import com.kongshanxinyu.cloudreading.listener.OnPlayingListener;
import com.kongshanxinyu.cloudreading.receiver.NoisyReceiver;
import com.kongshanxinyu.cloudreading.util.ConstantUtil;
import com.kongshanxinyu.cloudreading.util.ContextUtil;
import com.kongshanxinyu.cloudreading.util.MusicUtil;
import com.kongshanxinyu.cloudreading.util.PrefUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steve on 16/9/11.
 * E-mail: singleframe@aliyun.com
 */
public class PlayService extends Service implements MediaPlayer.OnCompletionListener,AudioManager.OnAudioFocusChangeListener {

    private static final List<BaseActivity> mBaseActivityStack=new ArrayList<>();
    private List<Music> localMusicList=new ArrayList<>();

    private MediaPlayer mediaPlayer=new MediaPlayer();
    private AudioManager audioManager;
    private OnPlayingListener onPlayingListener;
    private NotificationManager notificationManager;

    private NoisyReceiver noisyReceiver;

    private Handler handler=new Handler();
    private boolean isPlaying;

    private Music playingMusic;
    private int posInLocalMusic;

    private UpdateProgressRunnable progressRunnable;


    @Override
    public void onCreate() {
        super.onCreate();
        audioManager= (AudioManager) getSystemService(AUDIO_SERVICE);
        notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mediaPlayer.setOnCompletionListener(this);
        progressRunnable=new UpdateProgressRunnable();
        noisyReceiver=new NoisyReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction()==null){
            return START_STICKY;
        }
        switch (intent.getAction()){
            case ConstantUtil.ACTION_MEDIA_PLAY_PAUSE:
                pauseMusic();
                break;
            case ConstantUtil.ACTION_MEDIA_PLAY_NEXT:
                playNext();
                break;
            case ConstantUtil.ACTION_MEDIA_PLAY_PRE:
                playPre();
                break;
        }
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        onPlayingListener=null;
        return super.onUnbind(intent);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        playNext();
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange){
            case AudioManager.AUDIOFOCUS_LOSS:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                if (isPlaying){
                    pauseMusic();
                }

        }
    }






    public boolean isPause(){
        return !isPlaying&&mediaPlayer!=null;
    }

    public boolean isPlaying(){
        return mediaPlayer!=null&&mediaPlayer.isPlaying();
    }

    public void scanMusicList(){
        MusicUtil.scanLocalMusic(ContextUtil.getAppContext(),localMusicList);
        if (localMusicList==null){
            return;
        }
        updatePosInLocalMusicList();
        if (playingMusic==null){
            playingMusic=localMusicList.get(posInLocalMusic);
        }
    }

    public void updatePosInLocalMusicList(){
        int pos=0;
        for (int i=0;i<localMusicList.size();i++){
            if (localMusicList.get(i).getId()== PrefUtil.getCurrentPlayingSongId()){
                pos=i;
                return;
            }
        }
        posInLocalMusic=pos;
        PrefUtil.setCurrentPlayingSongId(localMusicList.get(pos).getId());
    }

    public static void addToStack(BaseActivity activity){
        mBaseActivityStack.add(activity);
    }

    public static void removeFromStack(BaseActivity activity){
        mBaseActivityStack.remove(activity);
    }

    public void playMusic(Music music){
        playingMusic=music;
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(music.getUri());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        isPlaying=true;
        //更新播放进度条
        handler.post(progressRunnable);
        //更新通知栏
        notificationManager.cancel(ConstantUtil.NOTIFICATION_FRONT_SERVICE);
        Notification notification=updateNotification(music);
        audioManager.requestAudioFocus(this,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);
        registerReceiver(noisyReceiver,new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY));
        startForeground(ConstantUtil.NOTIFICATION_FRONT_SERVICE,notification);
        onPlayingListener.onChangeMusic(music);
    }

    private Notification updateNotification(Music music) {
        String title=music.getFileName();
        String text=(music.getAlbum()==null?"未知":music.getAlbum())+"-"+(music.getArtist()==null?"":music.getArtist());
        Bitmap bitmap=music.getType()== Music.Type.LOCAL? PicLoader.getInstance().loadThumbnailPic(music.getCoverUri()):music.getCover();
        Intent intent=new Intent(this, MainActivity.class);
        intent.putExtra(ConstantUtil.FROM_NOTIFICATION_TO_HOMEACTIVITY,true);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
        Notification.Builder builder=new Notification.Builder(this);
        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(bitmap);
        builder.setSmallIcon(R.mipmap.ic_notification);
        return builder.getNotification();
    }

    public void playNext(){
        PlayMode mode=PlayMode.modeOf(PrefUtil.getPlayingMode());
        switch (mode){
            case SERIAL:
                break;
            case LOOP:
                break;
            case RANDOM:
                break;
        }
    }

    public void playPre(){

    }

    public void pauseMusic(){
        if (isPlaying){
            mediaPlayer.pause();
            isPlaying=false;
            stopForeground(true);
            audioManager.abandonAudioFocus(this);
            unregisterReceiver(noisyReceiver);
            onPlayingListener.onPausePlaying();
        }
    }

    public void continuePlay(){
        if (!isPlaying){
            mediaPlayer.start();
            isPlaying=true;
            handler.post(progressRunnable);
            registerReceiver(noisyReceiver,new IntentFilter(ConstantUtil.ACTION_MEDIA_PLAY_PAUSE));
        }
    }

    public void seekTo(int lo){
        if (mediaPlayer!=null){
            mediaPlayer.seekTo(lo);
            onPlayingListener.onPlayingProgress(lo);
        }
    }






    public void setOnPlayingListener(OnPlayingListener listener){
        this.onPlayingListener=listener;
    }

    public class PBinder extends Binder{
        public PlayService getService(){
            return PlayService.this;
        }
    }

    public class UpdateProgressRunnable implements Runnable{
        @Override
        public void run() {
            if (isPlaying){
                onPlayingListener.onPlayingProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(progressRunnable,50);
            }
        }
    }
}

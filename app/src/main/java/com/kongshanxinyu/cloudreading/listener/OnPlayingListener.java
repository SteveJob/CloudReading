package com.kongshanxinyu.cloudreading.listener;

import com.kongshanxinyu.cloudreading.bean.Music;

/**
 * Created by Steve on 16/10/11.
 * E-mail: singleframe@aliyun.com
 */
public interface OnPlayingListener {
    void onPlayingProgress(int progress);
    void onChangeMusic(Music music);
    void onPausePlaying();
    void onStartPlaying();
    void onContinuePlaying();
}

package com.kongshanxinyu.cloudreading.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.kongshanxinyu.cloudreading.service.PlayService;
import com.kongshanxinyu.cloudreading.util.ConstantUtil;

/**
 * Created by Steve on 16/10/11.
 * E-mail: singleframe@aliyun.com
 */
public class NoisyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i=new Intent(context, PlayService.class);
        i.setAction(ConstantUtil.ACTION_MEDIA_PLAY_PAUSE);
        context.startService(i);
    }
}

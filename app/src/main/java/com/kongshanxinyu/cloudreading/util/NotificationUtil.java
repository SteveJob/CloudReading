package com.kongshanxinyu.cloudreading.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.kongshanxinyu.cloudreading.R;
import com.kongshanxinyu.cloudreading.activity.HomeActivity;
import com.kongshanxinyu.cloudreading.bean.Music;

/**
 * Created by Steve on 16/10/13.
 * E-mail: singleframe@aliyun.com
 */
public class NotificationUtil {

    private NotificationManager notificationManager;
    private Context context;

    public NotificationUtil(Context context) {
        this.context=context;
        notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void showNotification(Music music){
        Notification notification=new Notification();
        Intent intent=new Intent(context, HomeActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);
        notification.contentIntent=pendingIntent;
        RemoteViews remoteViews=new RemoteViews(context.getPackageName(), R.layout.notifi_play);
        notification.contentView=remoteViews;


    }

    public void showNotification(){

    }


}

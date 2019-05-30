package com.vivo.a11085273.servicetest;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class ForeService extends Service {

    private static final String CHANNEL_ID = "fore_Service";

    public ForeService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //处理任务

        return START_STICKY;
    }
    @SuppressLint("CommitPrefEdits")
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("foreground", "onCreate");
        //如果API在26以上即版本为O则调用startForefround()方法启动服务
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            setForegroundService();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     *通过通知启动服务
     */
    @TargetApi(Build.VERSION_CODES.O)
    public void  setForegroundService()
    {
        //设定的通知渠道名称
        String channelName = "fore";
        //设置通知的重要程度
        int importance = NotificationManager.IMPORTANCE_HIGH;
        //向系统注册通知渠道，注册后不能改变重要性以及其他通知行为
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //构建通知渠道
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
        notificationManager.createNotificationChannel(channel);

        //在创建的通知渠道上发送通知
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground) //设置通知图标
                .setContentTitle("前台服务标题")//设置通知标题
                .setContentText("前台服务通知内容")//设置通知内容
                .setAutoCancel(true) //用户触摸时，自动关闭
                .setOngoing(true);//设置处于运行状态
        //将服务置于启动状态 NOTIFICATION_ID指的是创建的通知的ID
        startForeground(101,builder.build());
    }
}

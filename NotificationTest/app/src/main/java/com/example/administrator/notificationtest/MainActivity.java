package com.example.administrator.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // Create the NotificationChannel
                    CharSequence name = "name";
                    String description = "description";
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel mChannel = new NotificationChannel("id", name, importance);
                    mChannel.setDescription(description);
                    // Register the channel with the system; you can't change the importance
                    // or other notification behaviors after this
                    NotificationManager notificationManager = (NotificationManager) getSystemService(
                            NOTIFICATION_SERVICE);
                    notificationManager.createNotificationChannel(mChannel);
                }
            }
        });
    }


//    private void sendNotification() {
//        //获取NotificationManager实例
//        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        //实例化NotificationCompat.Builde并设置相关属性
//        Notification.Builder builder = new Notification.Builder(this)
//                //设置小图标
//                .setSmallIcon(R.mipmap.ic_launcher)
//                //设置通知标题
//                .setContentTitle("最简单的Notification")
//                //设置通知内容
//                .setContentText("只有小图标、标题、内容");
////                .setPriority(Notification.PRIORITY_DEFAULT); //设置该通知优先级
//        //设置通知时间，默认为系统发出通知的时间，通常不用设置
//        //.setWhen(System.currentTimeMillis());
//        //通过builder.build()方法生成Notification对象,并发送通知,id=1
//        notifyManager.notify(1, builder.build());
//    }
}

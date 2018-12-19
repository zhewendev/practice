package com.vivo.a11085273.drawabletest;

import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

//    private ImageView img_show;
//    private RotateDrawable cd;
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 0x123) {
//                cd.setLevel(cd.getLevel() + 400);
//            }
//        }
//    };
    private ImageView img_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_show = (ImageView) findViewById(R.id.img_show);
        TransitionDrawable td = (TransitionDrawable) img_show.getDrawable();
        td.startTransition(90000);
//        img_show = (ImageView) findViewById(R.id.img_show);
//        // 核心实现代码
//        cd = (RotateDrawable) img_show.getDrawable();
//        final Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.sendEmptyMessage(0x123);
//                if (cd.getLevel() >= 10000) {
//                    timer.cancel();
//                }
//            }
//        }, 0, 100);
    }
}
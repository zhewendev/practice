package com.baiheng.multiplelanguagedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.baiheng.mylibrary.SingleTon;
import com.squareup.leakcanary.RefWatcher;

import java.lang.ref.WeakReference;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

//    private MyHandler myHandler = new MyHandler(this);

//    private static class MyHandler extends Handler {
//        private static WeakReference<MainActivity> mActivity;
//        public MyHandler(MainActivity activity) {
//            mActivity = new WeakReference<MainActivity>(activity);
//        }
//
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            MainActivity activity = mActivity.get();
//            if (activity != null) {
//                switch (msg.what) {
//                    case 1:
//                        Log.d("MainActivity","处理handler消息");
//                        break;
//                    default:
//                        break;
//                }
//            }
//        }
//    }


    private Handler.Callback mCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case 1:
                    Log.d("MainActivity","处理Handler消息");
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    private Handler mHandler = new WeakRefHandler(mCallback);

    private Handler mmHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = 1;
                mHandler.sendMessage(message);
            }
        }).start();
//        TextView textView = (TextView) findViewById(R.id.textView_region);
//        SharedPreferences mferences = getSharedPreferences("data",MODE_PRIVATE);
//        textView.setText(mferences.getString("textcontent",getString(R.string.text_content)));
//        textView.setText(R.string.text_content);

        Locale locale = getResources().getConfiguration().locale;
        String language = getLanguageEnv();
        TextView textView = (TextView) findViewById(R.id.language_code);
        textView.setText(language);

        String string = "";
        boolean b = TextUtils.isEmpty(string);
        Log.e("MainActivity",b + "");
        SingleTon.getInstance(this).print();


    }
    private String getLanguageEnv() {
//        Locale l = Locale.getDefault();
        Locale l = getResources().getConfiguration().locale;
        String language = l.getLanguage();
        String country = l.getCountry().toLowerCase();
        if ("zh".equals(language)) {
            if ("cn".equals(country)) {
                language = "zh-CN";
            } else if ("tw".equals(country)) {
                language = "zh-TW";
            } else if ("hk".equals(country)) {
                language = "zh-HK";
            }
        } else if ("pt".equals(language)) {
            if ("br".equals(country)) {
                language = "pt-BR";
            } else if ("pt".equals(country)) {
                language = "pt-PT";
            }
        }
        return language;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        RefWatcher refWatcher = LanguageApplication.getRefWatcher(this);
        refWatcher.watch(this);

//        try {
//            // ....
//        } catch (Exception e) {
//            // ...
//        } finally {
//            try {
//                // ...
//            } catch (Exception e) {
//                // ...
//            }
//        }
    }
}

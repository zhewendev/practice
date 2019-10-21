package com.baiheng.aroutedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mTv = (TextView) findViewById(R.id.send_message);
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/com/FirstUtilsActivity").navigation(getApplicationContext(), new NavigationCallback() {

                    @Override
                    public void onFound(Postcard postcard) {
                        Log.d(TAG,"找到了目标activity");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Log.d(TAG, "找不到目标Activity");
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.d(TAG, "完成跳转");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Log.d(TAG, "跳转被拦截了");
                    }
                });
            }
        });
    }
}

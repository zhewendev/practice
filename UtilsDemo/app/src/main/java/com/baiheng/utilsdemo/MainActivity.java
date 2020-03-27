package com.baiheng.utilsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.get_phone_type);
        textView = (TextView) findViewById(R.id.phone_type);
        textView.setText("wait");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneType = DeviceUtils.getProductSeries("ro.vivo.product.series");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GaidInfo gaidInfo = GAIDUtils.getGoogleAdId(MainActivity.this);
                        Log.i("GAID",gaidInfo.getId() + "||||||" + gaidInfo.getIdLimeted());
                    }
                }).start();
//                int versionCode = DeviceUtils.getAppVersionCode(getApplicationContext(),"com.baiheng.utilsdemo");
                textView.setText(phoneType);
            }
        });
    }
}

package com.vivo.a11085273.activitylifecycletest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NormalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_activity);
    }
}

package com.vivo.a11085273.vivogametest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        System.out.println("FirstActivityOnCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("FirstActivityOnResume");
    }
}

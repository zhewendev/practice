package com.vivo.a11085273.vivogametest;

import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends FirstActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        System.out.println("MainActivityOnCreate");
    }
}

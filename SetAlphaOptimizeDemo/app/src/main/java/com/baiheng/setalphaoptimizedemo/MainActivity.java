package com.baiheng.setalphaoptimizedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private CustomImageView mImgRed;
    private CustomImageView mImgGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImgRed = (CustomImageView) findViewById(R.id.img_red);
//        mImgGreen = (CustomImageView) findViewById(R.id.img_green_not_overlap);
        mImgRed.setAlpha(0.5f);
        mImgGreen.setAlpha(0.5f);
    }


}

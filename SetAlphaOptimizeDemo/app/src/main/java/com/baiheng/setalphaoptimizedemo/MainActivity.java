package com.baiheng.setalphaoptimizedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

public class MainActivity extends AppCompatActivity {

    private CustomImageView mImgRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImgRed = (CustomImageView) findViewById(R.id.img_red);
//        mImgRed.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        mImgRed.setAlpha(0.5f);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        ViewTreeObserver observer = view.getViewTreeObserver();
//        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                view.getViewTreeObserver.removeGlobalOnLayoutListener(this);
//                int width = view.getMeasuredWidth();
//                int height = view.getMeasuredHeight();
//            }
//        });
//    }
}

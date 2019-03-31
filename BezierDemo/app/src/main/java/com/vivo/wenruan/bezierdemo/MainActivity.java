package com.vivo.wenruan.bezierdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BezierLayout bezierLayout = (BezierLayout) findViewById(R.id.bezier_layout);
        bezierLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bezierLayout.addHeart();
            }
        });
    }
}

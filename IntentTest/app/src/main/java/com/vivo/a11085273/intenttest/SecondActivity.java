package com.vivo.a11085273.intenttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        MyApp app = MyApp.getInstance();
        TextView textView2 = (TextView) findViewById(R.id.text_view2);
        textView2.setText(app.getState());
    }
}

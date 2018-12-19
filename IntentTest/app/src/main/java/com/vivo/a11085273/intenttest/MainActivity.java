package com.vivo.a11085273.intenttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ArrayList bundleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendValue = (Button) findViewById(R.id.button_sendValue);
        final TextView textView1 = (TextView) findViewById(R.id.text_view1);
        final MyApp appState = MyApp.getInstance();
        appState.setState("hello MainActivity");
        final String state = appState.getState();
        appState.setState("hello secondActivity");
        textView1.setText(state);
        sendValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


    }
}

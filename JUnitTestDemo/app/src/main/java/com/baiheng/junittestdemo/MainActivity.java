package com.baiheng.junittestdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.baiheng.junittestdemo.bean.Person;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Person person = new Person();
    }
}

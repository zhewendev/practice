package com.baiheng.memoryleakdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.leakcanary.RefWatcher;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TestManager manager = TestManager.getInstance(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MemoryLeakApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
}

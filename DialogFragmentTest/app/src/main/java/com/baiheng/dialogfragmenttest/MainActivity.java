package com.baiheng.dialogfragmenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MyDialogFragment myDialogFragment = new MyDialogFragment();
//        myDialogFragment.show(getSupportFragmentManager(),"mydialogFragment");
        TestUnionDialog testUnionDialog = new TestUnionDialog(this);
        testUnionDialog.show();
    }
}

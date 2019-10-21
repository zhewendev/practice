package com.vivo.a11085273.fragmenttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(this);
////        registerBroadcastReceiver();
//        RigthFragment rigthFragment = new RigthFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("key","setArguments");
//        rigthFragment.setArguments(bundle);
//        replaceFragment(rigthFragment);
        manager = this.getSupportFragmentManager();
        transaction = manager.beginTransaction();
        /*创建leftFragment*/
        LeftFragment leftFragment = new LeftFragment();
        /*创建RightFragment*/
        RigthFragment rightFragment = new RigthFragment();
        /*通过事物把两个fragment分别添加到对应的容器中*/
//        leftFragment.setTargetFragment(rightFragment,111);

        transaction.add(R.id.right_layout, rightFragment);
        transaction.add(R.id.left_layout, leftFragment);
        /*提交事物*/
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                replaceFragment(new AnotherRightFragment());
                break;
            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.right_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}

package com.vivo.a11085273.clientothertest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.vivo.a11085273.othertest.IMyAidlInterface;
import com.vivo.a11085273.othertest.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    IMyAidlInterface aidl;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //绑定服务成功回调
            aidl = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //服务断开时回调
            aidl = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService();
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ArrayList<User> users = (ArrayList<User>) aidl.addUser(new User(12, "demaxiya"));
                    Log.d("MainActivity", "远程回调结果:" + users.toString());
                    int result = aidl.add(12, 12);
                    Log.d("MainActivity", "远程回调结果:" + result);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void bindService(){
        Intent intent = new Intent();
        //Android 5.0开始，启动服务必须使用显示的，不能用隐式的
        intent.setComponent(new ComponentName("com.vivo.a11085273.othertest", "com.vivo.a11085273.othertest.MyAidlService"));
        startService(intent);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
}

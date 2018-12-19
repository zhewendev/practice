package com.vivo.a11085273.anotherbroadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AnotherBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"AnotherBroadcastReceiver",Toast.LENGTH_SHORT).show();
    }


}

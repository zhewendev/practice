package com.baiheng.mylibrary;

import android.content.Context;
import android.util.Log;

public class SingleTon {
    private static  SingleTon singleTon;
    private Context mContext;

    private SingleTon(Context context) {
        mContext = context.getApplicationContext();
    }

    public static SingleTon getInstance(Context context) {
        if (singleTon == null) {
            singleTon = new SingleTon(context);
        }
        return  singleTon;
    }
    public void print() {
        Log.i("SingleTon","SingleTonTon");
    }
}

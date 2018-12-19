package com.vivo.a11085273.intenttest;

import android.app.Application;

class MyApp extends Application {
    private String myState;
    private static MyApp instance;

    public static MyApp getInstance(){
        return instance;
    }


    public String getState(){
        return myState;
    }
    public void setState(String s){
        myState = s;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }

}

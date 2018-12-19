package com.vivo.a11085273.mvpprojectdemo;

public interface MvpCallback {

    void onSuccess(String data);

    void onFailure(String msg);

    void onError();

    void onComplete();
}

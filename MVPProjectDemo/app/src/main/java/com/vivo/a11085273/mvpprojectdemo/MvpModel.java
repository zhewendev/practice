package com.vivo.a11085273.mvpprojectdemo;


import android.os.Handler;

public class MvpModel {

    public static void getNetData(final String param, final MvpCallback callback) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (param) {
                    case "normal":
                        callback.onSuccess("根据参数"+param+"的请求网络数据成功");
                        break;
                    case "failure":
                        callback.onFailure("请求失败：参数有误");
                        break;
                    case "error":
                        callback.onError();
                        break;
                }
                callback.onComplete();
            }
        }, 2000);
    }

}

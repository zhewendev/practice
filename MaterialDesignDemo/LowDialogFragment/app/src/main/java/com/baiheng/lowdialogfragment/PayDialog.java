package com.baiheng.lowdialogfragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebViewClient;

public class PayDialog extends FullScreenDialog {

    private MyWebView myWebView;
    private Activity activity;


    public PayDialog(Activity context,String string) {
        super(context);
        activity = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myWebView = new MyWebView(activity);
        myWebView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        myWebView.setHorizontalScrollBarEnabled(false);//水平不显示
        myWebView.setVerticalScrollBarEnabled(false); //垂直不显示
        myWebView.setWebViewClient(new WebViewClient());
        setContentView(myWebView);

    }

    public void pay() {
        show();
        myWebView.getSettings().setJavaScriptEnabled(true);   // 设置WebView属性，运行执行js脚本
        myWebView.loadUrl("https://www.baidu.com");
    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}

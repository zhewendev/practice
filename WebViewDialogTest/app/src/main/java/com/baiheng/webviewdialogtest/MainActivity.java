package com.baiheng.webviewdialogtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtn;
    private WebView mWebView;
    private static final String[] payMethod= {"SMS","SSS","ccc"};
    private String string = "ccc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = (Button) findViewById(R.id.button);
        mWebView = (WebView) findViewById(R.id.webview);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentPage paymentPage = new PaymentPage(MainActivity.this);
                paymentPage.pay();
//                mWebView.setWebViewClient(new WebViewClient());
//                mWebView.getSettings().setJavaScriptEnabled(true);
//                mWebView.loadUrl("https://sandbox.dlocal.com/collect/pay/pay/M-790f5bb0-0b6a-11ea-8d66-7fb82c3fe391?xtid=CATH-ST-1574236251-276731576");
            }
        });
        for (String str : payMethod) {
            if (str.equals(string)) {
                mBtn.setText("可以这样调用支付方式");
            }
        }
//        if (payMethod.equals(string)) {
//
//        }
    }
}

package com.vivo.a11085273.webviewtest;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mOpenWebViewBtn;
    private RelativeLayout mRelativeLayout;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        mOpenWebViewBtn = (Button) findViewById(R.id.open_webview);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.webview_layout);
        mWebView = (WebView) findViewById(R.id.webview);
//        mWebView = new WebView(this);
//        mWebView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        mRelativeLayout.addView(mWebView);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        initWebViewSet(mWebView);
        mOpenWebViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mWebView.loadUrl("https://blog.csdn.net/");
                mWebView.loadUrl("file:////android_asset/web.html");
                mWebView.evaluateJavascript("javascript:ok()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String s) {
                        Log.i("nihao","ssss" + s);
                    }
                });
            }
        });
    }
    @SuppressLint({"SetJavaScriptEnabled","JavascriptInterface"})
    private void initWebViewSet(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webView.addJavascriptInterface(new JSBridge(),"android");     //Js调用本地Android代码

    }

    private void testEvaluateJavascript(WebView webView) {
        webView.evaluateJavascript("ok()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                Log.i("nihao",s);
            }
        });
    }

    public class JSBridge{
        @JavascriptInterface
        public void toastMessage(String message) {
            Toast.makeText(getApplicationContext(),"通过Native传递的Toast" + message,Toast.LENGTH_LONG).show();
        }
    }


}

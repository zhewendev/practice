package com.baiheng.webviewdialogtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class CommonWebView extends WebView {

    private static final String TAG = "CommonWebView";
    private CommonWebView.OnWebViewCallback mOnWebViewCallback;
    private CommonWebView.OnCompletePayCallback mOnCompletePayCallback;
    private ProgressBar mProgress;

    private Activity mActivity;
    private WebView mNewWebView;
    private RelativeLayout mMultipleWindowLayout;

    public CommonWebView(Context context) {
        super(context);
    }

    public CommonWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    public void setProgress(ProgressBar progress) {
        mProgress = progress;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void loadDefaultConfig() {
        WebSettings webSettings = this.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowUniversalAccessFromFileURLs(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportMultipleWindows(true);
        setWebViewClient(mWebClient);
        setWebChromeClient(webChromeClient);
        Log.i(TAG,"Loading default value");
    }

    private WebChromeClient webChromeClient= new WebChromeClient() {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (mProgress != null) {
                if (newProgress == 100) {
                    mProgress.setVisibility(GONE);
                } else {
                    mProgress.setVisibility(VISIBLE);
                    mProgress.setProgress(newProgress);
                }
            }
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {

            Log.i(TAG, "Create a new Window" + resultMsg);
            mNewWebView = new WebView(mActivity);
            initWebView(mNewWebView, this);
            mMultipleWindowLayout.setVisibility(VISIBLE);
            mMultipleWindowLayout.addView(mNewWebView);
            WebView.WebViewTransport transport = (WebViewTransport) resultMsg.obj;
            transport.setWebView(mNewWebView);
            resultMsg.sendToTarget();
            return true;
        }

        @Override
        public void onCloseWindow(WebView window) {
            super.onCloseWindow(window);
            if (mNewWebView != null) {
                mNewWebView.destroy();
            }
        }
    };

    private void initWebView(final WebView webView, WebChromeClient webChromeClient) {
        if (webView != null) {
            final WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.setWebChromeClient(webChromeClient);
        }
    }

    private WebViewClient mWebClient = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.i(TAG,"onPageStarted");
//            Toast.makeText(mActivity,"开始加载",Toast.LENGTH_LONG).show();
            mProgress.setVisibility(VISIBLE);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//            mOnWebViewCallback.onWebViewCallback();
            Log.i(TAG,"onReceivedError");
            loadData("", "text/html", "UTF-8");
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//            mOnWebViewCallback.onWebViewCallback();
            Log.i(TAG,"onReceivedError");
            loadData("", "text/html", "UTF-8");

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

            Log.i(TAG,"shouldOverrideUrlLoading");
            String url = request.getUrl().toString();
//            String url = request.getUrl().getPath();
            Log.i(TAG,url);
            try{
                if(!url.startsWith("http://") && !url.startsWith("https://")){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    mActivity.startActivity(intent);
                    if (url.contains("google.com")) {
                        Log.i(TAG,"支付结束");
                    }
                    return true;
                }
            }catch (Exception e){//防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                Log.i(TAG,"crash process");
                return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
            }
//            if (url.contains("google.com")) {
//                Log.i(TAG, "Payment completed, ready to query the payment result");
//                return true;
////                mOnCompletePayCallback.onCompletePayCallback();
//            }
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Toast.makeText(mActivity,"加载完成",Toast.LENGTH_LONG).show();
        }
    };
    public void setOnWebViewCallback(CommonWebView.OnWebViewCallback onWebViewCallback) {
        mOnWebViewCallback = onWebViewCallback;
    }

    public interface OnWebViewCallback {
        void onWebViewCallback();
    }

    public void setOnCompletePayCallback(CommonWebView.OnCompletePayCallback onCompletePayCallback) {
        mOnCompletePayCallback = onCompletePayCallback;
    }

    public interface OnCompletePayCallback {
        void onCompletePayCallback();
    }
}

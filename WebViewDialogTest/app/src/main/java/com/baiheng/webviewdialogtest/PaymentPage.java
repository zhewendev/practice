package com.baiheng.webviewdialogtest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class PaymentPage extends FullScreenDialog {

    private Activity mActivity;
    private CommonWebView mPaymentView;
    private LinearLayout mLinearLayout;

    public PaymentPage(Activity context) {
        super(context);
        mActivity = context;
    }

    public void pay() {
        show();
        mPaymentView.loadDefaultConfig();
        dopay();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPaymentView = new CommonWebView(mActivity);
        mPaymentView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mPaymentView.setHorizontalScrollBarEnabled(false);//水平不显示
        mPaymentView.setVerticalScrollBarEnabled(false); //垂直不显示
        mPaymentView.setActivity(mActivity);
        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        View contentView = layoutInflater.inflate(R.layout.show_webview_layout,null);
        ProgressBar progressBar = (ProgressBar) contentView.findViewById(R.id.web_progress_bar);
        RelativeLayout relativeLayout = (RelativeLayout) contentView.findViewById(R.id.web_view_content);
        relativeLayout.addView(mPaymentView);
        mPaymentView.setProgress(progressBar);
        setContentView(contentView);
    }

    private void dopay() {
        try {
//            mPaymentView.loadUrl("https://pay.dlocal.com/collect/pay/pay/M-b1ffa480-0b7d-11ea-91fc-7719f6a49eb7?xtid=CATH-ST-1574244507-213460968");
            mPaymentView.loadUrl("https://stage.codapayments.com/demo/iframe.jsp?txnId=5765055527572180122&countryCode=458&currencyCode=458&itemInfo=&environment=production&country=458&backend-lang=en&backend-pay-type=1&backend-msisdn=122518428&backend-mno-code=50212&backend-email=&backend-name=&backend-id-no=&backend-merchant-name=Koko+eBooks&backend-merchant-userid=Testuser397&backend-merchant-bankcode=PYTM0123456&backend-content-type=1&backend-account-number=&backend-security-code=&backend-voucher-code=&backend-voucher-pin=&backend-card-type=1&backend-subs-type=0&backend-subs-id=&backend-subs-plan-id=");
//            mPaymentView.loadUrl("https://www.csdn.net");
        } catch (RuntimeException e) {
            Log.i("PaymentPage","pay channel load url error", e);
        }
    }
}

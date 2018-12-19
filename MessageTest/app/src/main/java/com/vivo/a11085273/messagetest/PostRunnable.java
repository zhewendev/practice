package com.vivo.a11085273.messagetest;

import android.graphics.Bitmap;
import android.os.Handler;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;


public class PostRunnable implements Runnable {

    private Handler handler;
    private RefreshUI refreshUI;
    byte[] data = null;

    public PostRunnable(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("https://www.fotor.com/images2/features/one-tap-enhance/one_3.jpg");
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                data = EntityUtils.toByteArray(httpResponse.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回结果给UI线程
        handler.post(new Runnable() {
            @Override
            public void run() {
                refreshUI.setImage(data);
            }
        });
    }

    public interface RefreshUI {
        void setImage(byte[] data);
    }

    public void setRefreshUI(RefreshUI refreshUI) {
        this.refreshUI = refreshUI;
    }
}

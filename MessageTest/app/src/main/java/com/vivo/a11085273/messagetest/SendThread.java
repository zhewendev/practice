package com.vivo.a11085273.messagetest;

import android.os.Handler;
import android.os.Message;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class SendThread extends Thread {

    private Handler handler;

    public SendThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        /**
         耗时操作
         */
        byte[]data = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("https://www.fotor.com/images2/features/one-tap-enhance/one_3.jpg");
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                data = EntityUtils.toByteArray(httpResponse.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回结果给UI线程
        doTask(data);
    }

    /**
     通过handler返回消息
     @param data
     */
    private void doTask(byte[] data) {
        Message msg =Message.obtain();  //从全局池中返回一个message实例，避免多次创建message（如new Message）
        msg.obj = data;
        msg.what = 1;   //标志消息的标志
        handler.sendMessage(msg);
    }
}

package com.baiheng.okhttpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendBtn = (Button) findViewById(R.id.btn_send_request);
        mTv = (TextView) findViewById(R.id.content_text);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestWithHttpURLConnection();
            }
        });
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //首先创建一个OkHttpClient的实例
                    OkHttpClient client = new OkHttpClient();
                    //如果想要发一条HTTP请求，就需要创建一个Request对象
                    Request request = new Request.Builder()
                            .url("http://mock-api.com/ynWodMn6.mock/baiheng/get/test")
//                            .url("https://www.baidu.com")
                            .build();
                    //调用OkHttpClient的newCall()方法来创建一个Call对象
                    //调用他的execute()方法来发送并获取服务器返回的数据
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);
                    Log.d("MainActivity","KKKKKKKKKKKKKK");
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d("MainActivity","KKKKKKKKKKKKKK");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.d("MainActivity","KKKKKKKKKKKKLLLLKK");
                            String responseData = response.body().string();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    InputStream inp = System.in;
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                mTv.setText(response);
            }
        });
    }

}

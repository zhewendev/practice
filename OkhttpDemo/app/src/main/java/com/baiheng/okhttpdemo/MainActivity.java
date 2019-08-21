package com.baiheng.okhttpdemo;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.baiheng.okhttpdemo.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http2.Header;
import okio.BufferedSource;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest = findViewById(R.id.send_request);
        responseText = findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request) {
            sendRequestWithHttpURLConnection();
        }
    }

    private void sendRequestWithHttpURLConnection() {
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //首先创建一个OkHttpClient的实例
                    OkHttpClient client = new OkHttpClient();
                    //如果想要发一条HTTP请求，就需要创建一个Request对象
                    Request request = new Request.Builder()
                            .url("https://www.baidu.com")
                            .build();
                    //调用OkHttpClient的newCall()方法来创建一个Call对象
                    //调用他的execute()方法来发送并获取服务器返回的数据
                    Response response = client.newCall(request).execute();
//                    client.newCall(request).enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//
//                        }
//
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//
//                        }
//                    });
                    String responseData = response.body().string();
                    showResponse(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                    InputStream inp = System.in;
                }
            }
        }).start();
    }

    /**
     * 将数据显示到界面上
     *
     * @param response 要显示的数据
     */
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                responseText.setText(response);
            }
        });
    }
//private void okHttpPostFormData(String url) {
//    //POST参数构造MultipartBody.Builder，表单提交
//    HashMap<String, String> params = new HashMap<>();
//    MultipartBody.Builder MultipartBodyBuilder  = new MultipartBody.Builder()
//            .setType(MultipartBody.FORM);
//    if (params != null) {
//        for (String key : params.keySet()) {
//            if (params.get(key) != null) {
//                MultipartBodyBuilder.addFormDataPart(key, params.get(key));
//            }
//        }
//    }
//
//    // 构造Request->call->执行
//    Request request = new Request.Builder()
//            .url(url)
//            .post(MultipartBodyBuilder.build())//参数放在body体里
//            .build();
//
//    OkHttpClient okHttpClient = new OkHttpClient();
//    Call call = okHttpClient.newCall(request);
//    //[1]同步请求；
//       /* try {
//            call.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }*/
//
//    //[2]异步请求
//    call.enqueue(new Callback() {
//        @Override
//        public void onFailure(Call call, IOException e) {
//
//        }
//
//        @Override
//        public void onResponse(Call call, Response response) throws IOException {
//
//        }
//    });
//}
//
//    /**
//     * [多个文件的上传]
//     */
//
//    private void postMultiFile(String url){
//
//        HashMap<String, String> urlHeaders = new HashMap<>();
//
//        OkHttpClient client = new OkHttpClient();
//
//        // form 表单形式上传,MultipartBody的内容类型是表单格式，multipart/form-data
//        MultipartBody.Builder multiPartBodyBuilder= new MultipartBody
//                .Builder()
//                .setType(MultipartBody.FORM);
//
//        //参数
//        HashMap<String,String> params = new HashMap<>();
//        if (params != null) {
//            for (String key : params.keySet()) {
//                if (params.get(key)!=null){
//                    //增加一系列的参数；
//                    multiPartBodyBuilder.addFormDataPart(key, params.get(key));
//                }
//            }
//        }
//
//        //需要上传的文件，需要携带上传的文件（小型文件 不建议超过500K）
//        HashMap<String,String> files= new HashMap<>();
//        if (files != null) {
//            for (String key : files.keySet()) {
//                //重点：RequestBody create(MediaType contentType, final File file)构造文件请求体RequestBody
//                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), files.get(key));
//                multiPartBodyBuilder.addFormDataPart(key, files.get(key).getClass().getName(),requestBody);
//            }
//        }
//
//        //构造请求request
//        Request request = new Request.Builder()
//                .headers(urlHeaders == null ? new Headers.Builder().build() : Headers.of(urlHeaders))
//                .url(url)
//                .post(multiPartBodyBuilder.build())
//                .build();
//
//        //异步执行请求
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                //非主线程
//                if (response.isSuccessful()) {
//                    String str = response.body().string();
//                    Log.i("tk", response.message() + " , body " + str);
//
//                } else {
//                    Log.i("tk" ,response.message() + " error : body " + response.body().string());
//                }
//            }
//        });
//    }

}

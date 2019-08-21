package com.baiheng.httpurlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView responseText;
    private String picture = "https://i.ytimg.com/vi/ck-2nnFC8oE/hqdefault.jpg";
    private Bitmap bitmap;
    private ImageView imgPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        imgPic = (ImageView) findViewById(R.id.imagPic);
        Button downloadPicture = (Button) findViewById(R.id.download_picture);
        downloadPicture.setOnClickListener(this);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request) {
            sendRequestWithHttpURLConnection();
        } else if (v.getId() == R.id.download_picture) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        byte[] data = GetData.getImage(picture);
                        bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        showBitmap();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }

    private void sendRequestWithHttpURLConnection() {
        //开启线程发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.connect();
                    int i = connection.getResponseCode();
                    InputStream in = connection.getInputStream();
                    //对输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    showResponse(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse( final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //进行UI操作
                responseText.setText(response);
            }
        });
    }

    private void showBitmap() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imgPic.setImageBitmap(bitmap);
            }
        });
    }
}
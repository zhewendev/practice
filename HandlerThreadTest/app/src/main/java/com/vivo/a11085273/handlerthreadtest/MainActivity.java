package com.vivo.a11085273.handlerthreadtest;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    /**
     * 图片地址集合
     */
    private String url[]={
            "https://www.qqkw.com/d/file/p/2018/06-15/5f57a69b79ef4f41ecaa94fed9a63478.jpg",
            "https://www.qqkw.com/d/file/p/2018/06-15/02ec6b0d355382658e5616d80294a3c0.jpg",
            "https://www.qqkw.com/d/file/p/2018/06-15/ba7849f05cb092e5902dfabad9fae4c5.jpg",
            "https://www.qqkw.com/d/file/p/2018/06-15/02ec6b0d355382658e5616d80294a3c0.jpg",
            "https://www.qqkw.com/d/file/p/2018/06-15/5f57a69b79ef4f41ecaa94fed9a63478.jpg"
    };
    private ImageView imageView;
    @SuppressLint("HandlerLeak")
    private Handler mUIHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            LogUtils.e("次数："+ msg.what);
            ImageModel model = (ImageModel) msg.obj;
            imageView.setImageBitmap(model.bitmap);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);
        //创建异步HandlerThread
        HandlerThread handlerThread = new HandlerThread("downloadImage");
        //必须先开启线程
        handlerThread.start();
        //子线程Handler
        Handler childHandler = new Handler(handlerThread.getLooper(), new ChildCallback());
        for (int i = 0; i < 5; i++) {
            //每秒1秒去更新图片
            childHandler.sendEmptyMessageDelayed(i, 1000 * i);
        }
    }
    //Callback运行于子线程
    class ChildCallback implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            //子线程中进行网络请求
            Bitmap bitmap = downloadUrlBitmap(url[msg.what]);
            ImageModel imageModel = new ImageModel();
            imageModel.bitmap = bitmap;
            imageModel.url = url[msg.what];
            Message msg1 = Message.obtain();
            msg1.what = msg.what;
            msg1.obj = imageModel;
            //通知主线程去更新UI
            mUIHandler.sendMessage(msg1);
            return false;
        }
    }

    private Bitmap downloadUrlBitmap(String urlString) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        Bitmap bitmap=null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 512);
            bitmap=BitmapFactory.decodeStream(in);
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}
class ImageModel {
    String url;
    public Bitmap bitmap;
}

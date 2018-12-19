package com.vivo.a11085273.layoutinflatertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获得LayoutInflater对象;
        final LayoutInflater inflater = LayoutInflater.from(this);
        //获得外部容器对象
        final RelativeLayout rly = (RelativeLayout) findViewById(R.id.RelativeLayout1);
        Button btnLoad = (Button) findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加载要添加的布局对象
                LinearLayout ly = (LinearLayout) inflater.inflate(
                        R.layout.inflate, null, false).findViewById(
                        R.id.ly_inflate);
                //设置加载布局的大小与位置
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.CENTER_IN_PARENT);
                rly.addView(ly,lp);
            }
        });
    }

}

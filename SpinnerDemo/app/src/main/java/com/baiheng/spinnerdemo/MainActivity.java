package com.baiheng.spinnerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private String[] province = {"安徽省","河北省","河南省","湖北省","湖南省"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        VelocityTracker velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);
        velocityTracker.computeCurrentVelocity(1000);
        int xVelocity = (int) velocityTracker.getXVelocity();
        int yVelocity = (int) velocityTracker.getYVelocity();
        return super.onTouchEvent(event);
    }

    private void initView() {
        spinner = findViewById(R.id.spinner_view);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            //当item被选择后调用此方法
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //获取我们所选中的内容
//                String s = parent.getItemAtPosition(position).toString();
//                //弹一个吐司提示我们所选中的内容
//                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
//            }
//            //只有当patent中的资源没有时，调用此方法
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(getApplicationContext(), "你好你好", Toast.LENGTH_SHORT).show();
//            }
//        });
        spinner.setAdapter(new MyAdapter(province,this));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }


}

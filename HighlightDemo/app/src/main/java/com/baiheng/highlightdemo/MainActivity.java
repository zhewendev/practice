package com.baiheng.highlightdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.baiheng.module_highlight.Highlight;
import com.baiheng.module_highlight.position.OnLeftPosCallback;
import com.baiheng.module_highlight.position.OnRightPosCallback;
import com.baiheng.module_highlight.shape.OvalLightShape;
import com.baiheng.module_highlight.shape.RectLightShape;

public class MainActivity extends AppCompatActivity {

    private Highlight mHighlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void showHighlightGuide(View view) {
        mHighlight = new Highlight(MainActivity.this)
                .autoRemove(false)  //  设置背景点击高亮布局自动移除为false，默认为true
                .intercept(true)    //  设置拦截属性为true，高亮布局拦截后面布局的滑动效果，使下方点击回调失效
                .anchor(findViewById(R.id.id_container))
                .addHighlight(R.id.tv_light,R.layout.info_known,new OnRightPosCallback(45), new RectLightShape())
                .addHighlight(R.id.btn_right_light, R.layout.info_known,new OnLeftPosCallback(5), new OvalLightShape());
        mHighlight.show();

    }

    public void showNextHighlightGuide(View view) {
        mHighlight = new Highlight(MainActivity.this)
                .anchor(findViewById(R.id.id_container))
                .addHighlight(R.id.tv_light,R.layout.info_known,new OnRightPosCallback(45), new RectLightShape())
                .addHighlight(R.id.btn_right_light, R.layout.info_known,new OnLeftPosCallback(5), new OvalLightShape())
                .autoRemove(false)
                .enableNext();
        mHighlight.show();
    }


    public void clickKnown(View view)
    {
        if(mHighlight.isShowing() && mHighlight.isNext())//如果开启next模式
        {
            mHighlight.next();
        }else
        {
            remove(null);
        }
    }

    public void remove(View view)
    {
        mHighlight.remove();
    }

    public void add(View view)
    {
        mHighlight.show();
    }
}

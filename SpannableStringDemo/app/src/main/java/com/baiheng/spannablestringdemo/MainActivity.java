package com.baiheng.spannablestringdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;
    private TextView mTv4;
    private TextView mTv5;
    private TextView mTv6;
    private TextView mTv7;
    private TextView mTv8;
    private TextView mTv9;
    private TextView mTv10;
    private TextView mTv11;
    private TextView mTv12;
    private TextView mTv13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        showTextView();
    }

    private void init() {
        mTv1 = (TextView) findViewById(R.id.tv_1);
        mTv2 = (TextView) findViewById(R.id.tv_2);
        mTv3 = (TextView) findViewById(R.id.tv_3);
        mTv4 = (TextView) findViewById(R.id.tv_4);
        mTv5 = (TextView) findViewById(R.id.tv_5);
        mTv6 = (TextView) findViewById(R.id.tv_6);
        mTv7 = (TextView) findViewById(R.id.tv_7);
        mTv8 = (TextView) findViewById(R.id.tv_8);
        mTv9 = (TextView) findViewById(R.id.tv_9);
        mTv10 = (TextView) findViewById(R.id.tv_10);
        mTv11 = (TextView) findViewById(R.id.tv_11);
        mTv12 = (TextView) findViewById(R.id.tv_12);
        mTv13 = (TextView) findViewById(R.id.tv_13);
    }

    private void showTextView() {
        //设置TextView的背景颜色
        SpannableString ss1 = new SpannableString("设置背景颜色");
        ss1.setSpan(new BackgroundColorSpan(Color.parseColor("#FFD700")), 0,
                ss1.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mTv1.setText(ss1);

        //添加超链接和链接颜色
        String string = getString(R.string.baby_memeda);
        String textString = getString(R.string.baby_is_best);
        SpannableString ssl2 = new SpannableString(textString);
        setClickableSpan(ssl2, textString, string);
        mTv2.setText(ssl2);
        mTv2.setMovementMethod(LinkMovementMethod.getInstance());

        //添加超链接方式2
        SpannableString ssl3 = new SpannableString(getString(R.string.baby_is_best));
        ssl3.setSpan(new URLSpan("tel:10086"),0,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTv3.setText(ssl3);
        mTv3.setMovementMethod(LinkMovementMethod.getInstance());


        //设置高亮与文字颜色
        SpannableString ssl4 = fontStyle(MainActivity.this,textString, string, Color.BLUE,0,0,0);
        mTv4.setText(ssl4);

        //设置字体
        SpannableString ssl5 = new SpannableString(textString);
        ssl5.setSpan(new TypefaceSpan("monospace"),4, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置字体大小(绝对值)
        ssl5.setSpan(new AbsoluteSizeSpan(50),4,9,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置字体大小（相对值）
        ssl5.setSpan(new RelativeSizeSpan(1.5f),9,13,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTv5.setText(ssl5);

        //设置下划线
        SpannableString ssl6 = new SpannableString(textString);
        ssl6.setSpan(new UnderlineSpan(),5,9,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTv6.setText(ssl6);

        //在TextView中设置图片
        SpannableString ssl7 = new SpannableString("设置图片");
        ssl7.setSpan(new DynamicDrawableSpan(DynamicDrawableSpan.ALIGN_BOTTOM) {
            @Override
            public Drawable getDrawable() {
                Drawable d = getResources().getDrawable(R.drawable.ic_launcher_foreground);
                d.setBounds(0,0,50,50);
                return d;
            }
        },0,1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTv7.setText(ssl7);

        //TextView文字基于X轴缩放
        SpannableString ssl8 = new SpannableString("基于x轴缩放");
        // ScaleXSpan中的参数大于1表示横向扩大，小于1大于0表示缩小，等于1表示正常显示
        ssl8.setSpan(new ScaleXSpan(2),0, ssl8.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTv8.setText(ssl8);

        //上下标使用
        SpannableString ssl9 = new SpannableString("(x1 + x2)2 = x12+x22+2x1x2");
        ssl9.setSpan(new SubscriptSpan(),2,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //设置下标
        ssl9.setSpan(new AbsoluteSizeSpan(30),2,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);    //设置下标字体大小

        ssl9.setSpan(new SuperscriptSpan(),9,10,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置上标
        ssl9.setSpan(new AbsoluteSizeSpan(30),9,10,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //设置字体大小
        mTv9.setText(ssl9);






    }

    private void setClickableSpan(SpannableString span, String fullString, String subString) {
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                //这里设置跳转
                Uri uri = Uri.parse("tel:10086");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }

            //设置文字样式
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.bgColor = Color.WHITE;
                ds.setColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        };
        int begin = fullString.indexOf(subString);
        int end = begin + subString.length();
        span.setSpan(clickableSpan, begin, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 设置高亮与样式（加粗等）
     * @param context
     * @param text
     * @param target
     * @param color
     * @param style 设置类型（0：NORMAL、1：BOLD、 2：ITALIC、3：BOLD_ITALIC）
     * @param start
     * @param end
     * @return
     */
    public static SpannableString fontStyle(Context context, String text, String target, int color, int style,
                                            int start, int end) {

        SpannableString spannableString = new SpannableString(text);
        try {
            Pattern pattern = Pattern.compile(target);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                ForegroundColorSpan spanColor = new ForegroundColorSpan(color);
                spannableString.setSpan(spanColor, matcher.start() - start, matcher.end() + end,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                StyleSpan span = new StyleSpan(style);
                spannableString.setSpan(span, matcher.start() - start, matcher.end() + end,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        } catch (Exception e) {
            Log.e(TAG, "fontStyle error:" + e);
        }
        return spannableString;
    }
}

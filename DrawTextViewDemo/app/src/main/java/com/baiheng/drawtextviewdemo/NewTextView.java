package com.baiheng.drawtextviewdemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
//import android.graphics.Paint;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Looper;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class NewTextView extends View {

    private Context mContext;
    private Paint mPaint = new Paint();
    private TextPaint mTextPaint = new TextPaint();
    private CharSequence mCharSequence = "CharSequence使用学习";
    private String mStr = "暮从碧山下，山月随人归";
    private String text1 = "Love alone can release the power of the atom, so it will work for man and not against him";
    private String text2 = "a\nbc\nefghigklmn\nopqrstuvw\nxyz";
    private StaticLayout staticLayout1;
    private StaticLayout staticLayout2;
    private Path mCirclePath1 = new Path();
    private Path mCirclePath2 = new Path();
    private Typeface mFont;
    private String mFamilyName = "楷体";
    private Rect mRect = new Rect();
    private float advance;
    private int offAdvance;

    private float[]pos = new float[] {80,100,80,200,80,300,80,400};

    public NewTextView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public NewTextView(Context context, AttributeSet attr) {
        super(context, attr);
        mContext = context;
        init();
    }

    private void init() {
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(50);
        mTextPaint.setTextSize(50);

//        mCirclePath1.addCircle(250,300,180,Path.Direction.CCW);
//        mCirclePath2.addCircle(750,300,180, Path.Direction.CCW);
//
//        mFont = Typeface.create(mFamilyName, Typeface.BOLD);
//        mPaint.setTypeface(mFont);
//
//        AssetManager assetManager = mContext.getAssets();
//        Typeface typeface = Typeface.createFromAsset(assetManager,"medium_rom9.ttf");
//        mPaint.setTypeface(typeface);
//        mPaint.getTextBounds(mStr,0,mStr.length(),mRect);
//        Log.e("NewTextView",mRect.toShortString());
//        Log.e("NewTextView",mPaint.measureText(mStr) + "");
//        int temp = mPaint.breakText(mStr,false,200,null);
//        Log.e("NewTextView",temp + "");
//        advance = mPaint.getRunAdvance(mStr,0,mStr.length(),0,mStr.length(),false,mStr.length());
//        offAdvance = mPaint.getOffsetForAdvance(mCharSequence,0,mCharSequence.length(),0,mCharSequence.length(),false,100.f);
        staticLayout1 = new StaticLayout(text1,mTextPaint,700, Layout.Alignment.ALIGN_NORMAL,1,0,true);
        staticLayout2 = new StaticLayout(text2,mTextPaint,700, Layout.Alignment.ALIGN_NORMAL,1,0,true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawText("TextView绘制学习",400,400,mPaint);
//        canvas.drawText(mCharSequence,4,mCharSequence.length(),100,100,mPaint);
//        canvas.drawPath(mCirclePath1,mPaint);
//        canvas.drawPath(mCirclePath2,mPaint);
//        canvas.drawTextOnPath(mStr,mCirclePath1,0,0,mPaint);
//        canvas.drawTextOnPath(mStr,mCirclePath2,80,30,mPaint);
//        canvas.drawText("设置楷体绘制示例",10,100,mPaint);
//        canvas.drawText("获取自定义字体绘制示例123456789",10,100,mPaint);
//        canvas.drawText(mStr,0,200,mPaint);
//        mPaint.setStrokeWidth(3);
//        canvas.drawLine(advance, 200 - 50, advance, 200 + 10, mPaint);
//        canvas.drawText(mCharSequence,0,mCharSequence.length(),0,200,mPaint);
//        Log.e("NewTextView",offAdvance + "");
//        canvas.save();
//        canvas.translate(50,100);
        staticLayout1.draw(canvas);
        canvas.save();
        canvas.translate(0,300);
        staticLayout2.draw(canvas);
        canvas.restore();

    }
}

package com.baiheng.setalphaoptimizedemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomImageView extends View {

    private Paint mPaint;
    private int mSecondColor;

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.CustomImageView);
        int color = typedArray.getColor(R.styleable.CustomImageView_backgroundColor, 0xff0000);
        mSecondColor = typedArray.getColor(R.styleable.CustomImageView_secondBackGroundColor, 0x00ff00);
        mPaint = new Paint();
        mPaint.setColor(color);

    }

    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,400,400,mPaint);
        mPaint.setColor(mSecondColor);
        canvas.drawRect(200,300,600,700,mPaint);
    }
}

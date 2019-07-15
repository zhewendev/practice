package com.baiheng.setalphaoptimizedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CustomImageView extends View {

    private Paint mPaint;

    public CustomImageView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);

    }

//    @Override
//    public boolean hasOverlappingRendering() {
//        return false;
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,200,200,mPaint);
    }
}

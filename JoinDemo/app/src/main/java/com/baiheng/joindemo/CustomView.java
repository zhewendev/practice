package com.baiheng.joindemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class CustomView extends View {

    private Paint mPaint;
    private Path mPath;
    public CustomView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPath = new Path();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        mPath.moveTo(100, 100);
        mPath.rLineTo(200,0);
        mPath.rLineTo(-50,50);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);

        mPaint.setStrokeJoin(Paint.Join.MITER);
        mPath.moveTo(100, 200);
        mPath.rLineTo(200,0);
        mPath.rLineTo(-50,50);
        canvas.drawPath(mPath,mPaint);

        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        mPath.moveTo(100, 300);
        mPath.rLineTo(200,0);
        mPath.rLineTo(-50,50);
        canvas.drawPath(mPath,mPaint);

    }
}

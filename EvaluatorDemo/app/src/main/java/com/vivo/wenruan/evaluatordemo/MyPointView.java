package com.vivo.wenruan.evaluatordemo;

import android.animation.ObjectAnimator;
import android.animation.PointFEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

public class MyPointView extends View {

    private static final int DURATION = 3000;
    private MyPoint mMyPoint = new MyPoint();
    private Paint mPaint = new Paint();
    private PointF mTempPointF;


    public MyPointView(Context context) {
        super(context);
    }

    public MyPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPointView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mMyPoint.getPointF() != null) {
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.BLACK);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(mMyPoint.getPointF().x, mMyPoint.getPointF().y,100, mPaint);
        }
    }

    public void doAnimation() {
        ValueAnimator animator = ValueAnimator.ofObject(new PointFEvaluator(), new PointF(100,100), new PointF(500,500));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mTempPointF = (PointF) animation.getAnimatedValue();
                mMyPoint.setPointF(mTempPointF);
                invalidate();
            }
        });
        animator.setDuration(DURATION);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }

}

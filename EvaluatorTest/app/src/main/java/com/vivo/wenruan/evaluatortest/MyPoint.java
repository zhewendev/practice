package com.vivo.wenruan.evaluatortest;

import android.graphics.PointF;

public class MyPoint {
    private PointF mPointF;

    public MyPoint(PointF mPointF) {
        this.mPointF = mPointF;
    }

    public MyPoint() {

    }

    public PointF getPointF() {
        return mPointF;
    }

    public void setPointF(PointF mPointF) {
        this.mPointF = mPointF;
    }
}

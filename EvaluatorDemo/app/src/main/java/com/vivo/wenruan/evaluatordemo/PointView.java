package com.vivo.wenruan.evaluatordemo;

import android.content.Context;
import android.view.View;

public class PointView extends View {

    private float mRadius;

    public PointView(Context context) {
        super(context);
    }

    public float getRadius() {
        return mRadius;
    }

    public void setRadius(float mRadius) {
        this.mRadius = mRadius;
    }
}

package com.vivo.wenruan.bezierdemo;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF mControlP1;
    private PointF mControlP2;

    public BezierEvaluator(PointF controlP1, PointF controlP2) {
        this.mControlP1 = controlP1;
        this.mControlP2 = controlP1;
    }

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {

        float timeLeft = 1.0f - fraction;
        PointF point = new PointF();

        point.x = timeLeft * timeLeft * timeLeft * (startValue.x) + 3 * timeLeft * timeLeft * fraction
                * (mControlP1.x) + 3 * timeLeft * fraction * fraction * (mControlP2.x)
                + fraction * fraction * fraction * (endValue.x);

        point.y = timeLeft * timeLeft * timeLeft * (startValue.y) + 3 * timeLeft * timeLeft * fraction *
                (mControlP1.y) + 3 * timeLeft * fraction *
                fraction * (mControlP2.y) + fraction * fraction * fraction * (endValue.y);
        return point;
    }
}

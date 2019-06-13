package com.baiheng.module_highlight.shape;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.baiheng.module_highlight.Highlight;

public class CircleLightShape extends BaseLightShape {

    public CircleLightShape() {
        super();
    }

    public CircleLightShape(float dx, float dy) {
        super(dx, dy);
    }

    public CircleLightShape(float dx, float dy, float blurRadius) {
        super(dx, dy, blurRadius);
    }

    @Override
    protected void drawShape(Bitmap bitmap, Highlight.ViewPosInfo viewPosInfo) {
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        if (mBlurRadius > 0) {
            paint.setMaskFilter(new BlurMaskFilter(mBlurRadius, BlurMaskFilter.Blur.SOLID));
        }
        RectF rectF = viewPosInfo.rectF;
        canvas.drawCircle(rectF.left + (rectF.width() / 2), rectF.top + (rectF.height() / 2),
                Math.max(rectF.width(), rectF.height()) / 2, paint);
    }

    @Override
    protected void resetRectF4Shape(RectF viewPosInfoRectF, float dx, float dy) {
        viewPosInfoRectF.inset(dx, dy);
    }
}

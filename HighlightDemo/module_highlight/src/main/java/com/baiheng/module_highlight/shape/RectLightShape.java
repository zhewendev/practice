package com.baiheng.module_highlight.shape;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.baiheng.module_highlight.Highlight;

/**
 * 高亮区域为矩形
 */
public class RectLightShape extends BaseLightShape {

    private float mRx = 6;  //矩形边角水平方向半径距离
    private float mRy = 6;  //矩形边角垂直方向半径距离
    public RectLightShape() {
        super();
    }

    /**
     * @param dx
     * @param dy
     * @param blurRadius
     * @param rx
     * @param ry
     */
    public RectLightShape(float dx, float dy, float blurRadius, float rx, float ry) {
        super(dx,dy,blurRadius);
        mRx = rx;
        mRy = ry;
    }

    public RectLightShape(float dx, float dy, float blurRadius) {
        super(dx, dy, blurRadius);
    }

    public RectLightShape(float dx, float dy) {
        super(dx, dy);
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
        canvas.drawRoundRect(viewPosInfo.rectF, mRx, mRy, paint);
    }

    @Override
    protected void resetRectF4Shape(RectF viewPosInfoRectF, float dx, float dy) {
        viewPosInfoRectF.inset(dx,dy);
    }
}

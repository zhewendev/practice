package com.baiheng.module_highlight.shape;

import android.graphics.Bitmap;
import android.graphics.RectF;

import com.baiheng.module_highlight.Highlight;

/**
 * 高亮区域形状基类
 */
public abstract class BaseLightShape implements Highlight.LightShape {
    protected float mDx; //水平方向偏移
    protected float mDy; //垂直方向偏移
    protected  float mBlurRadius = 15;   //模糊半径，默认15

    public BaseLightShape() {

    }

    /**
     * @param dx
     * @param dy
     */
    public BaseLightShape(float dx, float dy) {
        mDx = dx;
        mDy = dy;
    }

    /**
     * @param dx
     * @param dy
     * @param blurRadius
     */
    public BaseLightShape(float dx, float dy, float blurRadius) {
        mDx = dx;
        mDy = dy;
        mBlurRadius = blurRadius;
    }

    @Override
    public void shape(Bitmap bitmap, Highlight.ViewPosInfo viewPosInfo) {
        resetRectF4Shape(viewPosInfo.rectF, mDx, mDy);
        drawShape(bitmap, viewPosInfo);
    }

    /**
     * 重置高亮区域形状的偏移
     * @param viewPosInfoRectF
     * @param dx
     * @param dy
     */
    protected abstract void resetRectF4Shape(RectF viewPosInfoRectF, float dx, float dy);

    /**
     * 使用bitmap绘制高亮区域
     * @param bitmap
     * @param viewPosInfo
     */
    protected abstract void drawShape(Bitmap bitmap, Highlight.ViewPosInfo viewPosInfo);
}

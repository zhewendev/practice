package com.baiheng.module_highlight.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.baiheng.module_highlight.Highlight;

import java.util.List;

/**
 * 高亮蒙层布局视图
 */
public class HighlightView extends FrameLayout {


    private static final PorterDuffXfermode MODE_DST_OUT = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT); // TODO
    private Bitmap mMaskBitmap;
    private Bitmap mLightBitmap;
    private Paint mPaint;
    private List<Highlight.ViewPosInfo> mViewRects;
    private Highlight mHighLight;   //高亮控件
    private LayoutInflater mInflater;   //布局加载器

    private int mMaskColor = 0xCC000000; //蒙层背景色

    private final boolean mIsNext;   //Next模式标志
    private int mPosition = -1; //当前显示的引导布局位置
    private Highlight.ViewPosInfo mViewPosInfo; //当前显示的高亮布局位置信息

    public HighlightView(Context context, Highlight highlight, int maskColor, List<Highlight.ViewPosInfo> viewRects, boolean isNext) {
        super(context);
        mHighLight = highlight;
        mInflater = LayoutInflater.from(context);
        mViewRects = viewRects;
        mMaskColor = maskColor;
        mIsNext = isNext;
        setWillNotDraw(false);  //设置onDraw()是否执行
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void addViewForTip() {
        //判断是否是Next模式
        if (mIsNext) {
            if (mPosition < -1 || mPosition > mViewRects.size() - 1) {
                //重置引导布局位置
                mPosition = 0;
            } else if (mPosition == mViewRects.size() - 1) {
                //移除当前布局
                mHighLight.remove();
                return;
            } else {
                mPosition++;
            }
            mViewPosInfo = mViewRects.get(mPosition);
            //移除所有的引导布局再添加当前位置的引导布局
        }
    }

}

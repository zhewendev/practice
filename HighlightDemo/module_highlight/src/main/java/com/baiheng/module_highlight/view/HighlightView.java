package com.baiheng.module_highlight.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.baiheng.module_highlight.Highlight;

import java.util.List;

/**
 * 高亮蒙层布局视图
 */
public class HighlightView extends FrameLayout {

    private static final PorterDuffXfermode MODE_DST_OUT = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
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

    /**
     * 添加高亮显示view视图
     */
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
            removeAllTips();
            addViewForEveryTip(mViewPosInfo);
        } else {
            for (Highlight.ViewPosInfo viewPosInfo : mViewRects) {
                addViewForEveryTip(viewPosInfo);
            }
        }
    }

    /**
     * 移除当前高亮布局的所有提示
     */
    private void removeAllTips() {
        removeAllViews();
    }

    /**
     * 添加每个高亮布局
     * @param viewPosInfo 高亮布局信息
     */
    private void addViewForEveryTip(Highlight.ViewPosInfo viewPosInfo) {

        View view = mInflater.inflate(viewPosInfo.layoutId, this, false);
        //设置id为layout id,供Highlight查找
        view.setId(viewPosInfo.layoutId);
        LayoutParams lp = buildTipLayoutParams(view, viewPosInfo);

        if (lp == null) {
            return;
        }
        addView(view, lp);
    }

    /**
     * 蒙层引导页图形混合
     */
    private void buildMask() {
        recycleBitmap(mMaskBitmap);
        mMaskBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mMaskBitmap);
        canvas.drawColor(mMaskColor);
        mPaint.setXfermode(MODE_DST_OUT);
        mHighLight.updateInfo();    //TODO

        recycleBitmap(mLightBitmap);
        mLightBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        // 如果是Next模式，添加每个提示布局的背景形状
        if (mIsNext) {
            //添加当前提示布局的高亮形状背景
            addViewEveryShape(mViewPosInfo);
        } else {
            for (Highlight.ViewPosInfo viewPosInfo : mViewRects) {
                addViewEveryShape(viewPosInfo);
            }
        }
        canvas.drawBitmap(mLightBitmap, 0, 0,mPaint);
        mPaint.setXfermode(null);

    }

    /**
     * 主动回收之前创建的bitmap
     * @param bitmap
     */
    private void recycleBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed || mIsNext) {
            buildMask();
            updateTipPos();
        }
    }

    // 更新高亮控件布局位置信息
    private void updateTipPos() {
        //  如果是Next模式，每次只有一个高亮子控件，刷新当前位置的tip
        if (mIsNext) {
            View view = getChildAt(0);
            LayoutParams lp = buildTipLayoutParams(view, mViewPosInfo);
            if (lp == null) {
                return;
            } else {
                view.setLayoutParams(lp);
            }
        } else {
            for (int i = 0, n = getChildCount(); i < n; i++) {
                View view = getChildAt(i);
                Highlight.ViewPosInfo viewPosInfo = mViewRects.get(i);
                LayoutParams lp = buildTipLayoutParams(view, viewPosInfo);
                if (lp == null) {
                    continue;
                } else {
                    view.setLayoutParams(lp);
                }
            }
        }
    }

    /**
     * 设置提示布局参数
     * @param view 高亮布局控件
     * @param viewPosInfo   高亮布局控件信息
     */
    private LayoutParams buildTipLayoutParams(View view, Highlight.ViewPosInfo viewPosInfo) {
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        if (lp.leftMargin == (int) viewPosInfo.marginInfo.leftMargin
            && lp.topMargin == (int) viewPosInfo.marginInfo.topMargin
            && lp.rightMargin == (int) viewPosInfo.marginInfo.rightMargin
            && lp.bottomMargin == (int) viewPosInfo.marginInfo.bottomMargin) {
            return null;
        }

        lp.leftMargin = (int) viewPosInfo.marginInfo.leftMargin;
        lp.topMargin = (int) viewPosInfo.marginInfo.topMargin;
        lp.rightMargin = (int) viewPosInfo.marginInfo.rightMargin;
        lp.bottomMargin = (int) viewPosInfo.marginInfo.bottomMargin;

        if (lp.rightMargin != 0) {
            lp.gravity = Gravity.RIGHT;
        } else {
            lp.gravity = Gravity.LEFT;
        }

        if (lp.bottomMargin != 0) {
            lp.gravity |= Gravity.BOTTOM;
        } else {
            lp.gravity |= Gravity.TOP;
        }
        return lp;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            canvas.drawBitmap(mMaskBitmap, 0, 0, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDraw(canvas);
    }

    /**
     * 添加提示控件布局的背景形状
     * @param viewPosInfo 高亮提示控件布局的位置信息
     */
    private void addViewEveryShape(Highlight.ViewPosInfo viewPosInfo) {
        viewPosInfo.lightShape.shape(mLightBitmap, viewPosInfo);
    }

    /**
     * 判断是否是Next模式
     * @return
     */
    public boolean isNext() {
        return mIsNext;
    }

    /**
     * 获取当前高亮提示布局位置信息
     * @return
     */
    public Highlight.ViewPosInfo getCurrentViewPosInfo() {
        return mViewPosInfo;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        recycleBitmap(mLightBitmap);
        recycleBitmap(mMaskBitmap);
    }
}

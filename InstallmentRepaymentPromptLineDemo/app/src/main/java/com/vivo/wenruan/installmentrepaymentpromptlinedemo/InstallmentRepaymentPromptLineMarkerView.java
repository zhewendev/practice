package com.vivo.wenruan.installmentrepaymentpromptlinedemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 分期还款提示轴自定义控件
 */
public class InstallmentRepaymentPromptLineMarkerView extends View {

    private int  mMarkerSize = 21;
    private int mLineSize = 12;
    private Drawable mBeginLine;
    private Drawable mEndLine;
    private Drawable mMarkerDrawable;

    public InstallmentRepaymentPromptLineMarkerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(attrs);
    }

    public InstallmentRepaymentPromptLineMarkerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.InstallmentRepaymentPromptLineMarkerView);

        mMarkerSize = a.getDimensionPixelSize(R.styleable.InstallmentRepaymentPromptLineMarkerView_common_marker_size, mMarkerSize);

        mLineSize = a.getDimensionPixelSize(R.styleable.InstallmentRepaymentPromptLineMarkerView_common_line_size, mLineSize);

        mBeginLine = a.getDrawable(R.styleable.InstallmentRepaymentPromptLineMarkerView_common_begin_line);

        mEndLine = a.getDrawable(R.styleable.InstallmentRepaymentPromptLineMarkerView_common_end_line);

        mMarkerDrawable = a.getDrawable(R.styleable.InstallmentRepaymentPromptLineMarkerView_common_marker);

        a.recycle();

        if (mBeginLine != null) {
            mBeginLine.setCallback(this);
        }

        if (mEndLine != null) {
            mEndLine.setCallback(this);
        }

        if (mMarkerDrawable != null) {
            mMarkerDrawable.setCallback(this);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec,heightMeasureSpec);

        int w = getPaddingLeft() + getPaddingRight();
        int h = getPaddingTop() + getPaddingBottom();

        if (mMarkerDrawable != null) {
            w += mMarkerSize;
            h += mMarkerSize;
        }

        w = Math.max(w, getMeasuredWidth());
        h = Math.max(h, getMeasuredHeight());


        int widthSize = resolveSizeAndState(w, widthMeasureSpec, 0);
        int heightSize = resolveSizeAndState(h, heightMeasureSpec, 0);

        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initDrawableSize();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBeginLine != null) {
            mBeginLine.draw(canvas);
        }

        if (mEndLine != null) {
            mEndLine.draw(canvas);
        }

        if (mMarkerDrawable != null) {
            mMarkerDrawable.draw(canvas);
        }

        super.onDraw(canvas);
    }

    private void initDrawableSize() {
        int pLeft = getPaddingLeft();
        int pRight = getPaddingRight();
        int pTop = getPaddingTop();
        int pBottom = getPaddingBottom();

        /**
         * 根据父类测量的宽高
         */
        int width = getWidth();
        int height = getHeight();

        /**
         * 圆的宽高大小
         */
        int cWidth = width - pLeft - pRight;
        int cHeight = height - pTop - pBottom;

        Rect bounds;

        if (mMarkerDrawable != null) {
            // 取最小值
            int markerSize = Math.min(mMarkerSize, Math.min(cWidth, cHeight));
            mMarkerDrawable.setBounds(pLeft, pTop, pLeft + markerSize, pTop + markerSize);

            bounds = mMarkerDrawable.getBounds();
        } else {
            //没有数据的时候
            bounds = new Rect(pLeft, pTop, pLeft + cWidth, pTop + cHeight);
        }

        int halfLineSize = mLineSize >> 1;
        int lineTop = bounds.centerY() - halfLineSize;

        /**
         *   设置开始线位置
         */
        if (mBeginLine != null) {
            mBeginLine.setBounds(0, lineTop, bounds.right, lineTop + mLineSize);
        }

        /**
         *   设置结束线位置
         */
        if (mEndLine != null) {
            mEndLine.setBounds(bounds.right, lineTop, width, lineTop + mLineSize);
        }
    }


    /**
     * 设置线的大小
     *
     * @param lineSize
     */
    public void setLineSize(int lineSize) {
        if (mLineSize != lineSize) {
            this.mLineSize = lineSize;
            initDrawableSize();
            invalidate();
        }
    }

    /**
     * 设置中间图片的大小
     *
     * @param markerSize
     */
    public void setMarkerSize(int markerSize) {
        if (this.mMarkerSize != markerSize) {
            mMarkerSize = markerSize;
            initDrawableSize();
            invalidate();
        }
    }

    /**
     * 画图片中左半部分的线 如果设置null，则不画
     */
    public void setBeginLine(Drawable beginLine) {
        if (this.mBeginLine != beginLine) {
            this.mBeginLine = beginLine;
            if (mBeginLine != null) {
                mBeginLine.setCallback(this);
            }
            initDrawableSize();
            invalidate();
        }
    }

    /**
     * 画图片中右半部分的线 如果设置null，则不画
     */
    public void setEndLine(Drawable endLine) {
        if (this.mEndLine != endLine) {
            this.mEndLine = endLine;
            if (mEndLine != null) {
                mEndLine.setCallback(this);
            }
            initDrawableSize();
            invalidate();
        }
    }

    /**
     * 设置中间的图片
     */
    public void setMarkerDrawable(Drawable markerDrawable) {
        if (markerDrawable == null) {
            return;
        }
        if (this.mMarkerDrawable != markerDrawable) {
            this.mMarkerDrawable = markerDrawable;
            if (mMarkerDrawable != null) {
                mMarkerDrawable.setCallback(this);
            }
            initDrawableSize();
            invalidate();
        }
    }

    /**
     * 设置begin线的颜色
     *
     * @param color
     */
    public void setBeginLineColor(int color) {
        if (mBeginLine != null) {
            mBeginLine.setColorFilter(color, PorterDuff.Mode.SRC_OVER);
            invalidate();
        }
    }

    /**
     * 设置end线的颜色
     *
     * @param color
     */
    public void setEndLineColor(int color) {
        if (mEndLine != null) {
            mEndLine.setColorFilter(color, PorterDuff.Mode.SRC_OVER);
            invalidate();
        }
    }

}

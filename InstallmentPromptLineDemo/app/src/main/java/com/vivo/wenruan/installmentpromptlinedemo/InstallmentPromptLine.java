package com.vivo.wenruan.installmentpromptlinedemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Layout;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.math.BigDecimal;
import java.text.NumberFormat;


public class InstallmentPromptLine extends View {


    /**
     * 最小值
     */
    private float mMin;
    /**
     * 最大值
     */
    private float mMax;
    /**
     * 中间分期节点位置
     */
    private float mProgress;
//    /**
//     * 支持浮点型结果输出
//     */
//    private boolean isFloatType;
    /**
     * 分期轨迹的高度
     */
    private int mTrackSize;
    /**
     *滑动节点内圈半径
     */
    private int mNodeRadius;
//    /**
//     *分期节点外圈半径
//     */
//    private int mOuterNodeRadius;
    /**
     * 分期轨迹的颜色
     */
    private int mTrackColor;
    /**
     * 滑动节点颜色
     */
    private int mNodeColor;
//    /**
//     * 当前分期节点外圈边线颜色
//     */
//    private int mOuterNodeBorderColor;
//    /**
//     * 当前分期节点外圈边线大小
//     */
//    private int mOuterNodeBorderSize;
    /**
     * 提示框颜色
     */
    private int mSignColor;
    /**
     * 提示框文本大小
     */
    private int mSignTextSize;
    /**
     * 提示框文本颜色
     */
    private int mSignTextColor;
    /**
     * 提示框高度
     */
    private int mSignHeight;
    /**
     * 提示框宽度
     */
    private int mSignWidth;
    /**
     * 是否显示滑动节点提示框
     */
    private boolean isShowSign;

//    private int mSectionTextPosition = BELOW_SECTION_MARK;
//    /**
//     * 是否显示节点
//     */
//    private boolean isShowSectionMark;
    /**
     * 节点段数（首尾）
     */
    private int mSectionCount;
    /**
     * 是否显示节点文本
     */
    private boolean isShowSectionText;
    /**
     * 显示节点文本大小
     */
    private int mSectionTextSize;
    /**
     * 显示节点文本颜色
     */
    private int mSectionTextColor;
    /**
     * 是否显示滑动节点文本
     */
    private boolean isShowNodeText;
    /**
     * 滑动节点文本大小
     */
    private int mNodeTextSize;
    /**
     * 滑动节点文本颜色
     */
    private int mNodeTextColor;
    /**
     * 提示框文本与轨迹的距离
     */
    private int mTextSpace;
    /**
     * 提示框箭头的高度
     */
    private int mSignArrowHeight;
    /**
     * 提示框箭头的宽度
     */
    private int mSignArrowWidth;
    /**
     * 提示框的圆角大小
     */
    private int mSignRound;
    private int barRoundingRadius = 0;
    /*********************************
     * 节点中心的X坐标
     */
    private float mNodeCenterX;
    private float mTrackLength;
    private float mLeft;
    private float mRight;
    private Paint mPaint;
    private Rect mRectText;
    private RectF roundRectangleBounds;
    private Path trianglePath;
    private Paint signPaint;
    private TextPaint signTextPaint;
    private InstallmentPromptLineConfigBuilder mConfigBuilder;
//    private boolean isSignArrowAutofloat;
    private String unit;
    private TextPaint valueTextPaint;




    /*************************************/
    private float mDelta;
    private float mSectionValue;
    private float mSectionOffset;
    private OnProgressChangedListener mProgressListener;
    private Rect valueSignBounds;
    private Point point1;
    private Point point2;
    private Point point3;
    private StaticLayout valueTextLayout;
    private boolean mReverse;
    private NumberFormat mFormat;
    private OnValueFormatListener mValueFormatListener;




    public InstallmentPromptLine(Context context) {
        this(context, null);
    }

    public InstallmentPromptLine(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InstallmentPromptLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    /**
     * 加载属性
     * @param attrs
     * @param defStyleAttr
     */
    private void init(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.InstallmentPromptLine, defStyleAttr, 0);
        mMin = a.getFloat(R.styleable.InstallmentPromptLine_min, 0.0f);
        mMax = a.getFloat(R.styleable.InstallmentPromptLine_max, 100.0f);
        mProgress = a.getFloat(R.styleable.InstallmentPromptLine_progress, mMin);
//        isFloatType = a.getBoolean(R.styleable.InstallmentPromptLine_is_float_type, false);
        mTrackSize = a.getDimensionPixelSize(R.styleable.InstallmentPromptLine_track_size, InstallmentPromptLineUtils.dp2px(2));
        mNodeRadius = a.getDimensionPixelSize(R.styleable.InstallmentPromptLine_node_radius, mTrackSize + InstallmentPromptLineUtils.dp2px(2));
//        mOuterNodeRadius = a.getDimensionPixelSize(R.styleable.InstallmentPromptLine_outer_node_radius, mTrackSize * 2);
        mTrackColor = a.getColor(R.styleable.InstallmentPromptLine_track_color, ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mNodeColor = a.getColor(R.styleable.InstallmentPromptLine_node_color, mTrackColor);
//        mOuterNodeBorderColor = a.getColor(R.styleable.InstallmentPromptLine_outer_node_border_color, mTrackColor);
//        mOuterNodeBorderSize = a.getDimensionPixelSize(R.styleable.InstallmentPromptLine_outer_node_border_size, InstallmentPromptLineUtils.dp2px(1));
        mSignColor = a.getColor(R.styleable.InstallmentPromptLine_sign_color, mTrackColor);
        mSignTextSize = a.getDimensionPixelSize(R.styleable.InstallmentPromptLine_sign_text_size, InstallmentPromptLineUtils.sp2px(14));
        mSignTextColor = a.getColor(R.styleable.InstallmentPromptLine_sign_text_color, Color.WHITE);
        mSignHeight = a.getDimensionPixelSize(R.styleable.InstallmentPromptLine_sign_height, InstallmentPromptLineUtils.dp2px(32));
        mSignWidth = a.getDimensionPixelSize(R.styleable.InstallmentPromptLine_sign_width, InstallmentPromptLineUtils.dp2px(72));
        isShowSign = a.getBoolean(R.styleable.InstallmentPromptLine_show_sign, false);
//        isShowSectionMark = a.getBoolean(R.styleable.InstallmentPromptLine_show_section_mark, false);
        mSectionCount = a.getInteger(R.styleable.InstallmentPromptLine_section_count, 10);
        isShowSectionText = a.getBoolean(R.styleable.InstallmentPromptLine_show_section_text, false);
        mSectionTextColor = a.getColor(R.styleable.InstallmentPromptLine_section_text_color, mTrackColor);
        mSectionTextSize = a.getDimensionPixelSize(R.styleable.InstallmentPromptLine_section_text_size, InstallmentPromptLineUtils.sp2px(14));
//        isSeekBySection = a.getBoolean(R.styleable.InstallmentPromptLine_seek_by_section, false);
//        int labelsResId = a.getResourceId(R.styleable.InstallmentPromptLine_sides_labels, 0);
        isShowNodeText = a.getBoolean(R.styleable.InstallmentPromptLine_show_node_text, false);
        mNodeTextSize = a.getDimensionPixelSize(R.styleable.InstallmentPromptLine_node_text_size, InstallmentPromptLineUtils.sp2px(14));
        mNodeTextColor = a.getColor(R.styleable.InstallmentPromptLine_node_text_color, mTrackColor);
        mTextSpace = a.getDimensionPixelSize(R.styleable.InstallmentPromptLine_text_space, InstallmentPromptLineUtils.dp2px(2));
        mSignArrowHeight = a.getDimensionPixelSize(R.styleable.InstallmentPromptLine_sign_arrow_height, InstallmentPromptLineUtils.dp2px(3));
        mSignArrowWidth = a.getDimensionPixelSize(R.styleable.InstallmentPromptLine_sign_arrow_width, InstallmentPromptLineUtils.dp2px(5));
        mSignRound = a.getDimensionPixelSize(R.styleable.InstallmentPromptLine_sign_round, InstallmentPromptLineUtils.dp2px(3));
        a.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mRectText = new Rect();

        //初始化提示框
        roundRectangleBounds = new RectF();
        valueSignBounds = new Rect();

        point1 = new Point();
        point2 = new Point();
        point3 = new Point();
        trianglePath = new Path();
        trianglePath.setFillType(Path.FillType.EVEN_ODD);

        initPaint();
        initConfigByPriority();
    }

    private void initPaint() {
        signPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        signPaint.setStyle(Paint.Style.FILL);
        signPaint.setAntiAlias(true);
        signPaint.setColor(mSignColor);

        valueTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        valueTextPaint.setStyle(Paint.Style.FILL);
        valueTextPaint.setTextSize(mSignTextSize);
        valueTextPaint.setColor(mSignTextColor);
    }

    private void initConfigByPriority() {
        mDelta = mMax - mMin;
        mSectionValue = mDelta / mSectionCount; //节点之间数值
        isShowSectionText = true;//、、、、、、、、、、、、可在属性中配置
        setProgress(mProgress);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = mNodeRadius * 2; // 默认高度为滑动节点圆的直径
        if (isShowNodeText) {
            mPaint.setTextSize(mNodeTextSize);
            mPaint.getTextBounds("分期", 0, 1, mRectText);
            height += mRectText.height() + mTextSpace; // 如果显示实时进度，则原来基础上加上进度文字高度和间隔
//            height = Math.max(height, mOuterNodeRadius * 2 + mRectText.height() + mTextSpace);
        }
        if (isShowSectionText) {
            mPaint.setTextSize(mSectionTextSize);
            mPaint.getTextBounds("分期", 0, 4, mRectText);
            height = Math.max(height, mNodeRadius * 2 + mRectText.height() + mTextSpace);
        }
        if (isShowSign) {
            height += mSignHeight;//加上提示框的高度
        }
        setMeasuredDimension(resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec), height);

        mLeft = getPaddingLeft() + mNodeRadius;
        mRight = getMeasuredWidth() - getPaddingRight() - mNodeRadius;

        if (isShowSectionText) {
            String text =  getMinText();
            mPaint.getTextBounds(text, 0, text.length(), mRectText);
            float max = Math.max(mNodeRadius, mRectText.width() / 2f);
            mLeft = getPaddingLeft() + max + mTextSpace;

            text = getMaxText();
            mPaint.getTextBounds(text, 0, text.length(), mRectText);
            max = Math.max(mNodeRadius, mRectText.width() / 2f);
            mRight = getMeasuredWidth() - getPaddingRight() - max - mTextSpace;

        }

        if (isShowSign) {
            mLeft = Math.max(mLeft, getPaddingLeft() + mSignWidth / 2 );
            mRight = Math.min(mRight, getMeasuredWidth() - getPaddingRight() - mSignWidth / 2 );
        }

        mTrackLength = mRight - mLeft;
        mSectionOffset = mTrackLength * 1f / mSectionCount;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float xLeft = getPaddingLeft();
        float xRight = getMeasuredWidth() - getPaddingRight();
        float yTop = getPaddingTop() + mNodeRadius;
        if (isShowSign) {//加上提示框高度
            yTop += mSignHeight;
            xLeft += (mSignWidth / 2 );
            xRight -= (mSignWidth / 2 );
        }
        // draw sectionText
        if (isShowSectionText) {
            mPaint.setTextSize(mSectionTextSize);
            mPaint.setColor( mSectionTextColor);

            float y_ = yTop + mNodeRadius + mTextSpace;
            String text = getMinText();
            mPaint.getTextBounds(text, 0, text.length(), mRectText);
            y_ += mRectText.height();
            xLeft = mLeft;
            canvas.drawText(text, xLeft, y_, mPaint);

            text = getMaxText();
            mPaint.getTextBounds(text, 0, text.length(), mRectText);
            xRight = mRight;
            canvas.drawText(text, xRight, y_, mPaint);
        }

        if (!isShowSectionText && !isShowNodeText) {
            xLeft += mNodeRadius;
            xRight -= mNodeRadius;
        }

        boolean isShowTextBelowSectionMark = true;
        //boolean conditionInterval = mSectionCount % 2 == 0;
        boolean conditionInterval = true;
        drawMark(canvas, xLeft, yTop, isShowTextBelowSectionMark, conditionInterval);
        mNodeCenterX = mTrackLength / mDelta * (mProgress - mMin) + xLeft;
        drawNodeText(canvas, yTop);
        // draw thumbText
//        if (isShowThumbText && !isThumbOnDragging && isTouchToSeekAnimEnd) {
//            drawThumbText(canvas, yTop);
//        }

        // draw track
        mPaint.setColor(mTrackColor);
        mPaint.setStrokeWidth(mTrackSize);
        canvas.drawLine(xLeft, yTop, mNodeCenterX, yTop, mPaint);

        // draw second track
        mPaint.setColor(mTrackColor);
        mPaint.setStrokeWidth(mTrackSize);
        canvas.drawLine(mNodeCenterX, yTop, xRight, yTop, mPaint);

        // draw thumb
        mPaint.setColor(mNodeColor);

        canvas.drawCircle(mNodeCenterX, yTop, mNodeRadius, mPaint);
        //draw sign
        if (!isShowSign) {
            return;
        }
        drawValueSign(canvas, mSignHeight, (int) mNodeCenterX);
    }

    private void drawMark(Canvas canvas, float xLeft, float yTop, boolean isShowTextBelowSectionMark, boolean conditionInterval) {
        float r = (mNodeRadius - InstallmentPromptLineUtils.dp2px(2)) / 2f;
        float junction = mTrackLength / mDelta * Math.abs(mProgress - mMin) + mLeft; // 交汇点
        mPaint.setTextSize(mSectionTextSize);
        mPaint.getTextBounds("0123456789", 0, "0123456789".length(), mRectText); // compute solid height

        float x_;
        float y_ = yTop + mRectText.height() + mNodeRadius + mTextSpace;

        for (int i = 0; i <= mSectionCount; i++) {
            x_ = xLeft + i * mSectionOffset;
            mPaint.setColor(x_ <= junction ? mTrackColor : mTrackColor);
            // sectionMark
            canvas.drawCircle(x_, yTop, r, mPaint);

            // sectionText belows section
            if (isShowTextBelowSectionMark) {
                float m = mMin + mSectionValue * i;
                //不可用，除了当前节点之外的其它节点用不可用颜色表示

                //Log.i("test",mProgress+"========"+m);
                //mPaint.setColor(isEnabled()?mSectionTextColor:mUnusableColor);
                mPaint.setColor(mSectionTextColor);
//                if (mSectionTextInterval > 1) {
//                    if (conditionInterval && i % mSectionTextInterval == 0) {
//                        if (isSidesLabels) {
//                            canvas.drawText(mSidesLabels[i], x_, y_, mPaint);
//                        } else {
//                            canvas.drawText(isFloatType ? float2String(m) : (int) m + "", x_, y_, mPaint);
//                        }
//                    }
//                } else {
//                    if (conditionInterval && i % mSectionTextInterval == 0) {
//                        if (isSidesLabels && i / mSectionTextInterval <= mSidesLabels.length) {
//                            canvas.drawText(mSidesLabels[i / mSectionTextInterval], x_, y_, mPaint);
//                        } else {
//                            canvas.drawText(isFloatType ? float2String(m) : (int) m + "", x_, y_, mPaint);
//                        }
//                    }
//                }
            }
        }
    }

    private void drawNodeText(Canvas canvas, float yTop) {
        mPaint.setColor(mNodeTextColor);
        mPaint.setTextSize(mNodeTextSize);
        mPaint.getTextBounds("0123456789", 0, "0123456789".length(), mRectText); // compute solid height
        float y_ = yTop + mRectText.height() + mOuterNodeRadius + mTextSpace;

//        if (isFloatType || (isShowProgressInFloat && mSectionTextPosition == TextPosition.BOTTOM_SIDES &&
//                mProgress != mMin && mProgress != mMax)) {
//            float progress = getProgressFloat();
//            String value = String.valueOf(progress);
//            if (mFormat != null) {
//                value = mFormat.format(progress);
//            }
//            if (value != null && unit != null && !unit.isEmpty()) {
//                if (!mReverse) {
//                    value += String.format("%s", unit);
//                } else {
//                    value = String.format("%s", unit) + value;
//                }
//            }
//            if (mValueFormatListener != null) value = mValueFormatListener.format(progress);
//            drawSignText(canvas, value, mNodeCenterX, y_, mPaint);
//        } else {
        int progress = getProgress();
        String value = String.valueOf(progress);
        if (mFormat != null) {
            value = mFormat.format(progress);
        }
        if (value != null && unit != null && !unit.isEmpty()) {
            if (!mReverse) {
                value += String.format("%s", unit);
            } else {
                value = String.format("%s", unit) + value;
            }
        }
        if (mValueFormatListener != null) value = mValueFormatListener.format(progress);
        drawSignText(canvas, value, mNodeCenterX, y_, mPaint);
//        }
    }

    public void drawSignText(Canvas canvas, String text, float x, float y, Paint paint) {
        canvas.drawText(text, x, y, paint);
    }
    private void drawValueSign(Canvas canvas, int valueSignSpaceHeight, int valueSignCenter) {
        valueSignBounds.set(valueSignCenter - mSignWidth / 2, getPaddingTop(), valueSignCenter + mSignWidth / 2, mSignHeight - mSignArrowHeight + getPaddingTop());

        int bordersize = isShowSignBorder ? mSignBorderSize : 0;
        // Move if not fit horizontal
        if (valueSignBounds.left < getPaddingLeft()) {
            int difference = -valueSignBounds.left + getPaddingLeft() + bordersize;
            roundRectangleBounds.set(valueSignBounds.left + difference, valueSignBounds.top, valueSignBounds.right +
                    difference, valueSignBounds.bottom);
        } else if (valueSignBounds.right > getMeasuredWidth() - getPaddingRight()) {
            int difference = valueSignBounds.right - getMeasuredWidth() + getPaddingRight() + bordersize;
            roundRectangleBounds.set(valueSignBounds.left - difference, valueSignBounds.top, valueSignBounds.right -
                    difference, valueSignBounds.bottom);
        } else {
            roundRectangleBounds.set(valueSignBounds.left, valueSignBounds.top, valueSignBounds.right,
                    valueSignBounds.bottom);
        }

        canvas.drawRoundRect(roundRectangleBounds, mSignRound, mSignRound, signPaint);
        if (isShowSignBorder) {
            roundRectangleBounds.top = roundRectangleBounds.top + mSignBorderSize / 2;
            canvas.drawRoundRect(roundRectangleBounds, mSignRound, mSignRound, signborderPaint);
        }

        // Draw arrow
        barRoundingRadius = isThumbOnDragging ? mThumbRadiusOnDragging : mThumbRadius;
        int difference = 0;
        if (valueSignCenter - mSignArrowWidth / 2 < barRoundingRadius + getPaddingLeft() + mTextSpace + bordersize) {
            difference = barRoundingRadius - valueSignCenter + getPaddingLeft() + bordersize + mTextSpace;
        } else if (valueSignCenter + mSignArrowWidth / 2 > getMeasuredWidth() - barRoundingRadius - getPaddingRight() - mTextSpace - bordersize) {
            difference = (getMeasuredWidth() - barRoundingRadius) - valueSignCenter - getPaddingRight() - bordersize - mTextSpace;
        }

        point1.set(valueSignCenter - mSignArrowWidth / 2 + difference, valueSignSpaceHeight - mSignArrowHeight + getPaddingTop());
        point2.set(valueSignCenter + mSignArrowWidth / 2 + difference, valueSignSpaceHeight - mSignArrowHeight + getPaddingTop());
        point3.set(valueSignCenter + difference, valueSignSpaceHeight + getPaddingTop());

        drawTriangle(canvas, point1, point2, point3, signPaint);
        if (isShowSignBorder) {
            drawTriangleBoder(canvas, point1, point2, point3, signborderPaint);
        }

        createValueTextLayout();
        // Draw value text
        if (valueTextLayout != null) {
            canvas.translate(roundRectangleBounds.left, roundRectangleBounds.top + roundRectangleBounds.height() / 2 - valueTextLayout.getHeight() / 2);
            valueTextLayout.draw(canvas);
        }
    }

    private void drawTriangle(Canvas canvas, Point point1, Point point2, Point point3, Paint paint) {
        trianglePath.reset();
        trianglePath.moveTo(point1.x, point1.y);
        trianglePath.lineTo(point2.x, point2.y);
        trianglePath.lineTo(point3.x, point3.y);
        trianglePath.lineTo(point1.x, point1.y);
        trianglePath.close();

        canvas.drawPath(trianglePath, paint);
    }

    /**
     * 将三角形的一条顶边用颜色给覆盖掉
     */
    private void drawTriangleBoder(Canvas canvas, Point point1, Point point2, Point point3, Paint paint) {
        triangleboderPath.reset();
        triangleboderPath.moveTo(point1.x, point1.y);
        triangleboderPath.lineTo(point2.x, point2.y);
        paint.setColor(signPaint.getColor());
        float value = mSignBorderSize / 6;
        paint.setStrokeWidth(mSignBorderSize + 1f);
        canvas.drawPath(triangleboderPath, paint);
        triangleboderPath.reset();
        paint.setStrokeWidth(mSignBorderSize);
        triangleboderPath.moveTo(point1.x - value, point1.y - value);
        triangleboderPath.lineTo(point3.x, point3.y);
        triangleboderPath.lineTo(point2.x + value, point2.y - value);
        paint.setColor(mSignBorderColor);
        canvas.drawPath(triangleboderPath, paint);
    }

    /**
     * 设置单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
        createValueTextLayout();
        invalidate();
        requestLayout();
    }

    public void setProgressWithUnit(float progress, String unitHtml) {
        setProgress(progress);
        this.unit = unitHtml;
        createValueTextLayout();
        invalidate();
        requestLayout();
    }

    private void createValueTextLayout() {
        String value = "";
        if (isShowProgressInFloat) {
            float progress = getProgressFloat();
            value = String.valueOf(progress);
            if (mFormat != null) {
                value = mFormat.format(progress);
            }
        } else {
            int progress = getProgress();
            value = String.valueOf(progress);
            if (mFormat != null) {
                value = mFormat.format(progress);
            }
        }
        if (mValueFormatListener == null) {
            if (value != null && unit != null && !unit.isEmpty()) {
                if (!mReverse) {
                    value += String.format(" <small>%s</small> ", unit);
                    //value += String.format("%s", unit);
                } else {
                    value = String.format(" %s ", unit) + value;
                }
            }
        } else {
            value = mValueFormatListener.format(Float.parseFloat(value));
        }
        Spanned spanned = Html.fromHtml(value);
        valueTextLayout = new StaticLayout(spanned, valueTextPaint, mSignWidth, Layout.Alignment.ALIGN_CENTER, 1, 0, false);
    }

    //draw progress text
    private void drawProgressText(Canvas canvas) {
        String value = isShowProgressInFloat ? String.valueOf(getProgressFloat()) : String.valueOf(getProgress());
        //String text = value != null ? formatter.format(value) : valueSegmentText;
        if (value != null && unit != null && !unit.isEmpty())
            value += String.format("%s", unit);
        float mCircle_r = isThumbOnDragging ? mThumbRadiusOnDragging : mThumbRadius;
        Paint mPartTextPaint = mPaint;
        mPartTextPaint.setColor(Color.BLACK);
        mPartTextPaint.setTextSize(25);
        //如果想精确的把文字画在圆圈中心，请使用基于Paint.Align.LEFT完整公式计算方法
        drawCircleText(canvas, mPartTextPaint, mThumbCenterX, getPaddingTop() + mThumbRadiusOnDragging, mCircle_r, value);
    }

    /**
     * 精确画圆圈中心文字（通用方法），其中文字的高度是最难计算适配的，采用此方法，可以完美解决
     *
     * @param canvas  画板
     * @param paint   画笔panit
     * @param centerX 圆圈中心X坐标
     * @param centerY 圆圈中心Y坐标
     * @param radius  半径
     * @param text    显示的文本
     */
    private void drawCircleText(Canvas canvas, Paint paint, float centerX, float centerY, float radius, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        float baseline = centerY - radius + (2 * radius - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(text, centerX - radius + radius - bounds.width() / 2, baseline, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        post(new Runnable() {
            @Override
            public void run() {
                requestLayout();
            }
        });
    }

    float dx;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) return false;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);

                isThumbOnDragging = isThumbTouched(event);
                if (isThumbOnDragging) {
                    if (isSeekBySection && !triggerSeekBySection) {
                        triggerSeekBySection = true;
                    }
                    invalidate();
                } else if (isTouchToSeek && isTrackTouched(event)) {
                    isThumbOnDragging = true;
                    mThumbCenterX = event.getX();
                    if (mThumbCenterX < mLeft) {
                        mThumbCenterX = mLeft;
                    }
                    if (mThumbCenterX > mRight) {
                        mThumbCenterX = mRight;
                    }
                    mProgress = (mThumbCenterX - mLeft) * mDelta / mTrackLength + mMin;
                    invalidate();
                }

                dx = mThumbCenterX - event.getX();

                break;
            case MotionEvent.ACTION_MOVE:
                if (isThumbOnDragging) {
                    mThumbCenterX = event.getX() + dx;
                    if (mThumbCenterX < mLeft) {
                        mThumbCenterX = mLeft;
                    }
                    if (mThumbCenterX > mRight) {
                        mThumbCenterX = mRight;
                    }
                    mProgress = (mThumbCenterX - mLeft) * mDelta / mTrackLength + mMin;
                    invalidate();

                    if (mProgressListener != null) {
                        mProgressListener.onProgressChanged(this, getProgress(), getProgressFloat(), true);
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);

                if (isAutoAdjustSectionMark) {
                    if (isTouchToSeek) {
                        postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isTouchToSeekAnimEnd = false;
                                autoAdjustSection();
                            }
                        }, isThumbOnDragging ? 0 : 300);
                    } else {
                        autoAdjustSection();
                    }
                } else if (isThumbOnDragging || isTouchToSeek) {
                    animate()
                            .setDuration(mAnimDuration)
                            .setStartDelay(!isThumbOnDragging && isTouchToSeek ? 300 : 0)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    isThumbOnDragging = false;
                                    invalidate();

                                    if (mProgressListener != null) {
                                        mProgressListener.onProgressChanged(SignSeekBar.this,
                                                getProgress(), getProgressFloat(), true);
                                    }
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {
                                    isThumbOnDragging = false;
                                    invalidate();
                                }
                            })
                            .start();
                }

                if (mProgressListener != null) {
                    mProgressListener.getProgressOnActionUp(this, getProgress(), getProgressFloat());
                }

                break;
        }

        return isThumbOnDragging || isTouchToSeek || super.onTouchEvent(event);
    }

    /**
     * 计算新的透明度颜色
     *
     * @param color 旧颜色
     * @param ratio 透明度系数
     */
    public int getColorWithAlpha(int color, float ratio) {
        int newColor = 0;
        int alpha = Math.round(Color.alpha(color) * ratio);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        newColor = Color.argb(alpha, r, g, b);
        return newColor;
    }

    /**
     * Detect effective touch of thumb
     */
    private boolean isThumbTouched(MotionEvent event) {
        if (!isEnabled())
            return false;
        float mCircleR = isThumbOnDragging ? mThumbRadiusOnDragging : mThumbRadius;
        float x = mTrackLength / mDelta * (mProgress - mMin) + mLeft;
        float y = getMeasuredHeight() / 2f;
        return (event.getX() - x) * (event.getX() - x) + (event.getY() - y) * (event.getY() - y)
                <= (mLeft + mCircleR) * (mLeft + mCircleR);
    }

    /**
     * Detect effective touch of track
     */
    private boolean isTrackTouched(MotionEvent event) {
        return isEnabled() && event.getX() >= getPaddingLeft() && event.getX() <= getMeasuredWidth() - getPaddingRight()
                && event.getY() >= getPaddingTop() && event.getY() <= getMeasuredHeight() - getPaddingBottom();
    }

    /**
     * Auto scroll to the nearest section mark
     */
    private void autoAdjustSection() {
        int i;
        //计算最近节点位置，mSectionCount：节点个数，mSectionOffset：两个节点间隔距离，mThumbCenterX：滑块中心点位置
        float x = 0;
        for (i = 0; i <= mSectionCount; i++) {
            x = i * mSectionOffset + mLeft;
            if (x <= mThumbCenterX && mThumbCenterX - x <= mSectionOffset) {
                break;
            }
        }

        BigDecimal bigDecimal = BigDecimal.valueOf(mThumbCenterX);
        //BigDecimal setScale保留1位小数，四舍五入，2.35变成2.4
        float x_ = bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
        boolean onSection = x_ == x; // 就在section处，不作valueAnim，优化性能

        AnimatorSet animatorSet = new AnimatorSet();
        ValueAnimator valueAnim = null;
        if (!onSection) {
            if (mThumbCenterX - x <= mSectionOffset / 2f) {
                valueAnim = ValueAnimator.ofFloat(mThumbCenterX, x);
            } else {
                valueAnim = ValueAnimator.ofFloat(mThumbCenterX, (i + 1) * mSectionOffset + mLeft);
            }
            valueAnim.setInterpolator(new LinearInterpolator());
            valueAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mThumbCenterX = (float) animation.getAnimatedValue();
                    mProgress = (mThumbCenterX - mLeft) * mDelta / mTrackLength + mMin;
                    invalidate();

                    if (mProgressListener != null) {
                        mProgressListener.onProgressChanged(SignSeekBar.this, getProgress(), getProgressFloat(), true);
                    }
                }
            });
        }
        if (!onSection) {
            animatorSet.setDuration(mAnimDuration).playTogether(valueAnim);
        }
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgress = (mThumbCenterX - mLeft) * mDelta / mTrackLength + mMin;
                isThumbOnDragging = false;
                isTouchToSeekAnimEnd = true;
                invalidate();

                if (mProgressListener != null) {
                    mProgressListener.getProgressOnFinally(SignSeekBar.this, getProgress(), getProgressFloat(), true);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mProgress = (mThumbCenterX - mLeft) * mDelta / mTrackLength + mMin;
                isThumbOnDragging = false;
                isTouchToSeekAnimEnd = true;
                invalidate();
            }
        });
        animatorSet.start();
    }

    private String getMinText() {
        return isFloatType ? float2String(mMin) : String.valueOf((int) mMin);
    }

    private String getMaxText() {
        return isFloatType ? float2String(mMax) : String.valueOf((int) mMax);
    }

    public float getMin() {
        return mMin;
    }

    public float getMax() {
        return mMax;
    }

    public void setProgress(float progress) {
        mProgress = progress;
        if (mProgressListener != null) {
            mProgressListener.onProgressChanged(this, getProgress(), getProgressFloat(), false);
            mProgressListener.getProgressOnFinally(this, getProgress(), getProgressFloat(), false);
        }
        postInvalidate();
    }

    public int getProgress() {
        if (isSeekBySection && triggerSeekBySection) {
            float half = mSectionValue / 2;

            if (mProgress >= mPreSecValue) { // increasing
                if (mProgress >= mPreSecValue + half) {
                    mPreSecValue += mSectionValue;
                    return Math.round(mPreSecValue);
                } else {
                    return Math.round(mPreSecValue);
                }
            } else { // reducing
                if (mProgress >= mPreSecValue - half) {
                    return Math.round(mPreSecValue);
                } else {
                    mPreSecValue -= mSectionValue;
                    return Math.round(mPreSecValue);
                }
            }
        }

        return Math.round(mProgress);
    }

    public float getProgressFloat() {
        return formatFloat(mProgress);
    }

    public void setOnProgressChangedListener(OnProgressChangedListener onProgressChangedListener) {
        mProgressListener = onProgressChangedListener;
    }

    public void setValueFormatListener(OnValueFormatListener valueFormatListener) {
        mValueFormatListener = valueFormatListener;
    }

    void config(SignConfigBuilder builder) {
        mMin = builder.min;
        mMax = builder.max;
        mProgress = builder.progress;
        isFloatType = builder.floatType;
        mTrackSize = builder.trackSize;
        mSecondTrackSize = builder.secondTrackSize;
        mThumbRadius = builder.thumbRadius;
        mThumbRadiusOnDragging = builder.thumbRadiusOnDragging;
        mTrackColor = builder.trackColor;
        mSecondTrackColor = builder.secondTrackColor;
        mThumbColor = builder.thumbColor;
        mSectionCount = builder.sectionCount;
        isShowSectionMark = builder.showSectionMark;
        isAutoAdjustSectionMark = builder.autoAdjustSectionMark;
        isShowSectionText = builder.showSectionText;
        mSectionTextSize = builder.sectionTextSize;
        mSectionTextColor = builder.sectionTextColor;
        mSectionTextPosition = builder.sectionTextPosition;
        mSectionTextInterval = builder.sectionTextInterval;
        isShowThumbText = builder.showThumbText;
        mThumbTextSize = builder.thumbTextSize;
        mThumbTextColor = builder.thumbTextColor;
        isShowProgressInFloat = builder.showProgressInFloat;
        mAnimDuration = builder.animDuration;
        isTouchToSeek = builder.touchToSeek;
        isSeekBySection = builder.seekBySection;
        mSidesLabels = mConfigBuilder.bottomSidesLabels;
        isSidesLabels = mSidesLabels != null && mSidesLabels.length > 0;
        mThumbBgAlpha = mConfigBuilder.thumbBgAlpha;
        mThumbRatio = mConfigBuilder.thumbRatio;
        isShowThumbShadow = mConfigBuilder.showThumbShadow;
        unit = mConfigBuilder.unit;
        mReverse = mConfigBuilder.reverse;
        mFormat = mConfigBuilder.format;
        mSignColor = builder.signColor;
        mSignTextSize = builder.signTextSize;
        mSignTextColor = builder.signTextColor;
        isShowSign = builder.showSign;
        mSignArrowWidth = builder.signArrowWidth;
        mSignArrowHeight = builder.signArrowHeight;
        mSignRound = builder.signRound;
        mSignHeight = builder.signHeight;
        mSignWidth = builder.signWidth;
        isShowSignBorder = builder.showSignBorder;
        mSignBorderSize = builder.signBorderSize;
        mSignBorderColor = builder.signBorderColor;
        isSignArrowAutofloat = builder.signArrowAutofloat;

        init();
        initConfigByPriority();
        createValueTextLayout();
        if (mProgressListener != null) {
            mProgressListener.onProgressChanged(this, getProgress(), getProgressFloat(), false);
            mProgressListener.getProgressOnFinally(this, getProgress(), getProgressFloat(), false);
        }

        mConfigBuilder = null;

        requestLayout();
    }

    public SignConfigBuilder getConfigBuilder() {
        if (mConfigBuilder == null) {
            mConfigBuilder = new SignConfigBuilder(this);
        }
        mConfigBuilder.min = mMin;
        mConfigBuilder.max = mMax;
        mConfigBuilder.progress = mProgress;
        mConfigBuilder.floatType = isFloatType;
        mConfigBuilder.trackSize = mTrackSize;
        mConfigBuilder.secondTrackSize = mSecondTrackSize;
        mConfigBuilder.thumbRadius = mThumbRadius;
        mConfigBuilder.thumbRadiusOnDragging = mThumbRadiusOnDragging;
        mConfigBuilder.trackColor = mTrackColor;
        mConfigBuilder.secondTrackColor = mSecondTrackColor;
        mConfigBuilder.thumbColor = mThumbColor;
        mConfigBuilder.sectionCount = mSectionCount;
        mConfigBuilder.showSectionMark = isShowSectionMark;
        mConfigBuilder.autoAdjustSectionMark = isAutoAdjustSectionMark;
        mConfigBuilder.showSectionText = isShowSectionText;
        mConfigBuilder.sectionTextSize = mSectionTextSize;
        mConfigBuilder.sectionTextColor = mSectionTextColor;
        mConfigBuilder.sectionTextPosition = mSectionTextPosition;
        mConfigBuilder.sectionTextInterval = mSectionTextInterval;
        mConfigBuilder.showThumbText = isShowThumbText;
        mConfigBuilder.thumbTextSize = mThumbTextSize;
        mConfigBuilder.thumbTextColor = mThumbTextColor;
        mConfigBuilder.showProgressInFloat = isShowProgressInFloat;
        mConfigBuilder.animDuration = mAnimDuration;
        mConfigBuilder.touchToSeek = isTouchToSeek;
        mConfigBuilder.seekBySection = isSeekBySection;
        mConfigBuilder.bottomSidesLabels = mSidesLabels;
        mConfigBuilder.thumbBgAlpha = mThumbBgAlpha;
        mConfigBuilder.thumbRatio = mThumbRatio;
        mConfigBuilder.showThumbShadow = isShowThumbShadow;
        mConfigBuilder.unit = unit;
        mConfigBuilder.reverse = mReverse;
        mConfigBuilder.format = mFormat;
        mConfigBuilder.signColor = mSignColor;
        mConfigBuilder.signTextSize = mSignTextSize;
        mConfigBuilder.signTextColor = mSignTextColor;
        mConfigBuilder.showSign = isShowSign;
        mConfigBuilder.signArrowHeight = mSignArrowHeight;
        mConfigBuilder.signArrowWidth = mSignArrowWidth;
        mConfigBuilder.signRound = mSignRound;
        mConfigBuilder.signHeight = mSignHeight;
        mConfigBuilder.signWidth = mSignWidth;
        mConfigBuilder.showSignBorder = isShowSignBorder;
        mConfigBuilder.signBorderSize = mSignBorderSize;
        mConfigBuilder.signBorderColor = mSignBorderColor;
        mConfigBuilder.signArrowAutofloat = isSignArrowAutofloat;

        return mConfigBuilder;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("save_instance", super.onSaveInstanceState());
        bundle.putFloat("progress", mProgress);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mProgress = bundle.getFloat("progress");
            super.onRestoreInstanceState(bundle.getParcelable("save_instance"));
            setProgress(mProgress);
            return;
        }
        super.onRestoreInstanceState(state);
    }

    private String float2String(float value) {
        return String.valueOf(formatFloat(value));
    }

    private float formatFloat(float value) {
        BigDecimal bigDecimal = BigDecimal.valueOf(value);
        return bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * Listen to progress onChanged, onActionUp, onFinally
     */
    public interface OnProgressChangedListener {

        void onProgressChanged(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser);

        void getProgressOnActionUp(SignSeekBar signSeekBar, int progress, float progressFloat);

        void getProgressOnFinally(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser);
    }

    public interface OnValueFormatListener {
        String format(float progress);
    }

}

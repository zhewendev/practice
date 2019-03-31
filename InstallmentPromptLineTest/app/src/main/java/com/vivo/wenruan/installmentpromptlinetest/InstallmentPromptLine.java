package com.vivo.wenruan.installmentpromptlinetest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class InstallmentPromptLine extends View {
    private static final int DEFAULT_RADIU = 35;
    private static final int DEFAULT_GENERAL_DRAWABLE_ID = R.drawable.icon_check;
    private static final int DEFAULT_FAILED_DRAWABLE_ID = R.drawable.icon_circle;
    private static final int DEFAULT_UNDERWEAR_DRAWABLE_ID = R.drawable.icon_exclamation_mark;

    private int mGeneralDrawableResourceId;
    private int mFailedDrawableResourceId;
    private int mUnderwayDrawableResourceId;
    private int mRadiu;
    private int mLineLength;
    private int mSectionTextSize;
    private boolean mShowSig;
    private int mSignColor;
    private int mSignTextSize;
    private int mSignTextColor;
    private int mSignHeight;
    private int mSignWidth;
    private int mSignArrowHeight;
    private int mSignArrowWidth;
    private int mSignRound;
    private int mSignSpace = 20;
    private int mSignOffset = 20;

    private Bitmap mIconGeneral;
    private Bitmap mIconUnderway;
    private Bitmap mIconFailed;

    private List<InstallmentPromptLineBean> mData = new ArrayList<>();
    private int mWidth; //宽度
    private int mHeight;//高度
    private Rect mGeneralRect;
    private Rect mUnderwayRect;
    private Rect mFailedRect;
    private RectF mSignBoundsRectF;
    private Paint mPaint;
    private Paint mIconPaint;
    private Paint mSignPaint;
    private Point point1;
    private Point point2;
    private Point point3;
    private Path mTrianglePath;

    public InstallmentPromptLine(Context context) {
        this(context, null);
    }

    public InstallmentPromptLine(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InstallmentPromptLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.InstallmentPromptLine);
        mGeneralDrawableResourceId = typedArray.getResourceId(R.styleable.InstallmentPromptLine_common_icon_underway,DEFAULT_GENERAL_DRAWABLE_ID);
        mFailedDrawableResourceId = typedArray.getResourceId(R.styleable.InstallmentPromptLine_common_icon_failed,DEFAULT_FAILED_DRAWABLE_ID);
        mUnderwayDrawableResourceId = typedArray.getResourceId(R.styleable.InstallmentPromptLine_common_icon_general,DEFAULT_UNDERWEAR_DRAWABLE_ID);
        mRadiu = typedArray.getInt(R.styleable.InstallmentPromptLine_common_radius, DEFAULT_RADIU);
        mSectionTextSize = typedArray.getDimensionPixelSize(R.styleable.InstallmentPromptLine_common_section_text_size, sp2px(context, 14));
        mShowSig = typedArray.getBoolean(R.styleable.InstallmentPromptLine_common_show_sign, false);
        mSignColor = typedArray.getColor(R.styleable.InstallmentPromptLine_common_sign_color, ContextCompat.getColor(context, R.color.colorPrimary));
        mSignTextSize = typedArray.getDimensionPixelSize(R.styleable.InstallmentPromptLine_common_sign_text_size, sp2px(context,14));
        mSignTextColor = typedArray.getColor(R.styleable.InstallmentPromptLine_common_sign_text_color,ContextCompat.getColor(context, R.color.colorAccent));
        mSignHeight = typedArray.getDimensionPixelSize(R.styleable.InstallmentPromptLine_common_sign_height, dp2px(context, 30));
        mSignWidth = typedArray.getDimensionPixelSize(R.styleable.InstallmentPromptLine_common_sign_width, dp2px(context, 120));
        mSignArrowHeight = typedArray.getDimensionPixelSize(R.styleable.InstallmentPromptLine_common_sign_arrow_height, dp2px(context, 3));
        mSignArrowWidth = typedArray.getDimensionPixelSize(R.styleable.InstallmentPromptLine_common_sign_arrow_width,dp2px(context, 5));
        mSignRound = typedArray.getDimensionPixelSize(R.styleable.InstallmentPromptLine_common_sign_round, dp2px(context, 3));
        typedArray.recycle();
        init();
    }

    private void init() {
        mIconGeneral = BitmapFactory.decodeResource(getResources(), mGeneralDrawableResourceId);
        mIconUnderway = BitmapFactory.decodeResource(getResources(), mUnderwayDrawableResourceId);
        mIconFailed = BitmapFactory.decodeResource(getResources(), mFailedDrawableResourceId);
        mIconPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIconPaint.setStyle(Paint.Style.FILL);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(4);
        mPaint.setTextSize(mSectionTextSize);
        mSignPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSignPaint.setStyle(Paint.Style.FILL);
        mSignPaint.setAntiAlias(true);
        mSignPaint.setColor(mSignColor);
        mGeneralRect = new Rect(0, 0, mIconGeneral.getWidth(), mIconGeneral.getHeight());
        mUnderwayRect = new Rect(0, 0, mIconUnderway.getWidth(), mIconUnderway.getHeight());
        mFailedRect = new Rect(0, 0, mIconFailed.getWidth(), mIconFailed.getHeight());

        mSignBoundsRectF = new RectF();
        point1 = new Point();
        point2 = new Point();
        point3 = new Point();
        mTrianglePath = new Path();
        mTrianglePath.setFillType(Path.FillType.EVEN_ODD);

    }

    public void setData(List<InstallmentPromptLineBean> data) {
        if (data == null || data.size() == 0) {
            return;
        }
        mData = data;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getPaddingLeft();
        mHeight = getPaddingTop();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mData == null || mData.size() == 0) {
            return;
        }
        int centerX = mWidth + mRadiu;
        int centerY;
        if (mShowSig) {
            centerY = mHeight + mSignHeight + mSignSpace + mRadiu;
        } else {
            centerY =mHeight + mRadiu;
        }
        for (int i = 0; i < mData.size(); i++) {
            InstallmentPromptLineBean item = mData.get(i);
            int bitmapCenter = centerX;
            Rect srcBitmapRect = new Rect(bitmapCenter - mRadiu, centerY - mRadiu, bitmapCenter + mRadiu, centerY + mRadiu);
            canvas.drawBitmap(item.getStatus() == InstallmentPromptLineBean.StepStatus.GENERAL ? mIconGeneral :
                    item.getStatus() == InstallmentPromptLineBean.StepStatus.UNDERWAY ? mIconUnderway :
                            mIconFailed, item.getStatus() == InstallmentPromptLineBean.StepStatus.GENERAL ? mGeneralRect :
                    item.getStatus() == InstallmentPromptLineBean.StepStatus.UNDERWAY ? mUnderwayRect :
                            mFailedRect, srcBitmapRect, mIconPaint);
            float v = mPaint.measureText(item.getName());

            canvas.drawText(item.getName(), bitmapCenter - v / 2, centerY + mRadiu * 2, mPaint);

            if (item.getLineLength() != 0) {
                int lineLeft = centerX + mRadiu;
                mLineLength = item.getLineLength();
                canvas.drawLine(lineLeft, centerY - 2, lineLeft + mLineLength, centerY - 2, mPaint);
            }
            if (item.getIsShowSign()){
                drawSign(canvas, mSignHeight, centerX);
                drawSignText(canvas, centerX,item);
            }
            centerX = centerX + mRadiu * 2 + mLineLength;
        }
    }

    /**
     * 绘制提示框
     * @param canvas 画布
     * @param mSignHeight 提示框高度
     * @param centerX 节点中心坐标
     */
    private void drawSign(Canvas canvas, int mSignHeight, int centerX) {
        mSignBoundsRectF.set(centerX - mSignOffset, mHeight, centerX + mSignWidth - mSignOffset, mSignHeight + mHeight);
        canvas.drawRoundRect(mSignBoundsRectF, mSignRound, mSignRound, mSignPaint);
        point1.set(centerX - mSignArrowWidth / 2, mSignHeight + mHeight);
        point2.set(centerX + mSignArrowWidth / 2, mSignHeight + mHeight);
        point3.set(centerX, mSignHeight + mSignArrowHeight + mHeight);
        drawTriangle(canvas, point1, point2, point3, mSignPaint);
        invalidate();

    }

    /**
     * 绘制提示框上三角形
     * @param canvas
     * @param point1
     * @param point2
     * @param point3
     * @param paint
     */
    private void drawTriangle(Canvas canvas, Point point1, Point point2, Point point3, Paint paint) {
        mTrianglePath.reset();
        mTrianglePath.moveTo(point1.x, point1.y);
        mTrianglePath.lineTo(point2.x, point2.y);
        mTrianglePath.lineTo(point3.x, point3.y);
        mTrianglePath.lineTo(point1.x, point1.y);
        mTrianglePath.close();
        canvas.drawPath(mTrianglePath, paint);
        invalidate();
    }

    /**
     * 绘制提示框中文字
     * @param canvas
     * @param centerX
     * @param item
     */
    private void drawSignText(Canvas canvas,int centerX, InstallmentPromptLineBean item) {
        String str = item.getSignContent();
        Rect mSignTextRect = new Rect();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(mSignTextSize);
        mPaint.setColor(mSignTextColor);
        mPaint.getTextBounds(str, 0, str.length(), mSignTextRect);
        float baseline = Math.abs(mSignTextRect.top) + mHeight + (mSignHeight - mSignTextRect.height()) / 2;
        canvas.drawText(str, centerX, baseline, mPaint);
        invalidate();

    }

    /**
     * sp转换为px
     * @param context
     * @param spValue
     * @return
     */
    public int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * dp转换为px
     * @param context
     * @param dpValue
     * @return
     */
    public int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}

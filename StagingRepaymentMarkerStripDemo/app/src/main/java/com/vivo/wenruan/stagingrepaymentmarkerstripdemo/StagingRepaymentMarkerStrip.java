package com.vivo.wenruan.stagingrepaymentmarkerstripdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.vivo.wenruan.stagingrepaymentmarkerstripdemo.StagingRepaymentMarkerStrip.TextPosition.BELOW_SECTION_MARK;
import static com.vivo.wenruan.stagingrepaymentmarkerstripdemo.StagingRepaymentMarkerStrip.TextPosition.BOTTOM_SIDES;
import static com.vivo.wenruan.stagingrepaymentmarkerstripdemo.StagingRepaymentMarkerStrip.TextPosition.SIDES;

public class StagingRepaymentMarkerStrip extends View {

    static final int NONE = -1;

    @IntDef({NONE, SIDES, BOTTOM_SIDES, BELOW_SECTION_MARK})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TextPosition {
        int SIDES = 0, BOTTOM_SIDES = 1, BELOW_SECTION_MARK = 2;
    }

    /**
     * 分期节点轨迹的高度
     */
    private int mTrackSize;
    /**
     *分期节点内圈半径
     */
    private int mNodeRadius;
    /**
     *分期节点外圈半径
     */
    private int mOuterNodeRadius;
    /**
     * 分期节点轨迹的颜色
     */
    private int mTrackColor;
    /**
     * 分期节点内圈填充颜色
     */
    private int mNodeColor;
    /**
     * 当前分期节点外圈边线颜色
     */
    private int mOuterNodeBorderColor;
    /**
     * 当前分期节点外圈边线大小
     */
    private int mOuterNodeBorderSize;
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
     * 提示框文本高度
     */
    private int mSignHeight;
    /**
     * 提示框文本宽度
     */
    private int mSignWidth;
    /**
     * 是否显示节点提示框
     */
    private boolean isShowSign;
    @TextPosition
    private int mSectionTextPosition = NONE;
    /**
     * 是否显示分界点
     */
    private boolean isShowSectionMark;
    /**
     * 是否显示分界点文本
     */
    private boolean isShowSectionText;
    /**
     * 是否显示当前分期数
     */
    private boolean isShowNodeText;
    /**
     * 节点分期数文本大小
     */
    private int mNodeTextSize;
    /**
     * 节点分期数文本颜色
     */
    private int mNodeTextColor;

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

    public StagingRepaymentMarkerStrip(Context context) {
        this(context, null);
    }

    public StagingRepaymentMarkerStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StagingRepaymentMarkerStrip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    /**
     * 加载属性
     * @param attrs
     * @param defStyleAttr
     */
    private void init(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.StagingRepaymentMarkerStrip, defStyleAttr, 0);
        mTrackSize = a.getDimensionPixelSize(R.styleable.StagingRepaymentMarkerStrip_track_size, SignUtils.dp2px(2));
        mNodeRadius = a.getDimensionPixelSize(R.styleable.StagingRepaymentMarkerStrip_node_radius, mTrackSize + SignUtils.dp2px(2));
        mOuterNodeRadius = a.getDimensionPixelSize(R.styleable.StagingRepaymentMarkerStrip_outer_node_radius, mTrackSize * 2);
        mTrackColor = a.getColor(R.styleable.StagingRepaymentMarkerStrip_track_color, ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mNodeColor = a.getColor(R.styleable.StagingRepaymentMarkerStrip_node_color, mTrackColor);
        mOuterNodeBorderColor = a.getColor(R.styleable.StagingRepaymentMarkerStrip_outer_node_border_color, mTrackColor);
        mOuterNodeBorderSize = a.getDimensionPixelSize(R.styleable.StagingRepaymentMarkerStrip_outer_node_border_size, SignUtils.dp2px(1));
        mSignColor = a.getColor(R.styleable.StagingRepaymentMarkerStrip_sign_color, mTrackColor);
        mSignTextSize = a.getDimensionPixelSize(R.styleable.StagingRepaymentMarkerStrip_sign_text_size, SignUtils.sp2px(14));
        mSignTextColor = a.getColor(R.styleable.StagingRepaymentMarkerStrip_sign_text_color, Color.WHITE);
        mSignHeight = a.getDimensionPixelSize(R.styleable.StagingRepaymentMarkerStrip_sign_height, SignUtils.dp2px(32));
        mSignWidth = a.getDimensionPixelSize(R.styleable.StagingRepaymentMarkerStrip_sign_width, SignUtils.dp2px(72));
        isShowSign = a.getBoolean(R.styleable.StagingRepaymentMarkerStrip_show_sign, false);
        isShowSectionMark = a.getBoolean(R.styleable.StagingRepaymentMarkerStrip_show_section_mark, false);
        isShowSectionText = a.getBoolean(R.styleable.StagingRepaymentMarkerStrip_show_section_text, false);
        int pos = a.getInteger(R.styleable.StagingRepaymentMarkerStrip_section_text_position, NONE);
        if (pos == 0) {
            mSectionTextPosition = SIDES;
        } else if (pos == 1) {
            mSectionTextPosition = TextPosition.BOTTOM_SIDES;
        } else if (pos == 2) {
            mSectionTextPosition = TextPosition.BELOW_SECTION_MARK;
        } else {
            mSectionTextPosition = NONE;
        }
        isShowNodeText = a.getBoolean(R.styleable.StagingRepaymentMarkerStrip_show_node_text, false);
        mNodeTextSize = a.getDimensionPixelSize(R.styleable.StagingRepaymentMarkerStrip_node_text_size, SignUtils.sp2px(14));
        mNodeTextColor = a.getColor(R.styleable.StagingRepaymentMarkerStrip_node_text_color, mTrackColor);
        mTextSpace = a.getDimensionPixelSize(R.styleable.StagingRepaymentMarkerStrip_text_space, SignUtils.dp2px(2));
        mSignArrowHeight = a.getDimensionPixelSize(R.styleable.StagingRepaymentMarkerStrip_sign_arrow_height, SignUtils.dp2px(3));
        mSignArrowWidth = a.getDimensionPixelSize(R.styleable.StagingRepaymentMarkerStrip_sign_arrow_width, SignUtils.dp2px(5));
        mSignRound = a.getDimensionPixelSize(R.styleable.StagingRepaymentMarkerStrip_sign_round, SignUtils.dp2px(3));
        a.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mRectText = new Rect();
        roundRectangleBounds = new RectF();
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

        signTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        signTextPaint.setStyle(Paint.Style.FILL);
        signTextPaint.setTextSize(mSignTextSize);
        signTextPaint.setColor(mSignTextColor);
    }

    private void initConfigByPriority() {
        if (mNodeRadius <= mTrackSize) {
            mNodeRadius = mTrackSize + SignUtils.dp2px(2);
        }
        if (mOuterNodeRadius <= mTrackSize) {
            mOuterNodeRadius = mTrackSize * 2;
        }
        if (mSectionTextPosition != NONE) {
            isShowSectionText = true;
        }
        if (isShowSectionText) {
            if (mSectionTextPosition == NONE) {
                mSectionTextPosition = TextPosition.SIDES;
            }
            if (mSectionTextPosition == TextPosition.BELOW_SECTION_MARK) {
                isShowSectionMark = true;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = mOuterNodeRadius * 2;
        if (isShowNodeText) {
            mPaint.setTextSize(mNodeTextSize);

            height += mRectText.height() = mTextSpace;  //
        }
        if (isShowSectionText && mSectionTextPosition >= TextPosition.BOTTOM_SIDES) {

        }
    }
}

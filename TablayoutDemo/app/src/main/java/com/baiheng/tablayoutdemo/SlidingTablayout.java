package com.baiheng.tablayoutdemo;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class SlidingTablayout extends HorizontalScrollView implements ViewPager.OnPageChangeListener{

    private Context mContext;
    private ViewPager mViewPager;
    private ArrayList<String> mTitles;
    private LinearLayout mTabsContainer;
    private int mCurrentTab;
    private float mCurrentPositionOffset;   //todo
    private int mTabCount;  //tab数
    private Rect mIndicatiorRect = new Rect();  //绘制tab指示器显示
    private Rect mTabRect = new Rect(); //todo
    private GradientDrawable mIndicatorDrawable = new GradientDrawable();   //todo

    private Paint mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);    //todo
    private Paint mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG); //todo
    private Paint mTrianglePaint = new Paint(Paint.ANTI_ALIAS_FLAG);    //todo
    private Path mTrianglePath = new Path();

//    private static final int STYLE_NORMAL = 0;
//    private static final int STYLE_TRIANGLE = 1;
//    private static final int STYLE_BLOCK = 2;
//    private int mIndicatorStyle = STYLE_NORMAL;

    private float mTabPadding;
    private boolean mTabSpaceEqual;
    private float mTabWidth;

    /**indicator **/
    private int mIndicatorColor;
    private float mIndicatorHeight;
    private float mIndicatorWidth;
    private float mIndicatorCornerRadius;
    private float mIndicatorMarginLeft;
    private float mIndicatorMarginTop;
    private float mIndicatorMarginRight;
    private float mIndicatorBottom;
    private float mIndicatorGravity;
    private boolean mIndicatorWidthEqualTitle;

    /** underline **/
    private int mUnderlineColor;
    private float mUnderlineHeight;
    private int mUnderlineGravity;

    /** divider **/
    private int mDividerColor;
    private float mDividerWidth;
    private float mDividerPadding;

    /** title **/
//    private static final int TEXT_BOLD_NONE = 0;
//    private static final int TEXT_BOLD_WHEN_SELECT = 1;
//    private static final int TEXT_BOLD_BOTH = 2;
    private float mTextsize;
    private int mTextSelectColor;
    private int mTextUnselectColor;
    private int mTextBold;
    private boolean mTextAllCaps;   //todo

    private int mLastScrollX;   //todo
    private int mHeight;    //todo
    private boolean mSnapOnTabClick;    //todo

    public SlidingTablayout(Context context) {
        this(context, null, 0);
    }

    public SlidingTablayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingTablayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setFillViewport(true);//设置滚动视图是否可以伸缩其内容以填充视口
//        setWillNotDraw(false);//重写onDraw方法,需要调用这个方法来清除flag
//        setClipChildren(false);
//        setClipToPadding(false);

        mContext = context;


    }


}

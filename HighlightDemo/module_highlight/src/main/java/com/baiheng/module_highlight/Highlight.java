package com.baiheng.module_highlight;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.baiheng.module_highlight.interfaces.HighlightInterface;
import com.baiheng.module_highlight.shape.RectLightShape;
import com.baiheng.module_highlight.utils.ViewUtils;
import com.baiheng.module_highlight.view.HighlightView;

import java.util.ArrayList;
import java.util.List;

/**
 * 蒙层引导页高亮处理
 */
public class Highlight implements HighlightInterface, ViewTreeObserver.OnGlobalLayoutListener {
    //高亮控件的位置信息
    public static class ViewPosInfo {
        public int layoutId = -1;
        public RectF rectF; //控件高亮区域
        public MarginInfo marginInfo;
        public View view;
        public OnPosCallback onPosCallback; //todo
        public LightShape lightShape;   //控件高亮形状
    }
    //高亮控件形状
    public interface LightShape {
        public void shape(Bitmap bitmap, ViewPosInfo viewPosInfo);
    }
    //控件边距信息
    public static class MarginInfo {
        public float topMargin;
        public float leftMargin;
        public float rightMargin;
        public float bottomMargin;

    }
    //
    public static interface OnPosCallback {
        void getPos(float rightMargin, float bottomMargin, RectF rectF, MarginInfo marginInfo);
    }

    private View mAnchor;
    private List<ViewPosInfo> mViewRects;   //高亮控件信息集合
    private Context mContext;
    private HighlightView mHighlightView;   //高亮提示布局视图
//    private HighlightInterface.OnClickCallback mClickCallback;  //Todo

    private boolean mIntercept = true;   //拦截属性，透明层布局后控件效果失效
    private int mMaskColor = 0xCC000000;    //蒙层背景色
    private boolean mAutoRemove = true;  //点击是否自动移除，默认为true
    private boolean mIsNext = false;    //Next模式标志，默认为false
    private boolean mShowing;   //是否显示




    public Highlight(Context context) {
        mContext = context;
        mViewRects = new ArrayList<ViewPosInfo>();
        mAnchor = ((Activity) mContext).findViewById(android.R.id.content);
        registerGlobalLayoutListener();
    }

    /**
     * ??????????????????????
     * @param anchor
     * @return
     */
    public Highlight anchor(View anchor) {
        mAnchor = anchor;
        registerGlobalLayoutListener();
        return this;
    }

    @Override
    public View getAnchor() {
        return mAnchor;
    }

    /**
     * ??????????????
     * @param intercept
     * @return
     */
    public Highlight intercept(boolean intercept) {
        mIntercept = intercept;
        return this;
    }

    public Highlight maskColor(int maskColor) {
        maskColor = mMaskColor;
        return this;
    }

    /**
     * ?????????????????????
     * @param viewId
     * @param decorLayoutId
     * @param onPosCallback
     * @param lightShape
     * @return
     */
    public Highlight addHighlight(int viewId, int decorLayoutId, OnPosCallback onPosCallback, LightShape lightShape) {
        ViewGroup parent = (ViewGroup) mAnchor;
        View view = parent.findViewById(viewId);
        addHighlight(view, decorLayoutId, onPosCallback, lightShape);
        return this;
    }

    /**
     * ???????????????????????????
     * @param view
     * @param decorLayoutId
     * @param onPosCallback
     * @param lightShape
     * @return
     */
    public Highlight addHighlight(View view, int decorLayoutId, OnPosCallback onPosCallback, LightShape lightShape) {

        if (onPosCallback == null && decorLayoutId != -1) {
            throw new IllegalArgumentException("onPosCallback can not be null.");
        }
        ViewGroup parent = (ViewGroup) mAnchor;
        RectF rectF = new RectF(ViewUtils.getLocatinInView(parent, view));
        if (rectF.isEmpty()) {
            return this;
        }
        ViewPosInfo viewPosInfo = new ViewPosInfo();
        viewPosInfo.layoutId = decorLayoutId;
        viewPosInfo.rectF = rectF;
        viewPosInfo.view = view;
        MarginInfo marginInfo = new MarginInfo();
        onPosCallback.getPos(parent.getWidth() - rectF.right, parent.getHeight() - rectF.bottom, rectF, marginInfo);
        viewPosInfo.marginInfo = marginInfo;
        viewPosInfo.onPosCallback = onPosCallback;
        viewPosInfo.lightShape = lightShape == null ? new RectLightShape() : lightShape;
        mViewRects.add(viewPosInfo);
        return this;
    }

    /**
     * ?????????????????
     */
    public void updateInfo() {
        ViewGroup parent = (ViewGroup) mAnchor;
        for (Highlight.ViewPosInfo viewPosInfo : mViewRects) {
            RectF rectF = new RectF(ViewUtils.getLocatinInView(parent, viewPosInfo.view));

            viewPosInfo.rectF = rectF;
            viewPosInfo.onPosCallback.getPos(parent.getWidth() - rectF.right, parent.getHeight() - rectF.bottom,
                    rectF, viewPosInfo.marginInfo);
        }
    }


    /**
     * 蒙层引导页是否展示
     * @return
     */
    public boolean isShowing() {
        return mShowing;
    }

    /**
     * 点击后是否自动移除
     * @param autoRemove
     * @return
     */
    public Highlight autoRemove(boolean autoRemove) {
        mAutoRemove = autoRemove;
        return this;
    }

    /**
     * 获取高亮布局
     * @return
     */
    @Override
    public HighlightView getHightlightView() {
        if (mHighlightView != null) {
            return mHighlightView;
        }
        return mHighlightView = (HighlightView) ((Activity) mContext).findViewById(R.id.high_light_view);
    }

    /**
     * 开启Next模式
     * @return
     */
    public Highlight enableNext() {
        mIsNext = true;
        return this;
    }

    /**
     * 判断是否是Next模式
     * @return
     */
    public boolean isNext() {
        return mIsNext;
    }

    /**
     * 切换到下一个提示布局中
     * @return
     */
    @Override
    public Highlight next() {
        if (getHightlightView() != null) {
            getHightlightView().addViewForTip();
        } else {
            throw new NullPointerException("the HighlightView is null, you must invoke show() before this!");
        }
        return this;
    }

    @Override
    public Highlight show() {
        if (getHightlightView() != null) {
            mHighlightView = getHightlightView();
            //重置当前HighLight对象属性
            mShowing = true;
            mIsNext = mHighlightView.isNext();
            return this;
        }
        //如果View rect 容器为空 直接返回
        if (mViewRects.isEmpty()) {
            return this;
        }
        HighlightView highlightView = new HighlightView(mContext, this, mMaskColor, mViewRects, mIsNext);
        highlightView.setId(R.id.high_light_view);
        if (mAnchor instanceof FrameLayout) {
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) mAnchor).addView(highlightView, ((ViewGroup) mAnchor).getChildCount(), lp);
        } else {
            FrameLayout frameLayout = new FrameLayout(mContext);
            ViewGroup parent = (ViewGroup) mAnchor.getParent();
            parent.removeView(mAnchor);
            parent.addView(frameLayout, mAnchor.getLayoutParams());
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            frameLayout.addView(mAnchor, lp);
            frameLayout.addView(highlightView);
        }

        if (mIntercept) {
            highlightView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mAutoRemove) {
                        remove();
                    }
                }
            });
        }

        highlightView.addViewForTip();
        mHighlightView = highlightView;
        mShowing = true;
        return this;
    }

    @Override
    public Highlight remove() {
        if (getHightlightView() == null) {
            return this;
        }
        ViewGroup parent = (ViewGroup) mHighlightView.getParent();
        if (parent instanceof RelativeLayout || parent instanceof FrameLayout) {
            parent.removeView(mHighlightView);
        } else {
            parent.removeView(mHighlightView);
            View origin = parent.getChildAt(0);
            ViewGroup graParent = (ViewGroup) parent.getParent();
            graParent.removeView(parent);
            graParent.addView(origin, parent.getLayoutParams());
        }
        mHighlightView = null;

        mShowing = false;
        return this;
    }

    /**
     * 为mAnchor注册全局布局监听器
     */
    private void registerGlobalLayoutListener() {
        mAnchor.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    /**
     * 为mAnchor反注册全局布局监听器
     */
    private void unRegisterGlobalLayoutListener() {
        mAnchor.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        unRegisterGlobalLayoutListener();
    }
}

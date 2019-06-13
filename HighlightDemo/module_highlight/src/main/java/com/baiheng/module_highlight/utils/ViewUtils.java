package com.baiheng.module_highlight.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class ViewUtils {

    private static final String FRAGMENT_CON = "NoSaveStateFrameLayout";

    /**
     * 确定视图控件在父布局中的位置
     * @param parent
     * @param child
     * @return
     */
    public static Rect getLocatinInView(View parent, View child) {
        if (child == null || parent == null) {
            throw new IllegalArgumentException("parent and child can not be null.");
        }

        View decorView = null;
        Context context = child.getContext();
        if (context instanceof Activity) {
            decorView = ((Activity) context).getWindow().getDecorView();    //获取程序显示区域
        }
        Rect result = new Rect();
        Rect tmpRect = new Rect();

        View tmpView = child;
        if (child == parent) {
            child.getHitRect(result);   //获取View可点击矩形左、上、右、下边界相对于父View的左顶点的距离（偏移量）
            return result;
        }
        //？？？？？？？？？？？？？？？？？？？？？？？
        while (tmpView != decorView && tmpView != parent) {
            tmpView.getHitRect(tmpRect);
            if (!tmpView.getClass().equals(FRAGMENT_CON)) {
                result.left += tmpRect.left;
                result.top += tmpRect.top;
            }
            tmpView = (View) tmpView.getParent();

            if (tmpView != null && tmpView.getParent() != null && (tmpView.getParent() instanceof ViewPager)) {
                tmpView = (View) tmpView.getParent();
            }
        }
        result.right = result.left + child.getMeasuredWidth();
        result.bottom = result.top + child.getMeasuredHeight();
        return result;
    }
}

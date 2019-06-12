package com.baiheng.module_highlight;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewTreeObserver;

import com.baiheng.module_highlight.interfaces.HighlightInterface;

public class Highlight implements HighlightInterface, ViewTreeObserver.OnGlobalLayoutListener {
    //控件位置信息
    public static class ViewPosInfo {
        public int layoutId = -1;
        public RectF rectF;
        public MarginInfo marginInfo;
        public View view;
        public OnPosCallback onPosCallback;
        public LightShape lightShape;
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
    //控件位置回调
    public static interface OnPosCallback {
        void getPos(float rightMargin, float bottomMargin, RectF rectF, MarginInfo marginInfo);
    }
}

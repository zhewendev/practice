package com.baiheng.module_highlight.interfaces;

import android.view.View;

import com.baiheng.module_highlight.Highlight;
import com.baiheng.module_highlight.view.HighlightView;

public interface HighlightInterface {
    /**
     * 移除
     */
    Highlight remove();

    /**
     * 显示
     */
    Highlight show();

    /**
     * 显示下一个布局
     *
     * @return
     */
    Highlight next();

    /**
     * @return 锚点布局
     */
    View getAnchor();

    /**
     * @return 高亮布局控件
     */
    HighlightView getHightlightView();

//    public static interface OnClickCallback {
//        void onClick();
//    }
//
//    /**
//     * 显示回调监听
//     */
//    public static interface OnShowCallback {
//        /**
//         * @param highlightView 高亮布局控件
//         */
//        void onShow(HighlightView highlightView);
//    }
//
//    /**
//     * 移除回调监听
//     */
//    public static interface OnRemoveCallback {
//        /**
//         * 移除高亮布局
//         */
//        void onRemove();
//    }
//
//    /**
//     * 下一个回调监听 只有Next模式下生效
//     */
//    public static interface OnNextCallback {
//        /**
//         * 监听下一步动作
//         *
//         * @param highlightView 高亮布局控件
//         * @param targetView     高亮目标控件
//         * @param tipView        高亮提示控件
//         */
//        void onNext(HighlightView highlightView, View targetView, View tipView);
//    }
//
//    /**
//     * mAnchor全局布局监听器
//     */
//    public static interface OnLayoutCallback {
//        /**
//         * 布局结束
//         */
//        void onLayouted();
//    }
}

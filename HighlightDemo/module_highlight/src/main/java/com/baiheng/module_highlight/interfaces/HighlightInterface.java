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
    HighlightView getHightLightView();

    public static interface OnClickCallback {
        void onClick();
    }


}

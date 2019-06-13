package com.baiheng.module_highlight.position;

import android.graphics.RectF;

import com.baiheng.module_highlight.Highlight;

/**
 * Created by caizepeng on 16/8/20.
 */
public  class OnBottomPosCallback extends OnBaseCallback{
    public OnBottomPosCallback() {
    }

    public OnBottomPosCallback(float offset) {
        super(offset);
    }

    @Override
    public void getPosition(float rightMargin, float bottomMargin, RectF rectF, Highlight.MarginInfo marginInfo) {
        marginInfo.rightMargin = rightMargin;
        marginInfo.topMargin = rectF.top + rectF.height() + mOffset;
    }

}

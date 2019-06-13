package com.baiheng.module_highlight.position;

import android.graphics.RectF;

import com.baiheng.module_highlight.Highlight;

/**
 * Created by caizepeng on 16/8/20.
 */
public class OnLeftPosCallback extends OnBaseCallback {
    public OnLeftPosCallback() {
    }

    public OnLeftPosCallback(float offset) {
        super(offset);
    }

    @Override
    public void getPosition(float rightMargin, float bottomMargin, RectF rectF, Highlight.MarginInfo marginInfo) {
        marginInfo.rightMargin = rightMargin+rectF.width() + mOffset;
        marginInfo.topMargin = rectF.top;
    }
}

package com.baiheng.module_highlight.position;

import android.graphics.RectF;

import com.baiheng.module_highlight.Highlight;

public abstract class OnBaseCallback implements Highlight.OnPosCallback {

    protected float mOffset;

    public OnBaseCallback() {

    }

    public OnBaseCallback(float offset) {
        mOffset = offset;
    }

    /**
     * @param rightMargin
     * @param bottomMargin
     * @param marginInfo
     */
    public void posOffset(float rightMargin, float bottomMargin, RectF rectF, Highlight.MarginInfo marginInfo ) {

    }

    @Override
    public void getPos(float rightMargin, float bottomMargin, RectF rectF, Highlight.MarginInfo marginInfo) {
        getPosition(rightMargin, bottomMargin, rectF, marginInfo);
        posOffset(rightMargin,bottomMargin,rectF,marginInfo);
    }

    public abstract void getPosition(float rightMargin, float bottomMargin, RectF rectF, Highlight.MarginInfo marginInfo);
}

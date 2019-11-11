package com.baiheng.infocollectiontest;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;


public class ClearEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {
    /**
     * 控件是否有焦点
     */
    private boolean mHasFoucs;
    private String mType;
    private InputListener mInputListener;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private void init() {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        mHasFoucs = hasFocus;
        editWatch();
    }

    public void editWatch() {
        if (mInputListener != null) {
            mInputListener.onTextChange(mType,mHasFoucs,getText().length());
        }
    }

    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int count,
                              int after) {
        editWatch();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        editWatch();
    }

    @Override
    public void afterTextChanged(Editable s) {
        editWatch();
    }

    public void setType(String type) {
        mType = type;
    }

    public String getType() {
        return mType;
    }

    public void setInputListener(InputListener inputListener) {
        mInputListener = inputListener;
    }

    public interface InputListener {
        void onTextChange(String type,boolean show,int length);
    }
}

package com.example.zhewen.testdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class ListenerRecycleView extends RecyclerView implements SecondAdapter.NotifyRefreshListener {

    private View view;

    public ListenerRecycleView(Context context) {
        super(context);
    }

    public ListenerRecycleView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    public ListenerRecycleView(Context context,AttributeSet attributeSet,int defStyle){
        super(context,attributeSet,defStyle);
    }

    @Override
    public void refresh(View view) {
        this.view = view;
    }

    public View getView(){
        return view;
    }
}

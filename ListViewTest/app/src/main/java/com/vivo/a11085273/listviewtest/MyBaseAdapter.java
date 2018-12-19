package com.vivo.a11085273.listviewtest;

import android.widget.BaseAdapter;

import java.util.List;

public abstract class MyBaseAdapter extends BaseAdapter {

    protected List<Object>listData;

    public MyBaseAdapter(List<Object> listData) {
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return this.listData.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

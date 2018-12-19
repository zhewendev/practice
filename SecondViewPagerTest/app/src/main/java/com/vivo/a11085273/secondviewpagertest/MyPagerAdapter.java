package com.vivo.a11085273.secondviewpagertest;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends PagerAdapter {

    private List<View> Listview;
    private List<Integer> drawableList;

    public MyPagerAdapter() {

    }

    public MyPagerAdapter(List<View> view, List<Integer>drawableList) {
        this.Listview = view;
        this.drawableList = drawableList;
    }

    @Override
    public int getCount() {
        return Listview.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = Listview.get(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(drawableList.get(position));

        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(String.valueOf(position));
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(Listview.get(position));
    }
}

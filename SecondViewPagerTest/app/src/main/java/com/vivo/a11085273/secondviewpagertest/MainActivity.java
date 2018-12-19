package com.vivo.a11085273.secondviewpagertest;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int[] drawableIds = new int[] { R.drawable.koala, R.drawable.desert,
            R.drawable.hydran, R.drawable.aaa, R.drawable.ddd };
    private ViewPager viewPager;
    private List<View> Listview = new ArrayList<View>();
    private List<Integer> drawableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.vpager_one);
        initData();
        createPageItems();
        viewPager.setAdapter(new MyPagerAdapter(Listview,drawableList));
        viewPager.addOnPageChangeListener(new CycleScrollOnPageChangeListener(viewPager, Listview));
        // 当滑到第0页时，position 会设置到 views.size() - 2
        // 先对其初始化，防止 setCurrentItem 因为时间延迟而出现闪动
//        viewPager.setCurrentItem(Listview.size() - 2, false);
        viewPager.setVisibility(View.INVISIBLE);
        viewPager.postDelayed(new Runnable() {

            @Override
            public void run() {
                viewPager.setVisibility(View.VISIBLE);
                // 设置初始 position
                viewPager.setCurrentItem(1, false);
            }
        }, 100);

    }

    private void initData() {
        drawableList = new ArrayList<Integer>();
        drawableList.add(drawableIds[drawableIds.length - 1]);
        for (int id : drawableIds) {
            drawableList.add(id);
        }
        drawableList.add(drawableIds[0]);
    }

    private void createPageItems() {
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < drawableList.size(); i++) {
            View view = inflater.inflate(R.layout.viewpager_item, null);
            Listview.add(view);
        }
    }
    /*
    class CycleScrollOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
        }

        @Override
        public void onPageScrolled(int position, float offset, int offsetPixels) {
            if (offset == 0) {
                if (position == 0) {
                    viewPager.setCurrentItem(Listview.size() - 2, false);
                } else if (position == (Listview.size() - 1)) {
                    viewPager.setCurrentItem(1, false);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
    /*
    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = views.get(position);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            imageView.setImageResource(drawableList.get(position));

            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText(String.valueOf(position));
            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    }*/
}

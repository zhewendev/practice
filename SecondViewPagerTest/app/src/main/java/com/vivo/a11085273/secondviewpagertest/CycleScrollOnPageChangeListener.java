package com.vivo.a11085273.secondviewpagertest;

import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

public class CycleScrollOnPageChangeListener implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private List<View> Listview;

    public CycleScrollOnPageChangeListener(ViewPager viewPager, List<View>Listview) {
        this.viewPager = viewPager;
        this.Listview = Listview;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
        if (v == 0) {
            if (i == 0) {
                viewPager.setCurrentItem(Listview.size() - 2, false);
            } else if (i == (Listview.size() - 1)) {
                viewPager.setCurrentItem(1, false);
            }
        }
    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }
}

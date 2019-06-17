package com.baiheng.tablayoutdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnTabSelectListener{

    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "华为","小米","oppo","vivo","苹果","一加","荣耀"
    };
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        for (String title : mTitles) {
            mFragments.add(SimpleCardFragment.getInstance(title));
        }


        View decorView = getWindow().getDecorView();
        ViewPager vp = ViewFindUtils.find(decorView, R.id.vp);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);

        /** 默认 */
        SlidingTabLayout tabLayout_1 = ViewFindUtils.find(decorView, R.id.tl_1);
        /**自定义部分属性*/
        SlidingTabLayout tabLayout_2 = ViewFindUtils.find(decorView, R.id.tl_2);
        /** 字体加粗,大写 */
        SlidingTabLayout tabLayout_3 = ViewFindUtils.find(decorView, R.id.tl_3);
        /** tab固定宽度 */
        SlidingTabLayout tabLayout_4 = ViewFindUtils.find(decorView, R.id.tl_4);
        /** indicator固定宽度 */
        SlidingTabLayout tabLayout_5 = ViewFindUtils.find(decorView, R.id.tl_5);
        /** indicator圆 */
        SlidingTabLayout tabLayout_6 = ViewFindUtils.find(decorView, R.id.tl_6);
        /** indicator矩形圆角 */
        final SlidingTabLayout tabLayout_7 = ViewFindUtils.find(decorView, R.id.tl_7);
        /** indicator三角形 */
        SlidingTabLayout tabLayout_8 = ViewFindUtils.find(decorView, R.id.tl_8);
        /** indicator圆角色块 */
        SlidingTabLayout tabLayout_9 = ViewFindUtils.find(decorView, R.id.tl_9);
        /** indicator圆角色块 */
        SlidingTabLayout tabLayout_10 = ViewFindUtils.find(decorView, R.id.tl_10);

        tabLayout_1.setViewPager(vp);
        tabLayout_2.setViewPager(vp);
        tabLayout_2.setOnTabSelectListener(this);
        tabLayout_3.setViewPager(vp);
        tabLayout_4.setViewPager(vp);
        tabLayout_5.setViewPager(vp);
        tabLayout_6.setViewPager(vp);
        tabLayout_7.setViewPager(vp);
        tabLayout_8.setViewPager(vp);
        tabLayout_9.setViewPager(vp);
        tabLayout_10.setViewPager(vp);

        vp.setCurrentItem(4);

    }

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}

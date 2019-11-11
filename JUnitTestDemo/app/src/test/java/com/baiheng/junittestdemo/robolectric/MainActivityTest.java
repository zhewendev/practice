package com.baiheng.junittestdemo.robolectric;


import com.baiheng.junittestdemo.BuildConfig;
import com.baiheng.junittestdemo.MainActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class, sdk = 23)
public class MainActivityTest {
    @Test
    public void testEmpty() {
//        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
//        Assert.assertNotNull(mainActivity);
    }
}

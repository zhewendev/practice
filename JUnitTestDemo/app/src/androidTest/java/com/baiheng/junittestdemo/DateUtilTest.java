package com.baiheng.junittestdemo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
@RunWith(Parameterized.class)
public class DateUtilTest {


    private String time;

    @Rule
    public MyRule myRule = new MyRule();
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }

    public DateUtilTest (String time) {
        this.time = time;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new String[] {
                "2017-10.17",
                "2017-10.17 16:00:02",
                "2017-11-01 22:11:00"
        });
    }

    @Test(expected = ParseException.class)
    public void dateToStamp() throws Exception {
        DateUtil.dateToStamp(time);
    }

    @Test
    public void stampToDate() {
//        assertEquals("预期时间",DateUtil.stampToDate(1508054402000L));
        assertThat("nihao",both(startsWith("n")).and(endsWith("o")));
    }
//
    @Test
    public void stampToDate1() {
        assertThat("手机号码断言","19854254",new IsMobilePhoneMatcher());
    }
}
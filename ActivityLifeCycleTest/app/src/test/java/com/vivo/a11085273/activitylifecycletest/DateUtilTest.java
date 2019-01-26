package com.vivo.a11085273.activitylifecycletest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class DateUtilTest {

    private String time;
    private long timeStamp;

    public DateUtilTest(String time,long timeStamp) {
        this.time = time;
        this.timeStamp = timeStamp;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
            {"2017-10-15",12L},
            {"2017-10-15 16:00:02",15L},
            {"2017-10",2222L}
        });
    }

//    @Before
//    public void setUp() throws Exception {
//        System.out.println("测试开始");
//        mDate = new Date();
//        mDate.setTime(timeStamp);
//    }

//    @After
//    public void tearDown() throws Exception {
//        System.out.println("测试结束");
//    }

    @Test(expected = ParseException.class)
    public void dateToStamp() throws Exception {
        DateUtil.dateToStamp(time);
//        assertThat("2017",startsWith("20"));
    }

    @Test
    public void stampToDate() {
        assertEquals("2017-10-15 16:00:02",DateUtil.stampToDate(timeStamp));
    }
}
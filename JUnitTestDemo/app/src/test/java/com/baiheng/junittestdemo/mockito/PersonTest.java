package com.baiheng.junittestdemo.mockito;

import android.util.Log;

import com.baiheng.junittestdemo.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PersonTest {

    @Mock
    Person mPerson,mPerson1;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testPersonReturn() {

        when(mPerson.getFood(anyString())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return args[0].toString() + "味道不错";
            }
        });
        when(mPerson.getName()).thenReturn("小明");
//        when(mPerson.getSex()).thenThrow(new NullPointerException("性别不明"));

//       System.out.println(mPerson.getName());
       System.out.println(mPerson.getFood("饺子"));
       System.out.println(mPerson.getFood("面条"));

//        System.out.println(mPerson.getSex());
    }

    @Test
    public void testPersonVerifyAfter() {
        when(mPerson.getAge()).thenReturn(18);
        System.out.println(mPerson.getAge());
        System.out.println(System.currentTimeMillis());
//        verify(mPerson,after(1000)).getAge();
        System.out.println(mPerson.getAge());
        System.out.println(System.currentTimeMillis());

        verify(mPerson,atLeast(2)).getAge();

    }

    @Test
    public void testPersonContains(){

        when(mPerson.getFood(contains("面"))).thenReturn("面条");
        //输出面条
        System.out.println(mPerson.getFood("面粉"));

    }

    @Test
    public void testPersonInOrder() {
        mPerson.setName("小明");
        mPerson.setAge(11);

        mPerson1.setName("小东");
        mPerson1.setAge(18);

        InOrder mInOrder = inOrder(mPerson,mPerson1);


        mInOrder.verify(mPerson).setName("小明");
        mInOrder.verify(mPerson).setAge(11);

        //错误的执行顺序
        mInOrder.verify(mPerson1).setAge(18);
        mInOrder.verify(mPerson1).setName("小东");
    }

}

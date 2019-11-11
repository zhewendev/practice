package com.baiheng.junittestdemo.mockito;

import com.baiheng.junittestdemo.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.ArgumentMatchers.anyString;

public class PersonSpyTest {
    @Spy
    Person mPerson;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testPersonSpy() {
        System.out.println(mPerson.getFood("饺子"));
    }
}

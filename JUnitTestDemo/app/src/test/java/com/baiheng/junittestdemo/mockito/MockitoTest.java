package com.baiheng.junittestdemo.mockito;

import com.baiheng.junittestdemo.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

//@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    @Test
    public void testIsNotNull() {
        assertNotNull(mainActivity);
    }

    @Mock
    MainActivity mainActivity;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
}

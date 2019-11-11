package com.baiheng.junittestdemo.powermock;

import com.baiheng.junittestdemo.bean.Banana;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.powermock.reflect.Whitebox;

//@RunWith(PowerMockRunner.class)
@PrepareForTest({Banana.class})
public class PowerMockTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Test
//    @PrepareForTest({Banana.class})
    public void testStaticMethod() throws Exception {
//        PowerMockito.mockStatic(Banana.class); //Mock静态类
//        Mockito.when(Banana.getColor()).thenReturn("绿色");
//        Assert.assertEquals("绿色",Banana.getColor());

//        Whitebox.setInternalState(Banana.class, "COLOR","红色的");
//        Assert.assertEquals("红色的",Banana.getColor());
        Banana mBanana = PowerMockito.mock(Banana.class);
        PowerMockito.when(mBanana.getBananaInfo()).thenCallRealMethod();
        PowerMockito.when(mBanana,"flavor").thenReturn("苦苦的");
        Assert.assertEquals("苦苦的黄色的",mBanana.getBananaInfo());
        //验证flavor是否调用了一次
        PowerMockito.verifyPrivate(mBanana).invoke("flavor");

    }

    @Test
//    @PrepareForTest({Banana.class})
    public void skipPrivateMethod() {
        Banana mBanana = new Banana();
        //跳过flavor方法
        PowerMockito.suppress(PowerMockito.method(Banana.class,"flavor"));
        Assert.assertEquals("null黄色的",mBanana.getBananaInfo());
    }

    @Test
    public void testChangeParentPrivate() throws Exception{
        Banana mBanana = new Banana();
        MemberModifier.field(Banana.class, "fruit").set(mBanana,"蔬菜");
        Assert.assertEquals("蔬菜",mBanana.getFruit());
    }

    @Test
    public void TestFinalMethod() throws Exception {
        Banana mBanana = PowerMockito.mock(Banana.class);
        PowerMockito.when(mBanana.isLike()).thenReturn(false);
        Assert.assertEquals(false,mBanana.isLike());
    }

    @Test
    public void testNewClass() throws Exception {
        Banana mBanana = PowerMockito.mock(Banana.class);
        PowerMockito.when(mBanana.getBananaInfo()).thenReturn("大香蕉");
        PowerMockito.whenNew(Banana.class).withNoArguments().thenReturn(mBanana);

        Banana newBanana = new Banana();
        Assert.assertEquals("大香蕉",newBanana.getBananaInfo());
    }

}

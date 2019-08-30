package com.baiheng.aidlservicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

/**
 * 抽象主题类
 */
abstract class Subject {
    public  abstract void visit();
}

/**
 * 真实主题类，委托对象
 */
class RealSubject extends Subject {
    @Override
    public void visit() {
        //具体逻辑
    }
}

/**
 * 代理类，代理对象
 */
class ProxySubject extends Subject {

    private RealSubject mSubject;   //持有真实主题类引用

    public ProxySubject(RealSubject realSubject) {
        this.mSubject = realSubject;
    }

    @Override
    public void visit() {
        mSubject.visit();   //调用真实主题类中逻辑方法
    }
}

class Client {
    public static void main(String[] args) {
        //构造真实主题对象，即委托对象
        RealSubject realSubject = new RealSubject();
        //构造代理对象
        ProxySubject proxySubject = new ProxySubject(realSubject);
        //调用代理相关方法
        proxySubject.visit();
    }
}
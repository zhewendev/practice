// IMyAidlInterface.aidl
package com.vivo.a11085273.othertest;
import com.vivo.a11085273.othertest.User;

// Declare any non-default types here with import statements
interface IMyAidlInterface {
    /**
     * 自己添加的方法
      */
    int add(int value1, int value2);
    List<User> addUser(in User user);
}

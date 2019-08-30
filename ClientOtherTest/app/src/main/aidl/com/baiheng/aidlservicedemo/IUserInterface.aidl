// IUserInterface.aidl
package com.baiheng.aidlservicedemo;
import com.baiheng.aidlservicedemo.User;

// Declare any non-default types here with import statements

interface IUserInterface {
    int add(int value1, int value2);
    List<User> addUser(in User user);
}

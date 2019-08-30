package com.baiheng.aidlservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

public class UserService extends Service {

    private ArrayList users;

    public UserService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        users = new ArrayList<User>();
        return iBinder;
    }

    private IBinder iBinder = new IUserInterface.Stub(){
        @Override
        public int add(int value1, int value2) throws RemoteException {
            return value1 + value2;
        }

        @Override
        public List<User> addUser(User user) throws RemoteException {
            users.add(user);
            return users;
        }
    };
}

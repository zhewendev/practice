package com.vivo.a11085273.othertest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class MyAidlService extends Service {

    private ArrayList users;

    public MyAidlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        users = new ArrayList<User>();
        return iBinder;
    }

    private IBinder iBinder = new IMyAidlInterface.Stub(){
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

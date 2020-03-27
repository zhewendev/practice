package com.baiheng.utilsdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import java.util.concurrent.LinkedBlockingQueue;

public class GAIDUtils {
    private static final String TAG = "GAIDUtils";

    /**
     * 获取G A I D （耗时操作）
     * */
    public static GaidInfo getGoogleAdId(Context context) {
        Log.i(TAG, "getGoogleAdId");
        if (Looper.getMainLooper() == Looper.myLooper()) {
            Log.i(TAG, "getGoogleAdId:  Cannot call in the main thread, You must call in the other thread");
            return new GaidInfo("", false);
        }
        try {
            PackageManager pm = context.getPackageManager();
            pm.getPackageInfo("com.android.vending", 0);
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            AdvertisingConnection connection = new AdvertisingConnection();
            try {
                if (context.bindService(intent, connection, Context.BIND_AUTO_CREATE)) {
                    AdvertisingInterface adInterface = new AdvertisingInterface(connection.getBinder());
                    String id = adInterface.getId();
                    if(!TextUtils.isEmpty(id)) {
                        return new GaidInfo(id, adInterface.isLimitAdTrackingEnabled(true));
                    }
                }
            } catch (Exception e) {
                Log.i(TAG,e.toString());
            } finally {
                context.unbindService(connection);
            }
        } catch (Exception ex) {
            Log.i(TAG,ex.toString());
        }
        return new GaidInfo("", false);
    }

    private static final class AdvertisingConnection implements ServiceConnection {
        boolean retrieved = false;
        private final LinkedBlockingQueue<IBinder> queue = new LinkedBlockingQueue<>(1);

        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected: ");
            try {
                this.queue.put(service);
            } catch (InterruptedException localInterruptedException) {
                Log.i(TAG, "onServiceConnected: " + localInterruptedException.toString());
            }
        }

        public void onServiceDisconnected(ComponentName name) {
        }

        IBinder getBinder() throws InterruptedException {
            if (this.retrieved)
                throw new IllegalStateException();
            this.retrieved = true;
            return this.queue.take();
        }
    }

    private static final class AdvertisingInterface implements IInterface {
        private IBinder binder;

        AdvertisingInterface(IBinder pBinder) {
            binder = pBinder;
        }

        public IBinder asBinder() {
            return binder;
        }

        /**
         * 获取gaid
         * @return
         * @throws RemoteException
         */
        public String getId() throws RemoteException {
            Log.i(TAG, "getId");
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            String id;
            try {
                data.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                binder.transact(1, data, reply, 0);
                reply.readException();
                id = reply.readString();
                Log.i(TAG, "getId: =" + id);
            } finally {
                reply.recycle();
                data.recycle();
            }
            return id;
        }

        /**
         * 获取gaid的状态
         * @param paramBoolean
         * @return
         * @throws RemoteException
         */
        public boolean isLimitAdTrackingEnabled(boolean paramBoolean) throws RemoteException {

            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            boolean limitAdTracking;
            try {
                data.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                data.writeInt(paramBoolean ? 1 : 0);
                binder.transact(2, data, reply, 0);
                reply.readException();
                limitAdTracking = 0 != reply.readInt();
            } finally {
                reply.recycle();
                data.recycle();
            }
            return limitAdTracking;
        }



    }

}

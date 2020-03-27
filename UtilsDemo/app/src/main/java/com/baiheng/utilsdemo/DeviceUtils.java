package com.baiheng.utilsdemo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Method;

public class DeviceUtils {
    public final static String TAG = "DeviceUtils";

    public static String getProductSeries(String name) {

        Class systemProperties = null;
        try {
            systemProperties = Class.forName("android.os.SystemProperties");
            Method staticMethod = systemProperties.getDeclaredMethod("get", String.class, String.class);
            Object obj = staticMethod.invoke(null, name, "");
            Log.d(TAG, "proKey: " + name + " value:" + obj.toString());
            return obj.toString();
        } catch (Exception e) {
            Log.e(TAG, "get System error", e);
            return "";
        }

    }


    public static int getAppVersionCode(Context context, String packageName) {

        if (context != null) {
            PackageManager pm = context.getPackageManager();
            if (pm != null) {
                PackageInfo pi;
                try {
                    pi = pm.getPackageInfo(packageName, 0);
                    if (pi != null) {
                        return pi.versionCode;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

//    public static String getVersion(Context context) {
//        PackageManager packageManager = context.getPackageManager();
//        try {
//            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
//            int mVersionCode = packageInfo.ver;
//
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return "V" + mVersionName;
//    }

}

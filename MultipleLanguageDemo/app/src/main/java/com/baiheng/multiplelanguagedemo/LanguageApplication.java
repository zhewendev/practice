package com.baiheng.multiplelanguagedemo;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class LanguageApplication extends Application {

    private RefWatcher refWatcher;
    private static LanguageApplication languageApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        languageApplication = this;
        refWatcher = setUpLeakCanary();
    }

    private RefWatcher setUpLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static LanguageApplication getInstance() {
        return  languageApplication;
    }

    public static RefWatcher getRefWatcher(Context context) {
        LanguageApplication languageApplication = (LanguageApplication) context.getApplicationContext();
        return languageApplication.refWatcher;
    }
}

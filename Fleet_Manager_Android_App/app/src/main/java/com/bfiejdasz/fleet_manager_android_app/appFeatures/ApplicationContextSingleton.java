package com.bfiejdasz.fleet_manager_android_app.appFeatures;

import android.content.Context;

public class ApplicationContextSingleton {
    private static ApplicationContextSingleton instance;
    private Context appContext;

    private ApplicationContextSingleton() {}

    public static synchronized ApplicationContextSingleton getInstance() {
        if (instance == null) {
            instance = new ApplicationContextSingleton();
        }
        return instance;
    }

    public void setAppContext(Context context) {
        appContext = context.getApplicationContext();
    }

    public Context getAppContext() {
        return appContext;
    }
}


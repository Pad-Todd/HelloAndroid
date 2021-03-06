package com.professionalandroid.apps.database;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class DeviceDatabaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}

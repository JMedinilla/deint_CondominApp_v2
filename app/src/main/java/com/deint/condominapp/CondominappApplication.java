package com.deint.condominapp;

import android.app.Application;
import android.content.Context;

import com.deint.condominapp.database.DatabaseHelper;

public class CondominappApplication extends Application {
    private static CondominappApplication instance;

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();

        DatabaseHelper.getInstance().open();
    }
}

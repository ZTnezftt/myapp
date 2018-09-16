package com.example.administrator.whatiseat.Activity;

import android.app.Application;
import android.content.Context;


public class MyApplication extends Application {
    private static Context mContext;
    public static Boolean isFirst=true;
    public static Boolean FirstOpen=true;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getInstance() {
        return mContext;
    }
}

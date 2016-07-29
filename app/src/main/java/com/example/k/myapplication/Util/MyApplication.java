package com.example.k.myapplication.Util;

import android.app.Application;
import android.content.Context;

/**
 * Created by k on 2016/7/28.
 */
public class MyApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }
}

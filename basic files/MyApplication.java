package com.haris.ecommerce;

import android.app.Application;
import android.content.Context;


public class MyApplication extends Application {

    static MyApplication mInstance;
    Context context;

    public MyApplication() {

    }

    public MyApplication(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        Thread.setDefaultUncaughtExceptionHandler(new ThreadHandeling());

        //===========Init. OneSignal




    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        ///MultiDex.install(this);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }


}


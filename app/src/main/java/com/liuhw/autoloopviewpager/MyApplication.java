package com.liuhw.autoloopviewpager;

import android.app.Application;

import com.liuhw.autoloopviewpager.crash.CrashHandler;

/**
 * Created by gary on 17-2-24.
 */

public class MyApplication extends Application {

    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        CrashHandler.getInstance().init(this);
    }

    public MyApplication getInstance() {
        return application;
    }
}

package com.example.fish.lockdemo;

import android.app.Application;
import android.util.Log;

import com.fish.lib.fishlib.log.LogUtil;


/**
 * Created by fish on 15/5/28.
 */
public class LockApplication extends Application {
    public boolean isLock;

    @Override
    public void onCreate() {
        super.onCreate();
       LogUtil.d("app oncreate");
        isLock=true;
    }
}

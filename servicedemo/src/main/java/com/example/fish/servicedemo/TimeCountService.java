package com.example.fish.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.fish.lib.fishlib.log.LogUtil;


public class TimeCountService extends Service {
    private final static String TAG = "main";
    private int count;
    private boolean quit;

    private Thread thread;
    private MyBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        // 声明一个方法，获取TimeCountService里的count值
        public int getCount() {
            return count;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("Service is Created");
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 每间隔一秒count加1 ，直到quit为true
                while (!quit) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        });
        thread.start();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.d("Service is Unbinded");
        return true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d( "Service is started");
        int x=super.onStartCommand(intent, flags, startId);
        LogUtil.d("start command ret"+x);
        return x;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d( "Service is Destroyed");
        this.quit = true;

    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.d( "Service is Binded");
        return binder;
    }
}

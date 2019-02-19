package com.aidl.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.fish.lib.fishlib.log.LogUtil;
import com.fish.lib.fishlib.util.system.SystemUtil;


public class MyService extends Service {
    private boolean quit;

    private Thread thread;
    private int count;

    @Override
    public void onCreate() {
        super.onCreate();
        String TAG="";
        LogUtil.d("onCreate process name " + SystemUtil.getCurProcessName(MyService.this));
        Log.i(TAG, "Service is Created");
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 每间隔一秒count加0.1 ，直到quit为true
                while (!quit) {
                    try {
                        Thread.sleep(100);
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
    public void onDestroy() {
        LogUtil.d("onDestroy process name " + SystemUtil.getCurProcessName(MyService.this));
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.d("onBind process name " + SystemUtil.getCurProcessName(MyService.this));
        return mBinder;
    }

    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {

        @Override
        public int getCount() throws RemoteException {
            return count;
        }

        @Override
        public double complexCal(String str, int t) throws RemoteException {
            LogUtil.d("complexCal real " + SystemUtil.getCurProcessName(MyService.this));
            int  ret=str.hashCode()+t;
            return ret*0.3;
        }
    };

}
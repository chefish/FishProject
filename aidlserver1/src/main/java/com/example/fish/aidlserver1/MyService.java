package com.example.fish.aidlserver1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.aidl.server.IMyAidlInterface;
import com.fish.lib.fishlib.log.LogUtil;
import com.fish.lib.fishlib.util.system.SystemUtil;


public class MyService extends Service {
    private boolean quit;

    private Thread thread;
    private int count;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.thread("service main");
        String TAG="";
        LogUtil.d("onCreate process name " + SystemUtil.getCurProcessName(MyService.this));
        LogUtil.d("Service is Created");
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 每间隔100ms count加1 ，直到quit为true
                while (!quit) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    if(mCallback!=null)
                    {
                        LogUtil.d("callback");
                        try {
                            mCallback.showTime(count);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }else
                    {
                        LogUtil.d("callback null");
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void onDestroy() {
        LogUtil.d("onDestroy process name " + SystemUtil.getCurProcessName(MyService.this));
        quit=true;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.d("onBind process name " + SystemUtil.getCurProcessName(MyService.this));
        return mBinder;
    }
    ICallback mCallback;
    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {

        @Override
        public int getCount() throws RemoteException {
            return count;
        }

        @Override
        public double complexCal(String str, int t) throws RemoteException {
            int  ret=str.hashCode()+t;
            return ret*0.3;
        }

        @Override
        public void setCallback(ICallback call) throws RemoteException {
            LogUtil.d("setcallback"+call);
            LogUtil.thread("set callback");
            mCallback=call;

        }
    };

}
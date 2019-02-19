package com.aidl.client;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.aidl.server.IMyAidlInterface;
import com.fish.lib.fishlib.log.LogUtil;
import com.fish.lib.fishlib.util.system.SystemUtil;

import java.util.concurrent.CountDownLatch;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button bindService;

    private Button unbindService;


    private IMyAidlInterface myAIDLInterface;


    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
                LogUtil.d("process name" + SystemUtil.getCurProcessName(MainActivity.this)+"main: "+SystemUtil.isMainThread(MainActivity.this));
                myAIDLInterface = IMyAidlInterface.Stub.asInterface(service);
                try {
                    int cnt = myAIDLInterface.getCount();
                double db = myAIDLInterface.complexCal("hello world",6);
                LogUtil.d("result is " + cnt);
                LogUtil.d( "complexCal value " + db);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        Button bv = (Button) findViewById(R.id.get_num);
        bv.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        Intent intent = new Intent("com.example.servicetest.MyAIDLService");
//        startService(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bind_service:
                Intent intent = new Intent("com.example.servicetest.MyAIDLService");
                boolean b= bindService(intent, connection, Service.BIND_AUTO_CREATE);

                LogUtil.d("bind result "+b);
                break;
            case R.id.unbind_service:
//                Intent unbindIntent = new Intent(this, com.aidl.server.MyService.class);
                unbindService(connection);
                LogUtil.d("unbind ");
                break;
            default:
                break;
        }

    }
}
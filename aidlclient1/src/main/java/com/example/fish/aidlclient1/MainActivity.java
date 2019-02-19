package com.example.fish.aidlclient1;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aidl.server.IMyAidlInterface;
import com.example.fish.aidlserver1.ICallback;
import com.fish.lib.fishlib.log.LogUtil;
import com.fish.lib.fishlib.util.system.SystemUtil;

public class MainActivity extends Activity implements View.OnClickListener {


    private Button bindService;

    private Button unbindService;


    private IMyAidlInterface myAIDLInterface;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LogUtil.d("handleMessage");
            TextView tv = (TextView) findViewById(R.id.textView);
            tv.setText("" + msg.what);


        }
    };
    private ICallback callback = new ICallback.Stub() {
        @Override
        public void showTime(int x) throws RemoteException {
            LogUtil.d("callback" + x);
            LogUtil.process(getApplicationContext());
            LogUtil.thread("showTime");
            handler.sendEmptyMessage(x);
//
        }
    };

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.d("process name" + SystemUtil.getCurProcessName(MainActivity.this));
            myAIDLInterface = IMyAidlInterface.Stub.asInterface(service);
            LogUtil.thread("onServiceConnected");
            try {
                int cnt = myAIDLInterface.getCount();
                double db = myAIDLInterface.complexCal("hello world", 6);
                LogUtil.d("result is " + cnt);
                LogUtil.d("complexCal value " + db);

                myAIDLInterface.setCallback(callback);

//                myAIDLInterface.setCallback(new ICallback.Stub() {
//                    @Override
//                    public void showTime(int x) throws RemoteException {
//                        LogUtil.d("callback"+x);
////                        TextView tv = (TextView) findViewById(R.id.textView);
////                        tv.setText(""+x);
//                    }
//
//                    @Override
//                    public IBinder asBinder() {
//                        return null;
//                    }
//                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    public String mAction="com.example.servicetest.MyAIDLService";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.thread("main");
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);

        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        Intent intent = new Intent(mAction);
        startService(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bind_service:
                Intent intent = new Intent(mAction);
                boolean b = bindService(intent, connection, Service.BIND_AUTO_CREATE);
                LogUtil.d("bind result " + b);
                break;
            case R.id.unbind_service:
                unbindService(connection);

                LogUtil.d("unbind ");
                break;
            default:
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(mAction);
        LogUtil.d("stopservice");
        stopService(intent);
    }
}
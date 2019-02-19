package com.example.fish.servicedemo;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fish.lib.fishlib.log.LogUtil;
import com.fish.lib.fishlib.util.system.SystemUtil;

/**
 * {@link #get}
 */
public class MainActivity extends Activity {
    private final String TAG = "main";
    private Button bind, unbind, getServiceStatus;
    private TimeCountService.MyBinder binder;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.d("onServiceDisconnected");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.d("onServiceConnected");
            // 取得Service对象中的Binder对象
            binder = (TimeCountService.MyBinder) service;
            LogUtil.fish("ismain " + SystemUtil.isMainThread(MainActivity.this));

        }
    };

    void get() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind = (Button) findViewById(R.id.bind);
        unbind = (Button) findViewById(R.id.unbind);
        getServiceStatus = (Button) findViewById(R.id.getServiceStatus);


        bind.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // 绑定服务到当前activity中
                Intent intent = new Intent();
                // 指定开启服务的action
                intent.setAction("com.time.count");
                bindService(intent, conn, Service.BIND_AUTO_CREATE);
            }
        });
        unbind.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // 解除绑定
                binder = null;
                unbindService(conn);
            }
        });
        getServiceStatus.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (binder != null) {
                    // 通过绑定服务传递的Binder对象，获取Service暴露出来的数据
                    Toast.makeText(MainActivity.this,
                            "Service.Count=" + binder.getCount(),
                            Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Service.Count=" + binder.getCount());
                } else {
                    Toast.makeText(MainActivity.this,
                            "you need bind it",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

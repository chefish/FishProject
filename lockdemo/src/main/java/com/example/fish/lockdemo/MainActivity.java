package com.example.fish.lockdemo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.fishlib.log.LogUtil;

/**
 * 模拟下用密码锁定应用
 */
public class MainActivity extends ActionBarActivity {
   private LockApplication mApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.d("main oncreate");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mApp= (LockApplication) getApplication();
    }

    @Override
    protected void onResume() {
        LogUtil.d("main onresume");
        if(mApp.isLock)
        {
            Intent inttt=new Intent(this,UnlockActivity.class);
            startActivity(inttt);
        }

        super.onResume();
    }
}

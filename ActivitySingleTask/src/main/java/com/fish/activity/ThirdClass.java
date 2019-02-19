package com.fish.activity;

import android.os.Bundle;

import com.fish.lib.fishlib.log.LogUtil;
import com.fish.lib.fishlib.ui.activity.BaseFishActivity;

/**
 * Created by fish on 15/9/25.
 */
public class ThirdClass extends BaseFishActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButtonClick(R.id.click, SecondClass.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d("ThirdClass：onDestroy");
    }
}

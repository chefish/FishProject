package com.fish.activity;

import android.os.Bundle;

import com.fish.lib.fishlib.ui.activity.BaseFishActivity;


public class MainActivity extends BaseFishActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setButtonClick(R.id.btn, SecondActivity.class);

    }

}

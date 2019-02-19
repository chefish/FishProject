package com.recycling.imageview;

import android.os.Bundle;

import com.fish.lib.fishlib.ui.activity.IntroListActivity;


/**
 * Created by fish on 15/6/10.
 */
public class RecyclingImageDemoActivity extends IntroListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addData("singleImage",SingleImageViewActivity.class);
        addData("mutiImage",MutiImageViewActivity.class);

    }
}

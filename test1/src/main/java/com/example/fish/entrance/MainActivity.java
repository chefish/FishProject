package com.example.fish.entrance;

import android.os.Bundle;

import com.animation.AA;
import com.animation.Animation1Activity;
import com.animation.Animation2Activity;
import com.animation.BB;
import com.customview.demo2.CustomViewActivity;

import com.fish.lib.fishlib.ui.activity.IntroListActivity;
import com.listview.ListViewMainActivity;
import com.listview.demo2.ListActivityDemo;
import com.listview.demo1.ListViewWithHeadDemo;
import com.listview.demo3.ListviewWithBaseAdapterActivity;
import com.listview.demo4.ListViewMultiItemActivity;
import com.listview.demo5.ListViewActivityYixin;
import com.recycling.imageview.RecyclingImageDemoActivity;
import com.renderscriptdemo.RsTryActivity;


public class MainActivity extends IntroListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addData("RecyclingImageDemo", RecyclingImageDemoActivity.class);
        addData("renderScriptTry", RsTryActivity.class);
        addData(CustomViewActivity.class);
        addData(ListViewMainActivity.class);
        addData(Animation1Activity.class);
        addData(Animation2Activity.class);
        addData(AA.class);
        addData(BB.class);
    }
}

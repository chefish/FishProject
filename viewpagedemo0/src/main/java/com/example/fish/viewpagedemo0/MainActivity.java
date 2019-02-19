package com.example.fish.viewpagedemo0;

import android.os.Bundle;


import com.fish.lib.fishlib.ui.activity.IntroListActivity;

import demo1.FuncIntroActivity;
import demo2.ScrollActivity;
import demo3.FromNetActivity;


/**
 * 导航的ListActivity，每点击里面一项，都进入某个activity
 */
public class MainActivity extends IntroListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addData("demo1", FuncIntroActivity.class);
        addData("demo2", ScrollActivity.class);
//class        int tt=0
//                ;
//        int dd=77/tt;
        addData("demo3", FromNetActivity.class);
    }
}

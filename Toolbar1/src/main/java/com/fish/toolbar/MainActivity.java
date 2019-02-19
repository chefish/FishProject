package com.fish.toolbar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        设定状态栏的颜色，当版本大于4.4时起作用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(android.R.color.holo_red_light);
            tintManager.setStatusBarTintResource(R.color.aa);
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("mainTitle");// 标题的文字需在setSupportActionBar之前，不然会无效
        mToolbar.setTitleTextColor(Color.parseColor("#ff0000"));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(android.R.drawable.ic_delete);
        mToolbar.setLogo(android.R.drawable.ic_media_play);
    }
}

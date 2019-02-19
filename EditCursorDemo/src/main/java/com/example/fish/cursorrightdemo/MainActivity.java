package com.example.fish.cursorrightdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.fish.lib.fishlib.ui.cursor_right_edit.CursorRightEditText;
import com.fish.lib.fishlib.util.system.ImeUtil;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        CursorRightEditText right= (CursorRightEditText) findViewById(R.id.edit);
//        right.init(14,18,"请输入金额", Color.rgb(0, 255, 0),Color.rgb(0, 0, 0));
//        ImeUtil.showKeyboardDelayed(right, this);
//        getSystemService()
    }



}

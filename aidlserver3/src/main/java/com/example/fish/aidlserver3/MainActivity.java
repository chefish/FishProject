package com.example.fish.aidlserver3;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


/**
 * 这个是在aidlserver2的基础上改的，主要目的是为了传输序列化的对象
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}

package com.example.fish.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;

import com.example.fish.myapplication.R;

/**
 * Created by fish on 15/6/1.
 */
public class Default2Resize extends ActionBarActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.click_edit_up);
    }
}

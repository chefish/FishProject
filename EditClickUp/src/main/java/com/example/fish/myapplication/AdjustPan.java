package com.example.fish.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.fish.myapplication.R;

/**
 * Created by fish on 15/6/1.
 */
public class AdjustPan extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        setContentView(R.layout.test);
    }
}

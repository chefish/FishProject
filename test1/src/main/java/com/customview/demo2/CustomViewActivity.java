package com.customview.demo2;

import android.app.Activity;
import android.os.Bundle;

/**
 * http://blog.csdn.net/hzc543806053/article/details/7672965
 */
public class CustomViewActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
}
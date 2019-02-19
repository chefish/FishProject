package com.example.fish.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.AdjustResize.AdjustResize;
import com.fish.lib.fishlib.ui.util.ButtonUtil;

/**
 * 编辑框，点击，软键盘弹出，布局上移的代码
 */

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonUtil.setLisById(this, R.id.adjustpan, AdjustPan.class).setText("观察adjustPan");
        ButtonUtil.setLisById(this,R.id.adjustresize,AdjustResize.class).setText("观察adjustResize");
        ButtonUtil.setLisById(this,R.id.default2resize,Default2Resize.class).setText("默认选择了adjustResize");
        ButtonUtil.setLisById(this,R.id.adjustresizehidden, AdjustResizeHiddenActivity.class).setText("adjustResize|hidden");
    }

}

package com.fish.text;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fish.lib.fishlib.ui.activity.BaseFishActivity;
import com.fish.lib.fishlib.util.system.ScreenUtil;
import com.fish.lib.fishlib.util.system.ToastUtils;


public class MainActivity extends BaseFishActivity {

    private TextView first;
    private TextView second;
    private View third;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first = (TextView) findViewById(R.id.first);
        second = (TextView) findViewById(R.id.second);
        third = findViewById(R.id.third);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScreenUtil.init(getApplicationContext());
                ToastUtils.showToast(context, "first：" + ScreenUtil.px2dip(first.getHeight())+"second：" + ScreenUtil.px2dip(second.getHeight())+"third：" + ScreenUtil.px2dip(third.getHeight()));

            }
        });
    }
}

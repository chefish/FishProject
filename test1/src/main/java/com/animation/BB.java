package com.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.fish.entrance.R;
import com.fish.lib.fishlib.log.LogUtil;

/**
 * Created by fish on 15/9/6.
 */
public class BB extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bb);

        View bb=findViewById(R.id.aa);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("click");
            }
        });
    }
}

package com.fish.click;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


/**
 * 在处理事件的时候收到点击事件，那么此次点击事件依然是有效的
 */
public class ClickActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button tbv = (Button) findViewById(R.id.text);
        tbv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                process
                Log.d("aa", "click");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("aa", "over");
            }
        });
    }

}

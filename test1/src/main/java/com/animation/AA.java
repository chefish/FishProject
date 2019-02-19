package com.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fish.entrance.R;

/**
 * Created by fish on 15/8/30.
 */
public class AA extends Activity {
    ListView listView;
    TextView textView1;
    TextView textView2;
    View big;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa);
//        textView1 = (TextView) findViewById(R.id.textview1);
//        final View view = findViewById(R.id.linearlayout);
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        view.setBackgroundResource(R.color.teamsns_post_write_send_enable);
//                        textView1.setBackgroundResource(R.color.teamsns_post_write_send_enable);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        view.setBackgroundResource(R.color.teamsns_post_write_send);
//                        textView1.setBackgroundResource(R.color.teamsns_post_write_send);
//                        break;
//                }
//                return false;
//            }
//        });
//
//
//        textView1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        textView1.setBackgroundResource(R.color.teamsns_post_write_send_enable);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        textView1.setBackgroundResource(R.color.teamsns_post_write_send);
//                        break;
//                }
//                return false;
//            }
//        });
    }
}

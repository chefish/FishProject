package com.fish.click;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * 连点三下必崩
 * btn的连续点击是一个固有的问题，所以其实button点击完毕之后可以给500ms时间，不可点击，让他处理点击事件
 */
public class MainActivity extends ActionBarActivity {

    ArrayList<String> aa;
    Button tbv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tbv = (Button) findViewById(R.id.text);
        aa = new ArrayList<>();
        aa.add("a");
        aa.add("b");
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
                aa.remove(0);
                if (aa.size() == 0) {
                    tbv.setVisibility(View.GONE);
                }
            }
        });
    }

}

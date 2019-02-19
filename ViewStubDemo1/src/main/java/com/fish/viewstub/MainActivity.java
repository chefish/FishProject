package com.fish.viewstub;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.fish.lib.fishlib.ui.activity.BaseFishActivity;

/**
 * http://blog.csdn.net/hitlion2008/article/details/6737537
 * viewstub出来的view也可以显示隐藏，所以我觉得viewstub的作用就是减少不必要的infalte，如果某个view不常出现，而且比较大，就可以用viewstub来做，比如很多界面的空白页，大部分时候是不会空白的，所以写个viewstub就好了
 * 但是写viewstub没有直接用visible或gone来的方便，所以我觉得超过10个以上的view布局，而且此布局不一定展现就可以用viewstub
 */
public class MainActivity extends BaseFishActivity {

    public View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButtonClick(R.id.btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (view == null) {
                    ViewStub stub = (ViewStub) findViewById(R.id.viewstub_demo_text);
                    view = stub.inflate();
                    TextView text = (TextView) findViewById(R.id.viewstub_demo_textview);
                    text.setText("The tree of liberty must be refreshed from time to time" +
                            " with the blood of patroits and tyrants! Freedom is nothing but " +
                            "a chance to be better!");

                } else {
                    view.setVisibility(View.VISIBLE);
                }

            }
        });

        setButtonClick(R.id.btnHide, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setVisibility(View.GONE);
            }
        });

    }

}

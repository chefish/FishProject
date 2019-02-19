package com.fish.likeinputmethodeffect;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * http://blog.csdn.net/litefish/article/details/49779871
 * 实现类似输入法的效果，点击弹出，再点击收起，用Relativelayout+ScrollView+LinearLayout实现，跟输入法的adjustResize的类似
 */
public class MainActivity extends ActionBarActivity {
    private  boolean keyboardShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.real_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.keyboard).setVisibility(keyboardShow?View.GONE:View.VISIBLE);
                keyboardShow=!keyboardShow;
            }
        });
    }
}

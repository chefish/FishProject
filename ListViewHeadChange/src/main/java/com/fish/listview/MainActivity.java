package com.fish.listview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * ListviewHead+
 *  ListviewHead基础上，加上对head的修改
 */
public class MainActivity extends ActionBarActivity {

    private ListView listView;
    private Button btn;

    private TextView tv;

    //private List<String> data = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_demo1);
        listView = (ListView) findViewById(R.id.list);
        btn = (Button) findViewById(R.id.btn);

        addHead();
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("dsgdfg");
                tv.setVisibility(tv.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

    }

    /**
     * addHead，headview会随着listview滑动
     */
    private void addHead() {

        View head = LayoutInflater.from(this).inflate(
                R.layout.listview_demo1_headview, null);
        tv = (TextView) head.findViewById(R.id.text2);
        listView.addHeaderView(head);
    }


    private List<String> getData() {

        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        data.add("测试数据5");
        data.add("测试数据6");
        data.add("测试数据7");
        data.add("测试数据8");
        data.add("测试数据9");
        data.add("测试数据10");
        data.add("测试数据11");
        data.add("测试数据12");

        return data;
    }
}
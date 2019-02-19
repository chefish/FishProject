package com.fish.lib.fishlib.ui.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 导航的ListActivity，每点击里面一项，都进入某个activity,没有title
 */
public class IntroListActivity extends ListActivity {

    private List<String> data=new ArrayList<>() ;
    private List<Class> activitys=new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data));
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intt = new Intent(IntroListActivity.this, activitys.get(position));
                startActivity(intt);
            }
        });
    }

    public void addData(String s, Class activity)
    {
        data.add(s);
        activitys.add(activity);
    }

    public void addData(Class activity)
    {
        addData(activity.getSimpleName(),activity);
    }
}

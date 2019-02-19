package com.example.simplegridviewtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * @desc GridView的实例。
 *       GridView包含9格，每一个格子包含“图片”和“文本”2部分。点击每一个格子，显示一个Toast提示语。
 * http://www.cnblogs.com/skywang12345/p/3263724.html,改过很多了
 * @author skywang
 *
 */
public class SimpleGridViewTest extends Activity implements AdapterView.OnItemClickListener{
	private GridView mGridView;
	private String IMAGE_ITEM = "imgage_item";
	private String TEXT_ITEM = "text_item";
    private String[] arrText = new String[]{ 
    		"Picture 1", "Picture 2", "Picture 3", 
    		"Picture 4", "Picture 5", "Picture 6",
            "Picture 7", "Picture 8", "Picture 9"
            };
    private int[] arrImages=new int[]{
    		R.drawable.p1, R.drawable.p2, R.drawable.p3, 
    		R.drawable.p4, R.drawable.p5, R.drawable.p6, 
            R.drawable.p7, R.drawable.p8, R.drawable.p9
            };
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_grid_view_test);

        mGridView = (GridView) findViewById(R.id.gridview);

        SimpleAdapter saImageItems = new SimpleAdapter(this, 
        		getGridViewData(),
                R.layout.grid_view_item,
                new String[] { IMAGE_ITEM, TEXT_ITEM },
                new int[] { R.id.itemImage, R.id.itemText });
        // 设置GridView的adapter。GridView继承于AbsListView。
        mGridView.setAdapter(saImageItems);
        mGridView.setOnItemClickListener(this);
    }
    
    /**
     * 获取GridView的数据
     */
    private List<HashMap<String, Object>> getGridViewData() {
    	List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
    	
    	for (int i=0; i<9; i++) {
        	HashMap<String, Object> map = new HashMap<String, Object>(); 
    		map.put(IMAGE_ITEM, arrImages[i]);
    		map.put(TEXT_ITEM, arrText[i]);
    		list.add(map);
    	}
    	
    	return list;
    }

    /**
     * GridView的点击回调函数
     * 
     * @param adapter  -- GridView对应的dapterView
     * @param view     -- AdapterView中被点击的视图(它是由adapter提供的一个视图)。
     * @param position -- 视图在adapter中的位置。
     * @param rowid    -- 被点击元素的行id。
     */
    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long rowid) {

    	// 根据元素位置获取对应的值
        HashMap<String, Object> item = (HashMap<String, Object>) adapter.getItemAtPosition(position);

        String itemText=(String)item.get(TEXT_ITEM);
        Object object=item.get(IMAGE_ITEM);
        Toast.makeText(this.getApplicationContext(), "You Select "+itemText, Toast.LENGTH_SHORT).show();
	}

}
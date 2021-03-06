package com.example.fish.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.util.Log;
import android.view.View;

import com.example.fish.myapplication.R;

public class InputMethodActivity extends Activity implements View.OnClickListener {
	
	private static final String TAG = "Scroll";
	private EditText mEdit;
	private Button mButton;
	private ScrollView mScrollView;
	private Handler mHandler = new Handler();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        
        mScrollView = (ScrollView) findViewById(R.id.scroll);
        mEdit = (EditText) findViewById(R.id.edit);
        mButton = (Button) findViewById(R.id.button);
        
        mEdit.setOnClickListener(this);
    }
    
	@Override
	public void onClick(View v) {



		//这里必须要给一个延迟，如果不加延迟则没有效果。我现在还没想明白为什么
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				//将ScrollView滚动到底
				mScrollView.fullScroll(View.FOCUS_DOWN);
			}
		}, 100);
		
	}
}
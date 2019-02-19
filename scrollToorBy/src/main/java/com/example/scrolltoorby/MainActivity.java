package com.example.scrolltoorby;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * http://blog.csdn.net/vipzjyno1/article/details/24577023
 */
public class MainActivity extends Activity implements OnClickListener{
	private TextView tv_hi;
	private ImageView img_icon;
	private Button btn_scrollTo;
	private Button btn_scrollBy;
	private Button btn_reset;
	ScrollView mScrollView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}

	private void initView() {
		tv_hi =(TextView)findViewById(R.id.tv_hi);
		img_icon =(ImageView)findViewById(R.id.img_icon);
		mScrollView =(ScrollView)findViewById(R.id.mScrollView);
		btn_scrollTo =(Button)findViewById(R.id.btn_scrollTo);
		btn_scrollBy =(Button)findViewById(R.id.btn_scrollBy);
		btn_reset =(Button)findViewById(R.id.btn_reset);
		btn_scrollTo.setOnClickListener(this);
		btn_scrollBy.setOnClickListener(this);
		btn_reset.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_scrollTo:
			img_icon.scrollTo(-10, -10);
			tv_hi.scrollTo(-10, -10);
			mScrollView.scrollTo();
			break;
		case R.id.btn_scrollBy:
			img_icon.scrollBy(0, -10);
			tv_hi.scrollBy(-10, -10);
			mScrollView.scrollBy();
			break;
		case R.id.btn_reset:
			img_icon.scrollTo(0, 0);
			tv_hi.scrollTo(0, 0);
			mScrollView.reset();
			break;
		default:
			break;
		}
	}
}

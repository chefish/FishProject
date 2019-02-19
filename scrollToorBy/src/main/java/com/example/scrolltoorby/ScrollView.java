package com.example.scrolltoorby;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

public class ScrollView extends RelativeLayout {
	public ImageView img;
	public ScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d("aa","onlayout");
    }

    public ScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ScrollView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context) {
		img = new ImageView(context);
		img.setImageResource(R.drawable.ic_launcher);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		img.setScaleType(ScaleType.CENTER);
		addView(img, params);
	}

	public void scrollTo(){
		img.scrollTo(-10, -10);
	}
	
	public void scrollBy(){
		img.scrollBy(-10, -10);
	}
	
	public void reset(){
		img.scrollTo(0, 0);
	}
}

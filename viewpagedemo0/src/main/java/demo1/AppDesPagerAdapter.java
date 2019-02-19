package demo1;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;


import com.fish.lib.fishlib.log.LogUtil;

import java.util.ArrayList;

public class AppDesPagerAdapter extends PagerAdapter{

	private ArrayList<View> views;
	
	public AppDesPagerAdapter(ArrayList<View> views){	
		this.views = views;
	}
	
	@Override
	public int getCount() {
		return this.views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	public void destroyItem(View container, int position, Object object) {
	    LogUtil.d("destroyItem" + position);
		((ViewPager)container).removeView(views.get(position));
	}
	
	public Object instantiateItem(View container, int position) {
	    LogUtil.d("instantiateItem" + position);
		((ViewPager)container).addView(views.get(position));
		return views.get(position);
	}
}

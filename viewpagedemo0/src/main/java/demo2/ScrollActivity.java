package demo2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.fish.viewpagedemo0.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * 功能介绍页，用viewpage实现,可以自动滚动
 * 
 * @author xmyu
 * 
 */
public class ScrollActivity extends Activity {

    private ViewPager mViewPager;
    // 3个页面指示点
    private ImageView mPageIndicator1;
    private ImageView mPageIndicator2;
    private ImageView mPageIndicator3;
    private final MyHandler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private final WeakReference<ScrollActivity> mActivity;

        private boolean mIsRunning=true;

        public MyHandler(ScrollActivity activity) {
            mActivity = new WeakReference<ScrollActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mIsRunning) {
                ScrollActivity activity = mActivity.get();
                activity.mViewPager.setCurrentItem((activity.mViewPager.getCurrentItem()+1)%3);
                if (activity != null) {
                    activity.mHandler.sendEmptyMessageDelayed(1, 5000);
                }
            }
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_des1);
        mViewPager = (ViewPager) findViewById(R.id.app_des_viewpager);

        mViewPager.setOnPageChangeListener(new PageChangeListener());
        mPageIndicator1 = (ImageView) findViewById(R.id.pageIndicator1);
        mPageIndicator2 = (ImageView) findViewById(R.id.pageIndicator2);
        mPageIndicator3 = (ImageView) findViewById(R.id.pageIndicator3);

        LayoutInflater inflater = LayoutInflater.from(this);
        View page1 = inflater.inflate(R.layout.app_des_gallery_one, null);
        View page2 = inflater.inflate(R.layout.app_des_gallery_two, null);
        View page3 = inflater.inflate(R.layout.app_des_gallery_three, null);

        final ArrayList<View> views = new ArrayList<View>();
        views.add(page1);
        views.add(page2);
        views.add(page3);

        // 填充ViewPager的数据适配器
        scrollPagerAdapter mPageIndicatorrAdapter = new scrollPagerAdapter(views);
        mViewPager.setAdapter(mPageIndicatorrAdapter);
        mHandler.sendEmptyMessageDelayed(1, 1000);

    }

    private class PageChangeListener implements OnPageChangeListener {

        public void onPageSelected(int page) {
            // 翻页时当前page,改变当前状态点
            switch (page) {
                case 0:
                    mPageIndicator1.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_on));
                    mPageIndicator2.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    mPageIndicator3.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    break;
                case 1:
                    mPageIndicator2.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_on));
                    mPageIndicator1.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    mPageIndicator3.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    break;
                case 2:
                    mPageIndicator3.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_on));
                    mPageIndicator2.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    // mPageIndicator4.setImageDrawable(getResources().getDrawable(R.drawable.page_spot_off));
                    break;
            // case 3:
            // mPageIndicator4.setImageDrawable(getResources().getDrawable(R.drawable.page_spot_on));
            // mPageIndicator3.setImageDrawable(getResources().getDrawable(R.drawable.page_spot_off));
            // break;

            }
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {}

        public void onPageScrollStateChanged(int arg0) {}
    }


}

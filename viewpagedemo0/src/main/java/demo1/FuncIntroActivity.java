package demo1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.fish.viewpagedemo0.R;

import java.util.ArrayList;

/**
 * 功能介绍页，用viewpage实现
 * @author xmyu
 *
 */
public class FuncIntroActivity extends Activity {

    private ViewPager mViewPager;
    // 3个页面指示点
    private ImageView mPageIndicator1;
    private ImageView mPageIndicator2;
    private ImageView mPageIndicator3;
    private ImageView mPageIndicator4;
    private ImageView mPageIndicator5;
    

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_des);
        mViewPager = (ViewPager) findViewById(R.id.app_des_viewpager);

        mViewPager.setOnPageChangeListener(new PageChangeListener());
        mPageIndicator1 = (ImageView) findViewById(R.id.pageIndicator1);
        mPageIndicator2 = (ImageView) findViewById(R.id.pageIndicator2);
        mPageIndicator3 = (ImageView) findViewById(R.id.pageIndicator3);
        mPageIndicator4 = (ImageView) findViewById(R.id.pageIndicator4);
        mPageIndicator5 = (ImageView) findViewById(R.id.pageIndicator5);
        

        LayoutInflater inflater = LayoutInflater.from(this);
        View page1 = inflater.inflate(R.layout.app_des_gallery_one, null);
        View page2 = inflater.inflate(R.layout.app_des_gallery_two, null);
        View page3 = inflater.inflate(R.layout.app_des_gallery_three, null);
        View page4 = inflater.inflate(R.layout.app_des_gallery_four, null);
        View page5 = inflater.inflate(R.layout.app_des_gallery_five, null);
        
        final ArrayList<View> views = new ArrayList<View>();
        views.add(page1);
        views.add(page2);
        views.add(page3);
        views.add(page4);
        views.add(page5);
        

        // 填充ViewPager的数据适配器
        AppDesPagerAdapter mPageIndicatorrAdapter = new AppDesPagerAdapter(views);
        mViewPager.setAdapter(mPageIndicatorrAdapter);

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

                    mPageIndicator4.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    mPageIndicator5.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    break;
                case 1:
                    
                    mPageIndicator1.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    mPageIndicator2.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_on));
                    mPageIndicator3.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));

                    mPageIndicator4.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    mPageIndicator5.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    break;
                case 2:
                    mPageIndicator1.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    mPageIndicator2.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    mPageIndicator3.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_on));

                    mPageIndicator4.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    mPageIndicator5.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    break;
                case 3:
                    mPageIndicator1.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    mPageIndicator2.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    mPageIndicator3.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));

                    mPageIndicator4.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_on));
                    mPageIndicator5.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    break;
                case 4:
                    mPageIndicator1.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    mPageIndicator2.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    mPageIndicator3.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));

                    mPageIndicator4.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_off));
                    mPageIndicator5.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_guide_point_on));
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

package com.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fish.entrance.R;
import com.fish.lib.fishlib.log.LogUtil;

/**
 * Created by fish on 15/8/14.
 */
public class Animation1Activity extends Activity {
    ImageView textview;
    Button btn;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_relative_activity);
        textview = (ImageView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.btn);
        float curTranslationX = textview.getTranslationY();
        LogUtil.d("" + curTranslationX);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                textview.measure(width, height);
                int x = textview.getMeasuredHeight();
                LogUtil.d("x measure" + x);
                float curTranslationX = textview.getTranslationY();
                //这不就是一点点爬上来的吗？
                ObjectAnimator animator = ObjectAnimator.ofFloat(textview, "translationY", 1000, 0);
                animator.setDuration(4000);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        textview.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator.start();

            }
        }, 2000);


    }
}

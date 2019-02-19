package com.fish.animation1;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;


/**
 * 动画
 */
public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa);
    }

    //最简单的
    public void rotateyAnimRun(View view) {
        ObjectAnimator.ofFloat(view, "rotation", 0.0F, -180.0F).setDuration(500).start();
    }


    //同时的动画
    public void rotateyAnimRun1(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(1000).start();
    }


    /**
     * 动画衔接
     *
     * @param view
     */
    public void rotateyAnimRun2(View view) {
//       放大5%   向上移动8dp  透明度由100%变为0%    动画时长150毫秒
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                1.05f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                1.05f);
        PropertyValuesHolder pvhB = PropertyValuesHolder.ofFloat("Y", view.getY(),
                view.getY() - 24);

        ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ, pvhB);
        anim.setDuration(150);

        PropertyValuesHolder pvhX1 = PropertyValuesHolder.ofFloat("alpha",
                0f, 1f);
        PropertyValuesHolder pvhY1 = PropertyValuesHolder.ofFloat("scaleX",
                1.05f, 1);
        PropertyValuesHolder pvhZ1 = PropertyValuesHolder.ofFloat("scaleY",
                1.05f, 1);
        PropertyValuesHolder pvhB1 = PropertyValuesHolder.ofFloat("Y",
                view.getY() - 24, view.getY());

        ObjectAnimator anim1 = ObjectAnimator.ofPropertyValuesHolder(view, pvhX1, pvhY1, pvhZ1, pvhB1);
        anim.setDuration(50);


        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).after(anim);

        animSet.start();


    }


    //TranslationY而不是用getY,避免API LEVEL>11的限制
    public void rotateyAnimRun3(View view) {
//        放大5%   向上移动8dp  透明度由100%变为0%    动画时长150毫秒
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                8f, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                8f, 1f);
        PropertyValuesHolder pvhB = PropertyValuesHolder.ofFloat("TranslationY", 0,
                -24, 0);

        ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(view, pvhB);
        anim.setDuration(1000).start();

    }

    //TranslateAnimation用法
    public void rotateyAnimRun4(View view) {
        TranslateAnimation animation = new TranslateAnimation(0, 0, 300, 300);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(1000);
        view.startAnimation(animation);
    }


}
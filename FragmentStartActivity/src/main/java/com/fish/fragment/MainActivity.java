package com.fish.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

import com.fish.lib.fishlib.ui.activity.BaseFishActivity;


/**
 * 展示了fragment的add，remove，hide，show方法
 */
public class MainActivity extends BaseFishActivity {
    private FragmentManager fragmentManager;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    boolean isOne;

    private int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        fragment1 = new Fragment1();
        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment1).commit();
        setTabSelection(0);

    }


    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (fragment1 == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    fragment1 = new Fragment1();
                    transaction.add(R.id.content, fragment1);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragment1);
                }
                break;
            case 1:

                if (fragment2 == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    fragment2 = new Fragment2();
                    transaction.add(R.id.content, fragment2);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(fragment2);
                }
                break;

        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (fragment2 != null) {
            transaction.hide(fragment2);
        }
        if (fragment1 != null) {
            transaction.hide(fragment1);
        }
    }

    public void changeToFragment2(){
        setTabSelection(1);
    }

    public void changeToFragment1(){
        setTabSelection(0);
    }

}
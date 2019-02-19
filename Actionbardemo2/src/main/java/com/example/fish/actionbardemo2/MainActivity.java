package com.example.fish.actionbardemo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;


import com.fish.lib.fishlib.log.LogUtil;
import com.fish.lib.fishlib.util.system.ToastUtils;

import java.lang.reflect.Field;

/**
 *
 * http://blog.csdn.net/guolin_blog/article/details/25466665
 * 实现了tabview
 * https://developer.android.com/training/basics/actionbar/styling.html
 * 写styles.xml的时候注意兼容性考虑
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //返回上一个界面的箭头
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setOverflowShowingAlways();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab tab = actionBar
                .newTab()
                .setText("artist")
                .setTabListener(
                        new TabListener<ArtistFragment>(this, "artist",
                                ArtistFragment.class));
        actionBar.addTab(tab);
        tab = actionBar
                .newTab()
                .setText("album")
                .setTabListener(
                        new TabListener<AlbumFragment>(this, "album",
                                AlbumFragment.class));
        actionBar.addTab(tab);

    }

    /**
     *在ViewConfiguration这个类中有一个叫做sHasPermanentMenuKey的静态变量，系统就是根据这个变量的值来判断手机
     * 有没有物理Menu键的。当然这是一个内部变量，我们无法直接访问它，但是可以通过反射的方式修改它的值，让它永远为
     * false就可以了
     */
    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);


        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider provider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        provider.setShareIntent(getDefaultIntent());


        MenuItem searchItem = menu.findItem(R.id.action_search);
        MenuItemCompat.setOnActionExpandListener(searchItem,new MenuItemCompat.OnActionExpandListener(){

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                LogUtil.d("expand");
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                LogUtil.d("collapse");
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 该Intent表示会将所有可以共享图片的程度都列出来
     * @return
     */
    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id)
        {
            case android.R.id.home:
                ToastUtils.showToast(MainActivity.this, "点击了返回按钮");
                finish();
                break;
        }

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}

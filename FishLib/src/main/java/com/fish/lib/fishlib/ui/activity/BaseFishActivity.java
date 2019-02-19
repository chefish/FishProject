package com.fish.lib.fishlib.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.fish.lib.R;
import com.fish.lib.fishlib.ui.util.ButtonUtil;

/**
 * Created by fish on 15/9/10.
 */
public class BaseFishActivity extends ActionBarActivity {
    protected ActionBarActivity context;

//    @Override
//    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        context = this;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }



    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void setButtonClick(int id, View.OnClickListener onClickListener) {
        ButtonUtil.setLisById(this, id, onClickListener);
    }

    public void setButtonClick(int id, final Class clazz) {
        ButtonUtil.setLisById(this, id, clazz);
    }

}

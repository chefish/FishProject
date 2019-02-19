package com.fish.edit;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


/**
 * 外部等效点击
 * 想要实现效果，点击外部框，相当于点击EditText，要做的事件是让EditText获取到焦点，并且弹出软键盘
 */
public class MainActivity extends ActionBarActivity {

    EditText edit;
    View viewOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.edit);
        viewOut = findViewById(R.id.out);
        viewOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.requestFocus();
                showKeyboard();
            }
        });
    }

    public void showKeyboard() {
        edit.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edit, 0);
    }

}

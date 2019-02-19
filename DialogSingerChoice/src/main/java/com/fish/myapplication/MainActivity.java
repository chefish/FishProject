package com.fish.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 单选对话框
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.b01);
        button.setText("对话框");
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                //选项数组
                String[] choices={"新浪微博","校内","街旁"};
                //包含多个选项的对话框
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.btn_star)
                        .setTitle("分享")
                        .setItems(choices, onselect).setPositiveButton("取消",null).create();
                dialog.show();
            }
        });
    }

    /**
     * 选项的事件监听器
     */
    DialogInterface.OnClickListener onselect = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // TODO Auto-generated method stub
            switch (which) {
                case 0:
                    Toast.makeText(MainActivity.this, "您选择了新浪微博.", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(MainActivity.this, "您选择了校内",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(MainActivity.this, "您选择了街旁",Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    };
}

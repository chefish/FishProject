package com.example.fish.yixininstal;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fishlib.ui.util.ButtonUtil;

import java.io.File;

/**
 * 卸载易信，安装sd卡上的apk对应的易信
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButtonUtil.setLisById(this,R.id.uninstall,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri packageURI = Uri.parse("package:im.yixin");
                //创建Intent意图
                Intent intent = new Intent(Intent.ACTION_DELETE,packageURI);
                //执行卸载程序
                startActivity(intent);
            }
        });


        ButtonUtil.setLisById(this,R.id.install,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                String str = "/Download/AppCenter/Apk/im.yixin_211.apk";
                String fileName = Environment.getExternalStorageDirectory() + str;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
                startActivity(intent);
            }
        });

    }

}

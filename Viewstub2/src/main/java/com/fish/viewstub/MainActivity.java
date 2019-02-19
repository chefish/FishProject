package com.fish.viewstub;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import com.fish.lib.fishlib.ui.activity.BaseFishActivity;


public class MainActivity extends BaseFishActivity {

    ViewStub vs;
    View viewContent;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vs = (ViewStub) findViewById(R.id.viewstub);
        setButtonClick(R.id.btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewContent = vs.inflate();
                btn = (Button) viewContent.findViewById(R.id.btn_new);
                tv = (TextView) viewContent.findViewById(R.id.tv_new);
            }
        });
    }

}

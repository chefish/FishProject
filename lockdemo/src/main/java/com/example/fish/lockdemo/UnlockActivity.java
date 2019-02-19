package com.example.fish.lockdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by fish on 15/5/27.
 */
public class UnlockActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);
        Button btn= (Button) findViewById(R.id.button);
        btn.setText("解锁");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LockApplication la= (LockApplication) getApplication();
                la.isLock=false;
                UnlockActivity.this.finish();
            }
        });
    }
}

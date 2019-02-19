package com.fish.viewstub;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * http://blog.csdn.net/hitlion2008/article/details/6737537
 */
public class MainActivity extends ActionBarActivity {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            if ((((int) (Math.random() * 100)) & 0x01) == 0) {
                // to show text
                // all you have to do is inflate the ViewStub for textview
                ViewStub stub = (ViewStub) findViewById(R.id.viewstub_demo_text);
                stub.inflate();
                TextView text = (TextView) findViewById(R.id.viewstub_demo_textview);
                text.setText("The tree of liberty must be refreshed from time to time" +
                        " with the blood of patroits and tyrants! Freedom is nothing but " +
                        "a chance to be better!");
            } else {
                // to show image
                // all you have to do is inflate the ViewStub for imageview
                ViewStub stub = (ViewStub) findViewById(R.id.viewstub_demo_image);
                stub.inflate();
                ImageView image = (ImageView) findViewById(R.id.viewstub_demo_imageview);
                image.setImageResource(R.drawable.flower);
        }
    }

}

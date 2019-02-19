package com.recycling.imageview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.example.fish.entrance.R;
import com.fish.lib.fishlib.ui.control.recycling.imageview.yixin.RecyclingBitmap;
import com.fish.lib.fishlib.ui.control.recycling.imageview.yixin.RecyclingBitmapDrawable;
import com.fish.lib.fishlib.ui.control.recycling.imageview.yixin.RecyclingImageView;
import com.fish.lib.fishlib.ui.util.ButtonUtil;


/**
 * Created by fish on 15/6/10.
 */
public class SingleImageViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycling_imageview_single_iv);

        final Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.flower);
        final RecyclingImageView recyclingImageView = (RecyclingImageView) findViewById(R.id.iv);
        final RecyclingBitmap rcb = new RecyclingBitmap(bm);
        recyclingImageView.setImageDrawable(new RecyclingBitmapDrawable(getResources(), rcb));
        ButtonUtil.setLisById(this, R.id.killbit, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclingImageView.installBitmap(null);
            }
        });

        ButtonUtil.setLisById(this, R.id.addbit, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.sky);
                RecyclingBitmap rcb1 = new RecyclingBitmap(bm1);
                recyclingImageView.installBitmap(rcb1);
            }
        });


    }
}

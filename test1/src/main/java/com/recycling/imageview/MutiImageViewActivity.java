package com.recycling.imageview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.fish.entrance.R;
import com.fish.lib.fishlib.ui.control.recycling.imageview.yixin.RecyclingBitmap;
import com.fish.lib.fishlib.ui.control.recycling.imageview.yixin.RecyclingImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fish on 15/6/10.
 */
public class MutiImageViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycling_imageview_muti_iv);
        RecyclingImageView recyclingImageView = (RecyclingImageView) findViewById(R.id.iv);
        List<RecyclingBitmap> rcbList=new ArrayList<>();
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.flower);
        Bitmap bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.sky);
        rcbList.add(new RecyclingBitmap(bm));
        rcbList.add(new RecyclingBitmap(bm1));
        recyclingImageView.installBitmaps(rcbList);


    }
}

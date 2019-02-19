package com.fish.lib.fishlib.ui.control.recycling.imageview.yixin;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class RecyclingBitmapDrawable extends BitmapDrawable implements IRecyclingDrawable {
	protected RecyclingBitmap bitmap;
	
    public RecyclingBitmapDrawable(Resources res, RecyclingBitmap bitmap) {
    	super(res, bitmap.getBitmap());
    	
    	this.bitmap = bitmap;
    }

    @Override
    public void setIsDisplayed(boolean isDisplayed) {
    	bitmap.setIsDisplayed(isDisplayed);
    }

    /**
     * 重用bitmap
     * @param res
     * @return
     */
	@Override
	public Drawable newDrawable(Resources res) {
		return new RecyclingBitmapDrawable(res, bitmap);
	}
	
    @Override
    public void draw(Canvas canvas) {
    	if (bitmap.hasBitmapSafely()) {
        	super.draw(canvas);
    	} else {
    		// ?
    	}
    }
}

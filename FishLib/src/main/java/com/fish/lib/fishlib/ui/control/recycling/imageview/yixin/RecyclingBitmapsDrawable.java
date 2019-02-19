package com.fish.lib.fishlib.ui.control.recycling.imageview.yixin;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;

import java.util.ArrayList;
import java.util.List;

public class RecyclingBitmapsDrawable extends ColorDrawable implements IRecyclingDrawable {
	protected List<RecyclingBitmap> bitmaps;
	
	protected int bkColor;

	final Rect bounds = new Rect();
    
	public RecyclingBitmapsDrawable(List<RecyclingBitmap> bitmaps) {
		this(bitmaps, Color.TRANSPARENT);
	}

	public RecyclingBitmapsDrawable(List<RecyclingBitmap> bitmaps, int bkColor) {
		super(bkColor);
		
		this.bitmaps = bitmaps;
		this.bkColor = bkColor;
	}

	@Override
	public void setIsDisplayed(boolean isDisplayed) {
		for (RecyclingBitmap bitmap : bitmaps) {
			bitmap.setIsDisplayed(isDisplayed);
		}
	}
	
	@Override
	public Drawable newDrawable(Resources res) {
		return new RecyclingBitmapsDrawable(bitmaps, bkColor);
	}
	
	@Override
	public void draw(Canvas canvas) {
        copyBounds(bounds);
        
        int width = bounds.width();
        int height = bounds.height();
        
		super.draw(canvas);
		
		// stock
		Bitmap stock = fromStock(width, height);
		
		if (stock == null) {
			// to canvas
			JoinBitmaps.join3(canvas, new int[] {width, height}, getBmps());
		} else {
			Canvas mem = new Canvas(stock);
			
			// clear
			mem.drawARGB(0, 0, 0, 0);
			
			// to memory
			JoinBitmaps.join3(mem, new int[] {width, height}, getBmps());
			
			// to canvas
			canvas.drawBitmap(stock, 0, 0, null);
		}
	}
	
	private List<Bitmap> getBmps() {
		List<Bitmap> bmps = new ArrayList<Bitmap>(bitmaps.size());
		for (RecyclingBitmap bitmap : bitmaps) {
			Bitmap bmp = bitmap.getBitmapSafely();
			if (bmp != null) {
				bmps.add(bmp);
			}
		}
		
		return bmps;
	}
		
	private static final List<Bitmap> stocks = new ArrayList<Bitmap>();
	private static final boolean ENABLE_MEM_CANVAS = false;

    /**
     * 从stocks里挑个大小合适的bitmap，如果没有就new一个
     * @param width
     * @param height
     * @return
     */
	private static final Bitmap fromStock(int width, int height) {
		// 3.0+ and disable
		if (VERSION.SDK_INT >= 11 && !ENABLE_MEM_CANVAS) {
			return null;
		}
		
		// stock
		for (Bitmap stock : stocks) {
			if (stock.getWidth() == width && stock.getHeight() == height) {
				return stock;
			}
		}
		
		try {
			// create
			Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

			// ?
			if (bitmap != null) {
				// stock
				stocks.add(bitmap);
				
				return bitmap;
			}
			
		} catch (Throwable tr) {
			tr.printStackTrace();
		}
		
		return null;
	}
}

package com.fish.lib.fishlib.ui.control.recycling.imageview.yixin;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

public class RecyclingBitmap {

    static final String TAG = "RecyclingBitmap";
    
    private Bitmap bitmap;
    
    private int mCacheRefCount = 0;
    private int mDisplayRefCount = 0;

    private boolean mHasBeenDisplayed;

    // DIAG
    private static final boolean DIAG_ON = false;
    private static final boolean DIAG_USE_ON = false;

    private static final int DIAG_BORN = 0;
    private static final int DIAG_DIE = 1;
    private static final int DIAG_USE = 2;

    private final String id;
    private final String tag;
    /**
     * 图片的宽、高、大小信息
     */
    private final String info;
    
    public RecyclingBitmap(Bitmap bitmap) {
        this(bitmap, null);
    }
    
    public RecyclingBitmap(Bitmap bitmap, String tag) {
    	// bitmap
    	this.bitmap = bitmap;
        // TAG
        this.tag = TextUtils.isEmpty(tag) ? "U" : tag;
        // ID as hex of hash code
        this.id = bitmap != null ? Integer.toHexString(bitmap.hashCode()) : null;
        // INFO
        this.info = bitmap != null ? infoOf(bitmap) : null;
        
        // DIAG_BORN
        diagnose(DIAG_BORN);
    }

    public final int sizeOf() {
    	return bitmap != null ? sizeOf(bitmap) : 0;
    }
    
    public Bitmap getBitmap() {
		return bitmap;
	}
    
    public int getWidth() {
    	return bitmap != null ? bitmap.getWidth() : 0;
    }

    public int getHeight() {
    	return bitmap != null ? bitmap.getHeight() : 0;
    }
    
    public Bitmap getBitmapSafely() {
        synchronized (this) {
        	return getValidBitmap();
        }
    }
    
    public boolean hasBitmapSafely() {
        synchronized (this) {
        	return hasValidBitmap();
        }
    }
    
	public void setIsDisplayed(boolean isDisplayed) {
        synchronized (this) {
            if (isDisplayed) {
                mDisplayRefCount++;
                mHasBeenDisplayed = true;
            } else {
                mDisplayRefCount--;
            }
            
            // Check to see if recycle() can be called
            checkState();
        }
    }

	public void setIsCached(boolean isCached) {
        synchronized (this) {
            if (isCached) {
                mCacheRefCount++;
            } else {
                mCacheRefCount--;
            }
            
            // Check to see if recycle() can be called
            checkState();
        }
    }

    private void checkState() {
        // If the cache and display ref counts = 0, and this bitmap
        // has been displayed, then recycle
        if (mCacheRefCount <= 0 && mDisplayRefCount <= 0 && mHasBeenDisplayed
                && hasValidBitmap()) {

        	BitmapRecycleBin.recycle(bitmap);
            
            // DIAG_DIE
            diagnose(DIAG_DIE);
        } else {
        	// DIAG_USE
        	diagnose(DIAG_USE);
        }
    }
    
    private boolean hasValidBitmap() {
        return bitmap != null && !bitmap.isRecycled();
    }

    private Bitmap getValidBitmap() {
        return bitmap != null && !bitmap.isRecycled() ? bitmap : null;
    }

    /**
     * 获取宽，高，size
     * @param bitmap
     * @return
     */
    private static final String infoOf(Bitmap bitmap) {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append(bitmap.getWidth());
    	sb.append('x');
    	sb.append(bitmap.getHeight());
    	sb.append('=');
    	sb.append(sizeOf(bitmap));
    	
    	return sb.toString();
    }
    
    private static int sizeOf(Bitmap bitmap) {
    	// Bitmap.getByteCount in API 12
    	return bitmap.getRowBytes() * bitmap.getHeight();
    }
    
    private void diagnose(int event) {
    	if (!DIAG_ON) {
    		return;
    	}
    	
    	if (event == DIAG_USE && !DIAG_USE_ON) {
    		return;
    	}
    	
    	if (id == null) {
    		return;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	// TAG
    	sb.append(tag);
		sb.append(" ");
		
		switch (event) {
		// DIAG_BORN
		case DIAG_BORN:
			sb.append("BORN");
			break;
		// DIAG_DIE
		case DIAG_DIE:
			sb.append("DIE");
			break;
		// DIAG_USE
		case DIAG_USE:
			sb.append("IN USE ");
			sb.append(mCacheRefCount);
			sb.append(" ");
			sb.append(mDisplayRefCount);
			break;
		}
		sb.append(" ");
		
    	// ID
		sb.append(id);
		
		sb.append(" ");
		
		// INFO
		sb.append(info);

		Log.d(TAG, sb.toString());
    }
    
    public final String dump() {
    	StringBuilder sb = new StringBuilder();
    	
    	// ID
		sb.append(id);
		
		sb.append(" ");
		
		// INFO
		sb.append(info);
    	
    	return sb.toString();
    }
}

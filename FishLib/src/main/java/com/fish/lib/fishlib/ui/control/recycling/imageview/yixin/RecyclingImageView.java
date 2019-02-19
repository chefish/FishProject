package com.fish.lib.fishlib.ui.control.recycling.imageview.yixin;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.List;

//recyclingImageView.setImageDrawable(new RecyclingBitmapDrawable(getResources(), new RecyclingBitmap(lBitmap)));


public class RecyclingImageView extends ImageView {
	
	protected boolean detacheClearDrawable = true;
	
    public RecyclingImageView(Context context) {
        super(context);
    }

    public RecyclingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public RecyclingImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDetachedFromWindow() {
    	if(detacheClearDrawable) {
    		setImageDrawable(null);
    	}

        super.onDetachedFromWindow();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        // Keep hold of previous Drawable
        final Drawable previousDrawable = getDrawable();

        // Call super to set new Drawable
        super.setImageDrawable(drawable);

        // Notify new Drawable that it is being displayed
        notifyDrawable(drawable, true);

        // Notify old Drawable so it is no longer being displayed
        notifyDrawable(previousDrawable, false);
    }

    private static void notifyDrawable(Drawable drawable, final boolean isDisplayed) {
        if (drawable instanceof IRecyclingDrawable) {
            // The drawable is a RecyclingDrawable, so notify it
            ((IRecyclingDrawable) drawable).setIsDisplayed(isDisplayed);
        } else if (drawable instanceof LayerDrawable) {
            // The drawable is a LayerDrawable, so recurse on each layer
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            for (int i = 0, z = layerDrawable.getNumberOfLayers(); i < z; i++) {
                notifyDrawable(layerDrawable.getDrawable(i), isDisplayed);
            }
        }
    }
    
    public void installBitmap(RecyclingBitmap bitmap) {
    	setImageDrawable(bitmap == null ? null : createDrawable(bitmap));
    }

    public void installBitmaps(List<RecyclingBitmap> bitmaps) {
    	setImageDrawable(bitmaps == null ? null : createDrawable(bitmaps));
    }
    
    protected void uninstall() {
     	// uninstall
    	setImageDrawable(null); 	
    }

    protected Drawable createDrawable(RecyclingBitmap bitmap) {
    	// default
    	return new RecyclingBitmapDrawable(getResources(), bitmap);
    }
    
    protected Drawable createDrawable(List<RecyclingBitmap> bitmaps) {
    	// default
    	return new RecyclingBitmapsDrawable(bitmaps);
    }
}

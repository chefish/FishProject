package com.fish.lib.fishlib.ui.control.recycling.imageview.yixin;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public interface IRecyclingDrawable {
    public void setIsDisplayed(boolean isDisplayed);
    
    public Drawable newDrawable(Resources res);
}

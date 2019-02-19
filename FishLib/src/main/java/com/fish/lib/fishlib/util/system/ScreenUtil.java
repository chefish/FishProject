package com.fish.lib.fishlib.util.system;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.lang.reflect.Field;


/**
 * 需要初始化一次,只需要初始化一次
 * @see ScreenUtil#init(android.content.Context)
 */
public class ScreenUtil {
    private static final String TAG = "ScreenUtil";

    private static double RATIO = 0.85;

    public static int screenWidth;
    public static int screenHeight;
    public static int screenMin;// 宽高中，较小的值
    public static int screenMax;// 宽高中，较大的值

    public static float density;
    public static float scaleDensity;
    public static float xdpi;
    public static float ydpi;
    public static int densityDpi;

    public static int dialogWidth;
    public static int statusbarheight;
    public static int navbarheight;

    public static boolean initSucc;

    /**
     * 初始化
     * 传入application context
     * 如ScreenUtil.init(getApplicationContext());
     *
     * @param context
     */
    public static void init(Context context) {
        if (null == context || initSucc) {
            return;
        }
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        screenMin = (screenWidth > screenHeight) ? screenHeight : screenWidth;
        screenMax = (screenWidth < screenHeight) ? screenHeight : screenWidth;
        density = dm.density;
        scaleDensity = dm.scaledDensity;
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;
        densityDpi = dm.densityDpi;
        statusbarheight = getStatusBarHeight(context);
        navbarheight = getNavBarHeight(context);
        Log.d(TAG, "screenWidth=" + screenWidth + " screenHeight=" + screenHeight + " density=" + density);
        initSucc = true;
    }

    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception E) {
            E.printStackTrace();
        }
        return sbar;
    }

    public static int getNavBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static int dip2px(float dipValue) {
        final float scale = ScreenUtil.getDisplayDensity();
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = ScreenUtil.getDisplayDensity();
        return (int) (pxValue / scale + 0.5f);
    }

    private static float getDisplayDensity() {

        return density;
    }

    public static int getDisplayWidth() {

        return screenWidth;
    }

    public static int getDisplayHeight() {

        return screenHeight;
    }

    public static int getScreenMin() {

        return screenMin;
    }

    public static int getScreenMax() {

        return screenMax;
    }

    public static int getDialogWidth() {
        dialogWidth = (int) (getScreenMin() * RATIO);
        return dialogWidth;
    }
}

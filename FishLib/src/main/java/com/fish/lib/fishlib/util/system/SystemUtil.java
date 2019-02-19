package com.fish.lib.fishlib.util.system;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;


import com.fish.lib.fishlib.log.LogUtil;

import java.util.List;

/**
 * Created by hzyuxiaomin on 2015/5/8.
 */
public class SystemUtil {
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 判断当前线程是否是主线程
     *
     * @param context
     * @return
     */
    public static boolean isMainThread(Context context) {
        return Looper.myLooper() == Looper.getMainLooper();
//        int pid = android.os.Process.myPid();
//        ActivityManager mActivityManager =
//                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        String packageName = context.getPackageName();
//        if (mActivityManager == null || TextUtils.isEmpty(packageName)) {
//            return false;
//        } else {
//            if (mActivityManager.getRunningAppProcesses() == null) {
//                return false;
//            } else {
//                for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
//                        .getRunningAppProcesses()) {
//                    if (appProcess == null) {
//                        continue;
//                    }
//                    if (appProcess.pid == pid) {
//                        if (appProcess.processName.equalsIgnoreCase(packageName)) {
//                            return true;
//                        }
//                    }
//                }
//            }
//        }
//
//        return false;
    }


    /**
     * 判断一个service是否启动
     *
     * @param context
     * @param className 这个service的全名
     * @return
     */
    public static boolean isServiceRunning(Context context, String className) {
        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList =
                activityManager.getRunningServices(Integer.MAX_VALUE);
        LogUtil.d("current service num:" + serviceList.size());
        for (ActivityManager.RunningServiceInfo runningServiceInfo : serviceList) {
            if (runningServiceInfo.service.getClassName().equals(className)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
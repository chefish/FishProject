package com.fish.lib.fishlib.util.system;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by fish on 15/5/22.
 * 输入法工具
 */
public class ImeUtil {

    /**
     * 延时弹出键盘
     *
     * @param view
     *            ：键盘的焦点项
     */
    public static void showKeyboardDelayed(View view, final Activity activity) {
        final View viewToFocus = view;
        if (view != null) {
            view.requestFocus();
        }

        assert view != null;
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewToFocus == null || viewToFocus.isFocused()) {
                    showKeyboard(true, activity);
                }
            }
        }, 200);
    }

    protected  static void showKeyboard(boolean isShow,Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isShow) {
            if (activity.getCurrentFocus() == null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            } else {
                imm.showSoftInput(activity.getCurrentFocus(), 0);
            }
        } else {
            if (activity.getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

}

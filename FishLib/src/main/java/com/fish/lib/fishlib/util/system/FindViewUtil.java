package com.fish.lib.fishlib.util.system;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by fish on 15/10/30.
 */
public class FindViewUtil {

    /**
     * 在一个viewgroup里面查找特点类型的View
     * ex：ex:Collection<TextView> aa=findChildrenByClass(searchView, TextView.class);
     * @param viewGroup
     * @param clazz
     * @param <V>
     * @return
     */
    public static <V extends View> Collection<V> findChildrenByClass(ViewGroup viewGroup, Class<V> clazz) {
        return gatherChildrenByClass(viewGroup, clazz, new ArrayList<V>());
    }

    private static <V extends View> Collection<V> gatherChildrenByClass(ViewGroup viewGroup, Class<V> clazz, Collection<V> childrenFound) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            final View child = viewGroup.getChildAt(i);
            if (clazz.isAssignableFrom(child.getClass())) {
                childrenFound.add((V) child);
            }
            if (child instanceof ViewGroup) {
                gatherChildrenByClass((ViewGroup) child, clazz, childrenFound);
            }
        }
        return childrenFound;
    }

}

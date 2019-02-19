package com.fish.lib.fishlib.ui.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

/**
 * Created by fish on 15/6/1.
 */
public class ButtonUtil {
    public static Button setLisById(Activity activity, int id, View.OnClickListener onClickListener)
    {
        Button btn= (Button) activity.findViewById(id);
        if(btn!=null){
            btn.setOnClickListener(onClickListener);
        }
        return  btn;
    }

    public static Button setProperty(Activity activity, int id,String text ,View.OnClickListener onClickListener)
    {
        Button btn=setLisById(activity,id,onClickListener);
        if(btn!=null){
            btn.setText(text);
        }
        return  btn;
    }

    /**
     * 用来启动activity
     * @param activity
     * @param id
     * @param clazz
     * @return
     */
    public static Button setLisById(final Activity activity,int id, final Class clazz)
    {
        Button btn= (Button) activity.findViewById(id);
        if(btn!=null){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intt=new Intent(activity, clazz);
                    activity.startActivity(intt);
                }
            });
        }
        return  btn;
    }
}

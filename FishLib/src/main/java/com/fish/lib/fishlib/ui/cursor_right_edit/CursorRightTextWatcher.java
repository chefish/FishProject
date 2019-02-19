package com.fish.lib.fishlib.ui.cursor_right_edit;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

/**
 * Created by fish on 15/5/22.
 */
public class CursorRightTextWatcher implements TextWatcher {
    protected CursorRightEditText mRightText;
    public CursorRightTextWatcher(CursorRightEditText editText)
    {
        mRightText=editText;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mRightText.isChanging()) {
            mRightText.setChanging(false);
        }

        String str = s.toString();
        Log.d("onTextChanged", s.toString() + " start:" + start + " count:" + count + " before:" + before);
        if (mRightText.isLikeHint()) {
            int hintLen=mRightText.getHintString().length();
            if (str.length() > hintLen) {
                mRightText.postInput(s.toString());

            } else if (str.length() < hintLen) {
                //输入“删除键”处理下
                mRightText.postHint();

            }
            return;

        } else {
            if (s.toString().equals("")) {
                mRightText.postHint();
                return;
            }


        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

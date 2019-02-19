package com.fish.lib.fishlib.ui.cursor_right_edit;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * Created by hzyuxiaomin on 2015/5/20.
 * 使用假的hint达到光标在右边的效果
 * 这里的hint本质上就是text
 * 以hint字符串为“输入金额”为例
 * 初始化时，显示“输入金额”，输入1，就会变为“输入金额1”，我们把他改为“1”
 * 存在问题，弹不出键盘
 */
public class CursorRightEditText extends EditText {

    private String mLikeHintString;
    private boolean mIsLikeHintState;
    private int mLikeHintTextSize;
    private int mGeneralTextSize;
    /**
     * 是否是中间状态，“输入金额1”就是中间状态，“输入金”也是中间状态
     *
     * @return
     */
    private boolean mIsChanging;
    private int mTextColor ;
//    private int mLikeHintTextColor = Color.rgb(192, 192, 192);
    private int mLikeHintTextColor ;


    public CursorRightEditText(Context context) {
        super(context);
    }

    public CursorRightEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CursorRightEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public boolean isChanging() {
        return mIsChanging;
    }

    public boolean isLikeHint() {
        return mIsLikeHintState;
    }

    public void setChanging(boolean mIsChanging) {
        this.mIsChanging = mIsChanging;
    }
    public void init(int hintTextSize,int textSize, String hintStr,int hintColor,int color) {
        mLikeHintTextColor=hintColor;
        mTextColor=color;
        mLikeHintTextSize = hintTextSize;
        mGeneralTextSize=textSize;
        mLikeHintString = hintStr;
        setLikeHint();
        addTextChangedListener(new CursorRightTextWatcher(this));
    }

    public String getHintString() {
        return mLikeHintString;
    }


    public void setLikeHint(boolean b) {
        mIsLikeHintState = b;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isLikeHint()) {
            requestFocus();
            setSelection(mLikeHintString.length());
            return true;
        }
        return super.onTouchEvent(event);
    }
public boolean isNormal()
{
    return !mIsLikeHintState&&!mIsChanging;
}

    public void setLikeHint() {
        mIsLikeHintState = true;
        setText(mLikeHintString);
        setSelection(mLikeHintString.length());
        setTextSize(mLikeHintTextSize);
        setTextColor(mLikeHintTextColor);
    }


    public void postHint() {
        setChanging(true);
        post(new Runnable() {
            @Override
            public void run() {
                setLikeHint();
            }
        });
    }

    /**
     * 以hint字符串为“输入金额”为例
     * hint时输入1，本来该显示为“输入金额1” ，改为“1”
     *
     * @param s
     */
    public void postInput(final String s) {
        setChanging(true);
        post(new Runnable() {
            @Override
            public void run() {

                setLikeHint(false);
                setText(s.subSequence(mLikeHintString.length(), s.length()));
                setSelection(length());
                setTextSize(mGeneralTextSize);
                setTextColor(mTextColor);
            }
        });
    }
}

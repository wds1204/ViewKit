package com.wds.viewkit;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Copyright (C), 2016-2020, 未来酒店
 * File: MyTextView.java
 * Author: wds_sun
 * Date: 2020/10/15 2:23 PM
 * Description:
 */
public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {
    private static final String TAG = MyTextView.class.getName();

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("TAG", "MotionEvent.ACTION==="+ev.getAction()+"    "+super.onTouchEvent(ev));
      return   super.onTouchEvent(ev);
//        int action = ev.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                Log.d(TAG, "3:onTouchEvent action:ACTION_DOWN");
//                return false;
////                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.d(TAG, "3:onTouchEvent action:ACTION_MOVE");
////                return false;
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.d(TAG, "3:onTouchEvent action:ACTION_UP");
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                Log.d(TAG, "3:onTouchEvent action:ACTION_CANCEL");
//                break;
//        }
//        return false;
    }
}

package com.wds.viewkit.widget;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

/**
 * Copyright (C), 2016-2020, 未来酒店
 * File: LayoutView2.java
 * Author: wds_sun
 * Date: 2020/10/14 2:43 PM
 * Description:
 */
public class LayoutView2 extends LinearLayout {
    private final String TAG = "LayoutView2";
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {

        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    public LayoutView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, TAG);
        Looper.getMainLooper().getQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                return false;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "2:onInterceptTouchEvent action:ACTION_DOWN");
                break;
            //return true;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "2:onInterceptTouchEvent action:ACTION_MOVE");
                break;
            //return true;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "2:onInterceptTouchEvent action:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "2:onInterceptTouchEvent action:ACTION_CANCEL");
                break;
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "2:onTouchEvent action:ACTION_DOWN");
                return true;
//                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "2:onTouchEvent action:ACTION_MOVE");
//                return false;
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "2:onTouchEvent action:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "2:onTouchEvent action:ACTION_CANCEL");
                break;
        }
        return false;
    }
}
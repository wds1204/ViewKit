package com.wds.viewkit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.icu.text.RelativeDateTimeFormatter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.wds.viewkit.R;

/**
 * Copyright (C), 2016-2020, 未来酒店
 * File: ColorTrackTextView.java
 * Author: wds_sun
 * Date: 2020/10/14 10:00 AM
 * Description:
 */
public class ColorTrackTextView extends androidx.appcompat.widget.AppCompatTextView {
    int mStartColor, mEndColor;
    Paint defaultPaint, changePaint;
    float mCurrentProgress = 0f;
    // 当前朝向
    private Direction mDirection = Direction.DIRECTION_RIGHT;
    // 绘制的朝向枚举
    public enum Direction {
        DIRECTION_LEFT, DIRECTION_RIGHT
    }
    public ColorTrackTextView(Context context) {
        this(context, null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /*自定义属性*/
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        mStartColor = typedArray.getColor(R.styleable.ColorTrackTextView_startColor, mStartColor);
        mEndColor = typedArray.getColor(R.styleable.ColorTrackTextView_endColor, mEndColor);
        typedArray.recycle();


        defaultPaint = new Paint();
        // 仿抖动
        defaultPaint.setDither(true);
        defaultPaint.setAntiAlias(true);
        defaultPaint.setColor(mStartColor);
        defaultPaint.setTextSize(getTextSize());

        changePaint = new Paint();
        changePaint.setDither(true);
        changePaint.setAntiAlias(true);
        changePaint.setColor(mEndColor);
        changePaint.setTextSize(getTextSize());


    }

    /**
     * 利用clipRect裁剪API 利用两个画笔不断的画
     * <p>
     * 0～midValue，midValue～endvalue
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        /*绘制不变色的  0～midValu*/
        int midValue = (int) (getWidth() * mCurrentProgress);
        /*绘制  0～midValue的颜色*/
        if( mDirection==Direction.DIRECTION_LEFT) {
            drawText(canvas, 0, midValue, defaultPaint);
            /*绘制 midValue～endvalue的颜色*/
            drawText(canvas, midValue, getWidth(), changePaint);
        }else {
            drawText(canvas, 0,getWidth()- midValue, defaultPaint);
            /*绘制 midValue～endvalue的颜色*/
            drawText(canvas, getWidth()- midValue, getWidth(), changePaint);
        }

    }

    /**
     * 设置方向
     * @param mDirection
     */
    public void setmDirection(Direction mDirection) {
        this.mDirection = mDirection;
    }

    public void setmCurrentProgress(float mCurrentProgress) {
        this.mCurrentProgress = mCurrentProgress;
        invalidate();
    }

    public void drawText(Canvas canvas, int startValue, int endValue, Paint paint) {
        canvas.save();

        /*绘制不变色的  0～midValue*/
        canvas.clipRect(startValue, 0, endValue, getHeight());

        String text = getText().toString();
        Rect textBounds = new Rect();
        defaultPaint.getTextBounds(text, 0, text.length(), textBounds);
        int dx = getWidth() / 2 - textBounds.width() / 2;
        Paint.FontMetrics fontMetrics = defaultPaint.getFontMetrics();
        int baseLine = (int) ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);

        int dy = getHeight() / 2 + baseLine;
        canvas.drawText(text, dx, dy, paint);
        canvas.restore();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        return super.dispatchTouchEvent(event);
    }


}

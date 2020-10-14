package com.wds.viewkit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
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
    int mStartColor,mEndColor;
    Paint defaultPaint,changePaint;
    float mCurrentProgress=0f;
    public ColorTrackTextView(Context context) {
        this(context,null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /*自定义属性*/
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        mStartColor = typedArray.getColor(R.styleable.ColorTrackTextView_startColor, mStartColor);
        mEndColor = typedArray.getColor(R.styleable.ColorTrackTextView_endColor, mEndColor);
        typedArray.recycle();


        defaultPaint = new Paint();

        defaultPaint.setAntiAlias(true);
        defaultPaint.setColor(mStartColor);
        defaultPaint.setTextSize(getTextSize());

        changePaint = new Paint();

        changePaint.setAntiAlias(true);
        changePaint.setColor(mEndColor);
        changePaint.setTextSize(getTextSize());


    }

    /**
     * 利用clipRect裁剪API 利用两个画笔不断的画
     *
     * 0～midValue，midValue～endvalue
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.clipRect()  裁剪区域

        canvas.save();

        /*绘制不变色的  0～midValu*/
        int midValue= (int) (getWidth()*mCurrentProgress);
        canvas.clipRect(0,0,midValue,getHeight());

        String text = getText().toString();
        Rect textBounds = new Rect();
        defaultPaint.getTextBounds(text,0,text.length(),textBounds);
        int dx=getWidth()/2-textBounds.width()/2;
        Paint.FontMetrics fontMetrics = defaultPaint.getFontMetrics();
        int baseLine= (int) ((fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom);;
        int dy=getHeight()/2+baseLine;
        canvas.drawText(text,dx,dy,defaultPaint);

        canvas.restore();

        /*绘制变色 midValue～endvalue*/
        canvas.save();
        canvas.clipRect(midValue,0,getWidth(),getHeight());
        canvas.drawText(text,dx,dy,changePaint);
        canvas.restore();
    }

    public void setmCurrentProgress(float mCurrentProgress) {
        this.mCurrentProgress = mCurrentProgress;
        invalidate();
    }
}

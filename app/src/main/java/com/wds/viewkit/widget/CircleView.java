package com.wds.viewkit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wds.viewkit.R;

/**
 * Copyright (C), 2016-2020, 未来酒店
 * File: CircleView.java
 * Author: wds_sun
 * Date: 2020/10/15 3:02 PM
 * Description:
 */
public class CircleView extends View {
    private final Paint paint,loadingpaint,textpaint;
    int circleSize, circleTextColor, circleTextSize;
    int defaultColor= Color.GREEN;
    int loadingColor= Color.RED;
    public CircleView(Context context) {
        this(context, null);
    }

    private float currentProgress=0;
    private float maxProgress=100;
    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /*自定义属性*/
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        circleSize = typedArray.getDimensionPixelSize(R.styleable.CircleView_circleSize, circleSize);
        circleTextColor = typedArray.getColor(R.styleable.CircleView_circleTextColor, circleTextColor);
        circleTextSize = typedArray.getDimensionPixelSize(R.styleable.CircleView_circleTextSize, circleTextSize);
        defaultColor = typedArray.getColor(R.styleable.CircleView_defaultColor, defaultColor);
        loadingColor = typedArray.getColor(R.styleable.CircleView_loadingColor, loadingColor);
        typedArray.recycle();

        paint = new Paint();
        paint.setStrokeWidth(circleSize);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(defaultColor);

        loadingpaint = new Paint();
        loadingpaint.setStrokeWidth(circleSize);
        loadingpaint.setStyle(Paint.Style.STROKE);
        loadingpaint.setColor(loadingColor);

        textpaint = new Paint();
        textpaint.setAntiAlias(true);
        textpaint.setTextSize(circleTextSize);
        textpaint.setColor(circleTextColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);

        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);

        int size = Math.min(w, h);
        setMeasuredDimension(size, size);

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        int center = getWidth() / 2;
        canvas.drawCircle(center, center, getWidth() / 2-circleSize/2, paint);

        RectF rectF = new RectF();
        rectF.left=circleSize/2;
        rectF.top=circleSize/2;
        rectF.bottom=getWidth()-circleSize/2 ;
        rectF.right=getWidth()-circleSize/2  ;
        if(currentProgress==0) return;

        int progress= (int) (currentProgress/maxProgress*100);

//        Integer

        canvas.drawArc(rectF,180,360* currentProgress/maxProgress,false,loadingpaint);


        /*3：画文字*/
        String stepText=progress+"%";
        Rect textBounds = new Rect();
        textpaint.getTextBounds(stepText,0,stepText.length(),textBounds);
        int dx=getWidth()/2-textBounds.width()/2;
        Paint.FontMetrics fontMetrics = textpaint.getFontMetrics();

        int dy= (int) ((fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom);
        int baseline=getHeight()/2+dy;
        canvas.drawText(stepText,dx,baseline, textpaint);
    }

    public void setCurrentProgress(float currentProgress) {
        this.currentProgress = currentProgress;
        invalidate();
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress=maxProgress;
    }
}

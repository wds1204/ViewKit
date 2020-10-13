package com.wds.viewkit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wds.viewkit.R;

import androidx.annotation.Nullable;

/**
 * 模拟计步器
 */
public class StepView extends View {
    int stepInnerColor = Color.BLUE;
    int stepOutColor = Color.RED;
    int borderWidth = 30;
    int stepTextSize;
    int stepTextColor=Color.BLACK;

    int mMaxStep=0;
    int mCurrentStep=0;

    Paint innerPaint,outPaint,textPaint;

    public StepView(Context context) {
        this(context, null);
    }

    public StepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.StepView);
        stepInnerColor = typedArray.getColor(R.styleable.StepView_stepOutColor, stepInnerColor);
        stepOutColor = typedArray.getColor(R.styleable.StepView_stepInnerColor, stepOutColor);
        stepTextColor = typedArray.getColor(R.styleable.StepView_stepTextColor, stepTextColor);

        borderWidth = typedArray.getDimensionPixelSize(R.styleable.StepView_borderWidth, borderWidth);
        stepTextSize =  typedArray.getDimensionPixelSize(R.styleable.StepView_stepTextSize, stepTextSize);
        Log.e("TAG", "stepTextSize======"+stepTextSize);
        typedArray.recycle();


        init();
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);

        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);

        int size = Math.min(w, h);

        setMeasuredDimension(size, size);

    }

    private void init() {
        innerPaint = new Paint();
        innerPaint.setColor(stepInnerColor);
        innerPaint.setStrokeWidth(borderWidth);
        innerPaint.setStyle(Paint.Style.STROKE);
        innerPaint.setStrokeCap(Paint.Cap.ROUND);

        outPaint = new Paint();
        outPaint.setColor(stepOutColor);
        outPaint.setStrokeWidth(borderWidth);
        outPaint.setStyle(Paint.Style.STROKE);
        outPaint.setStrokeCap(Paint.Cap.ROUND);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(stepTextColor);
        textPaint.setTextSize(stepTextSize);

    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int marginWidth = borderWidth / 2 + getPaddingLeft();
        /*1：画内圆弧*/
        RectF rcf = new RectF(marginWidth, marginWidth, getHeight() - marginWidth, getHeight() - marginWidth);
        canvas.drawArc(rcf, 135, 270, false, innerPaint);
        /*2：画外圆弧*/
        if(mMaxStep==0) return;
        canvas.drawArc(rcf, 135, 270*mCurrentStep/mMaxStep, false, outPaint);

        /*3：画文字*/
        String stepText=String.valueOf(mCurrentStep);
        Rect textBounds = new Rect();
        textPaint.getTextBounds(stepText,0,stepText.length(),textBounds);
        int dx=getWidth()/2-textBounds.width()/2;
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();

        int dy= (int) ((fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom);
        int baseline=getHeight()/2+dy;
        canvas.drawText(stepText,dx,baseline, textPaint);

    }

    public void setMaxStep(int mMaxStep) {
        this.mMaxStep = mMaxStep;
    }

    public void setCurrentStep(int currentStep){
        this.mCurrentStep=currentStep;
        invalidate();
    }
}

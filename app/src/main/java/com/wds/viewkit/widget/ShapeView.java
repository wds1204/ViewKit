package com.wds.viewkit.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Copyright (C), 2016-2020, 未来酒店
 * File: ShapeView.java
 * Author: wds_sun
 * Date: 2020/10/15 3:46 PM
 * Description:
 */
public class ShapeView extends View {

    private Shape mCurrentShape = Shape.Cirecle;//当前形状
    private int timeMillis;
    private Path path;

    public enum Shape {
        Cirecle, Square, Triange;
    }

    private Paint mPaint;

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        int center = getWidth() / 2;
        switch (mCurrentShape) {
            case Cirecle:
                mPaint.setColor(Color.BLUE);
                canvas.drawCircle(center, center, getWidth() / 2, mPaint);

                break;
            case Square:
                mPaint.setColor(Color.RED);
                canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
                break;
            case Triange:
                mPaint.setColor(Color.GREEN);

                if(path==null) {
                    path=new Path();
                    path.moveTo(getWidth()/2,0);
                    path.lineTo(getWidth(), (float) (getHeight()/2*Math.sqrt(3)));
                    path.lineTo(0,(float) (getHeight()/2*Math.sqrt(3)));
                    path.close();
                }
                canvas.drawPath(path,mPaint);
                break;
        }
    }

    public void exchange() {

        exchange(1000);
    }

    public void changeView() {
        switch (mCurrentShape) {
            case Cirecle:
                mCurrentShape = Shape.Square;
                break;
            case Square:
                mCurrentShape = Shape.Triange;
                break;
            case Triange:
                mCurrentShape = Shape.Cirecle;
                break;
        }
        invalidate();
    }

    public void exchange(int timeMillis) {
        this.timeMillis=timeMillis;
        handler.sendEmptyMessageDelayed(1, timeMillis);
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            changeView();
            handler.sendEmptyMessageDelayed(1, timeMillis);

        }
    };
}

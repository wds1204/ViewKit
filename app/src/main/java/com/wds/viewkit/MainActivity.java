package com.wds.viewkit;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.wds.viewkit.widget.CircleView;
import com.wds.viewkit.widget.ColorTrackTextView;
import com.wds.viewkit.widget.StepView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    StepView stepView;
    CircleView circleview;
    ColorTrackTextView colorTrackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setLoadingProgress();
//        changeTextColor();
    }

    public void setLoadingProgress() {
        circleview = findViewById(R.id.circleview);

        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 100);
        valueAnimator.setDuration(3000);
//        valueAnimator.setInterpolator(new DecelerateInterpolator());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentStep = (float) animation.getAnimatedValue();

                circleview.setCurrentProgress((int) currentStep);

            }
        });

        valueAnimator.start();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    //    public void changeTextColor() {
//        colorTrackTextView = findViewById(R.id.colorTrackTextView);
//        colorTrackTextView.setmDirection(ColorTrackTextView.Direction.DIRECTION_RIGHT);//设置从右到左
//        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
//        valueAnimator.setDuration(3000);
//        valueAnimator.setInterpolator(new DecelerateInterpolator());
////        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
////        valueAnimator.setRepeatCount(2);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float currentStep = (float) animation.getAnimatedValue();
//                colorTrackTextView.setmCurrentProgress(currentStep);
//            }
//        });
//        valueAnimator.setStartDelay(500);
//        valueAnimator.start();
//
//
//    }
//    public void setSteps(){
//                stepView = findViewById(R.id.stepview);
//        stepView.setMaxStep(4000);
//
//        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0,3204);
//        valueAnimator.setDuration(2000);
//        valueAnimator.setInterpolator(new DecelerateInterpolator());
//
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override public void onAnimationUpdate(ValueAnimator animation) {
//                float currentStep = (float) animation.getAnimatedValue();
//
//                stepView.setCurrentStep((int) currentStep);
//
//            }
//        });
//
//        valueAnimator.start();
//
//    }
}
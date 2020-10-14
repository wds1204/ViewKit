package com.wds.viewkit;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;

import com.wds.viewkit.widget.ColorTrackTextView;
import com.wds.viewkit.widget.StepView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    StepView stepView;
    ColorTrackTextView colorTrackTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeTextColor();
    }

    public void changeTextColor() {
        colorTrackTextView = findViewById(R.id.colorTrackTextView);

        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentStep = (float) animation.getAnimatedValue();
                Log.e("TAG", "currentStep====="+currentStep);
                colorTrackTextView.setmCurrentProgress(currentStep);
            }
        });
        valueAnimator.start();


    }
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
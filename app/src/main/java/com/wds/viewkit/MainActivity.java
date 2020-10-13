package com.wds.viewkit;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

import com.wds.viewkit.widget.StepView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    StepView stepView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stepView = findViewById(R.id.stepview);
        stepView.setMaxStep(4000);

        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0,3204);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator animation) {
                float currentStep = (float) animation.getAnimatedValue();

                stepView.setCurrentStep((int) currentStep);

            }
        });

        valueAnimator.start();
    }
}
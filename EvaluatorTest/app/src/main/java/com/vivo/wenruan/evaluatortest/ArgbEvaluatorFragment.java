package com.vivo.wenruan.evaluatortest;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class ArgbEvaluatorFragment extends Fragment {

    private final int FIRST_COLOR = 0xffff0000;
    private final int SECOND_COLOR = 0xff0000ff;
    private final int DURATION = 3000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.argb_evaluator_fragment, container, false);
        initData(view);
        return view;
    }

    private void initData(View view) {
        final TextView textView = (TextView) view.findViewById(R.id.text_view);
        final TextView textView1 = (TextView) view.findViewById(R.id.text_view_2);
        Button button = (Button) view.findViewById(R.id.animator_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //动画实现一
                ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(), FIRST_COLOR, SECOND_COLOR);
                animator.setDuration(DURATION);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int color = (Integer) animation.getAnimatedValue();
                        textView.setBackgroundColor(color);
                    }
                });
                animator.start();

                //动画实现二
                final ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
                valueAnimator.setDuration(DURATION);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float fraction = animation.getAnimatedFraction();
                        int color = (Integer)(mArgbEvaluator.evaluate(fraction, FIRST_COLOR, SECOND_COLOR));
                        textView1.setBackgroundColor(color);
                    }
                });
                valueAnimator.start();
            }
        });

    }
}

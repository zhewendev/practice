package com.vivo.wenruan.evaluatordemo;

import android.animation.FloatArrayEvaluator;
import android.animation.IntArrayEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ArrayEvaluatorFragment extends Fragment {

    private int[] start = {200};
    private int[] end = {800};
    private float[] startF = {400f};
    private float[] endF = {1000f};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.array_evaluator_fragment, container, false);
        initData(view);
        return view;
    }

    private void initData(View view) {
        Button button = (Button) view.findViewById(R.id.animator_btn);
        final TextView textView = (TextView) view.findViewById(R.id.text_view);
        final TextView textView1 = (TextView) view.findViewById(R.id.text_view_2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ValueAnimator animator = ValueAnimator.ofObject(new IntArrayEvaluator(),start,end);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int[] curValue = (int[]) animation.getAnimatedValue();
                        textView.layout(textView.getLeft(), curValue[0],textView.getRight(), curValue[0] + textView.getHeight());
                    }
                });
                animator.setDuration(3000);
                animator.start();


                final ValueAnimator valueAnimator = ValueAnimator.ofObject(new FloatArrayEvaluator(), startF, endF);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float[] curValue = (float[]) valueAnimator.getAnimatedValue();
                        int temp = (int) curValue[0];
                        textView1.layout(textView1.getLeft(), temp,textView1.getRight(), temp + textView1.getHeight());
                    }
                });
                valueAnimator.setDuration(3000);
                valueAnimator.start();
            }
        });
    }
}

package com.vivo.wenruan.evaluatordemo;

import android.animation.ObjectAnimator;
import android.animation.PointFEvaluator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PointFEvaluatorFragment extends Fragment {

    PointView mPointView = new PointView(getContext());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rect_evaluator_fragment, container, false);
        initData(view);
        return view;
    }

    private void initData(View view) {
        Button button = (Button) view.findViewById(R.id.animator_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofObject(mPointView, "Radius",
                        new PointFEvaluator(), new PointF(0, 0), new PointF(1, 1));
                animator.start();
            }
        });
    }
}

package com.vivo.wenruan.evaluatortest;

import android.animation.ObjectAnimator;
import android.animation.RectEvaluator;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RectEvaluatorFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rect_evaluator_fragment, container, false);
        initData(view);
        return view;
    }

    private void initData(View view) {
        final View someImage = view.findViewById(R.id.some_image);
        Button button = (Button) view.findViewById(R.id.animator_btn);
        Rect local = new Rect(400, 600, 610, 800);
        someImage.getLocalVisibleRect(local);
        final Rect from = new Rect(local);
        final Rect to = new Rect(0,0,1200,2000);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator anim = ObjectAnimator.ofObject(someImage, "clipBounds", new RectEvaluator(), from, to);
                anim.setDuration(2000);
                anim.start();
            }
        });
    }
}

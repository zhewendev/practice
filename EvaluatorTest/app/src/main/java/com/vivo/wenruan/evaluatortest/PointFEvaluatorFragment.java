package com.vivo.wenruan.evaluatortest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PointFEvaluatorFragment extends Fragment {

    private MyPointView myPointView;
    private static final int DURATION = 2000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pointf_evaluator_fragment, container, false);
        initData(view);
        return view;
    }

    private void initData(View view) {
        Button button = (Button) view.findViewById(R.id.animator_btn);
        myPointView = (MyPointView) view.findViewById(R.id.my_point_view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPointView.doAnimation();
            }
        });
    }
}

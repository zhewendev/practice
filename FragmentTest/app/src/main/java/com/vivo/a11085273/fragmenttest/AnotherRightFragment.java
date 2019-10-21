package com.vivo.a11085273.fragmenttest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class AnotherRightFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.another_right_fragment, container, false);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString("param");
            Toast.makeText(getActivity(), mParam1, Toast.LENGTH_LONG).show();
        }
        return view;
    }

    public static AnotherRightFragment newInstance(String text) {
        AnotherRightFragment fragment = new AnotherRightFragment();
        Bundle args = new Bundle();
        args.putString("param", text);
        fragment.setArguments(args);
        return fragment;
    }
}

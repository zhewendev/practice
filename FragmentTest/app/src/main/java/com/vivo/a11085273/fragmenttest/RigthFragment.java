package com.vivo.a11085273.fragmenttest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class RigthFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.right_fragment, container, false);
        sendResult(Activity.RESULT_OK);
        return view;
    }

    public void setLog(String str) {
        Toast.makeText(getActivity(),str,Toast.LENGTH_LONG).show();
    }

    private void sendResult(int reusltOk) {
        if (getTargetFragment() == null) {
            return;
        } else {
            Intent intent = new Intent();
            intent.putExtra("key", "Fragment通信进行中");
            getTargetFragment().onActivityResult(111,reusltOk, intent);
        }
    }
}

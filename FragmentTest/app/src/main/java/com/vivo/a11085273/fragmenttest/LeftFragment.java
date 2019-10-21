package com.vivo.a11085273.fragmenttest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.TransactionTooLargeException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class LeftFragment extends Fragment {

//    private titleSelectInterface mSelectInterface;

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            mSelectInterface = (titleSelectInterface) context;
//        } catch (Exception e) {
//            throw new ClassCastException(context.toString() + "must implement LeftFragment.titleSelectInterface");
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_fragment, container,false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        send();
//        mSelectInterface.onTitleSelect("Fragment通信");
    }

//    public interface titleSelectInterface {
//        void onTitleSelect(String title);
//    }

    private void send() {
//        FragmentManager manager = getFragmentManager();
//        RigthFragment rigthFragment = (RigthFragment) manager.fi
//        setTargetFragment(rigthFragment,111);
        RigthFragment fragment = new RigthFragment();
        fragment.setTargetFragment(this,111);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        } else {
            String string = data.getStringExtra("key");
            Toast.makeText(getActivity(),string,Toast.LENGTH_LONG).show();
        }
    }
}

package com.vivo.wenruan.evaluatortest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.fragment_jump_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (flag) {
                    case 0 :
                        replaceFragment(new ArgbEvaluatorFragment());
                        button.setText(getString(R.string.argbevaluator_fragment));
                        flag++;
                        break;
                    case 1 :
                        replaceFragment(new RectEvaluatorFragment());
                        button.setText(getString(R.string.rectevaluator_fragment));
                        flag++;
                        break;
                    case 2 :
                        replaceFragment(new PointFEvaluatorFragment());
                        button.setText(getString(R.string.pointf_evaluator_fragment));
                        flag++;
                        break;
                    case 3 :
                        replaceFragment(new ArrayEvaluatorFragment());
                        button.setText(getString(R.string.arrayevaluagor_fragment));
                        flag = 0;
                        break;
                }
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.evaluator_fragment, fragment);
        transaction.commit();
    }
}

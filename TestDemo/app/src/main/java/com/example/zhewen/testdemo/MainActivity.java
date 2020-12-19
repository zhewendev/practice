package com.example.zhewen.testdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vivo.springkit.nestedScroll.NestedScrollLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    private ProgressDialog progressDialog;
//    private TextView mTextView;
//    private TextView mTextViewDismiss;
//    private CheckBox mCheckBox;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mTextView = findViewById(R.id.tv_btn);
//        mTextViewDismiss = findViewById(R.id.tv_btn_dismiss);
//        mCheckBox = findViewById(R.id.checkbox);
//        mTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCheckBox.setChecked(true);
//            }
//        });
//        mTextViewDismiss.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                mCheckBox.setChecked(false);
//            }
//        });
//    }
//
//    public void buildProgressDialog(int id) {
//        if (progressDialog == null) {
//            progressDialog = new ProgressDialog(this);
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        }
//        progressDialog.setMessage(getString(id));
//        progressDialog.setCancelable(true);
//        progressDialog.show();
//    }
//
//    public void cancelProgressDialog() {
//        if (progressDialog != null)
//            if (progressDialog.isShowing()) {
//                progressDialog.dismiss();
//            }
//    }

    private RecyclerView rv_test;
    private ArrayList<String> firstList = new ArrayList<>();
    private ArrayList<String> secondList = new ArrayList<>();
    private TextView mTextView;
    private RadioButton radioButton;
//    private NestedScrollLayout nestedScrollLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.tv_switch);
        radioButton = findViewById(R.id.radio);
//        nestedScrollLayout = findViewById(R.id.nested);
//        nestedScrollLayout.setBounceConfig(0.8,1);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton.setChecked(!radioButton.isChecked());
            }
        });
        //条目模拟数据
        firstList.add("1.单选:你是谁");
        firstList.add("2.单选:你是谁");
        firstList.add("3.单选:你是谁");
        firstList.add("4.单选:你是谁");
        firstList.add("5.单选:你是谁");
        firstList.add("1.单选:你是谁");
        firstList.add("2.单选:你是谁");
        firstList.add("3.单选:你是谁");
        firstList.add("4.单选:你是谁");
        firstList.add("5.单选:你是谁");
        firstList.add("1.单选:你是谁");
        firstList.add("2.单选:你是谁");
        firstList.add("3.单选:你是谁");
        firstList.add("4.单选:你是谁");
        firstList.add("5.单选:你是谁");
        firstList.add("1.单选:你是谁");
        firstList.add("2.单选:你是谁");
        firstList.add("3.单选:你是谁");
        firstList.add("4.单选:你是谁");
        firstList.add("5.单选:你是谁");
        //选项模拟数据
        secondList.add("选项A");
        secondList.add("选项B");
        secondList.add("选项C");
        secondList.add("选项D");
        secondList.add("选项E");
        rv_test = findViewById(R.id.rv_test);
        //RecyclerView适配器
        rv_test.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL,false));
        FirstAdapter firstAdapter = new FirstAdapter(MainActivity.this,firstList,secondList);
        rv_test.setAdapter(firstAdapter);

    }
}

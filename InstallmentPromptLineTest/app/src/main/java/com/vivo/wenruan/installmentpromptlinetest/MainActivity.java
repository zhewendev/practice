package com.vivo.wenruan.installmentpromptlinetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<InstallmentPromptLineBean> oneData = new ArrayList<>();
    private ArrayList<InstallmentPromptLineBean> twoData = new ArrayList<>();
    private InstallmentPromptLine mOne;
    private InstallmentPromptLine mTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        initView();
        initData();
        setData();
    }

    private void initView() {
        mOne = (InstallmentPromptLine) findViewById(R.id.sv_one);
        mTwo = (InstallmentPromptLine) findViewById(R.id.sv_two);
    }

    private void initData() {
        oneData.add(new InstallmentPromptLineBean("第1期",InstallmentPromptLineBean.StepStatus.GENERAL, 400, false, ""));
        oneData.add(new InstallmentPromptLineBean("",InstallmentPromptLineBean.StepStatus.UNDERWAY, 400, true, "第6期，待还款"));
        oneData.add(new InstallmentPromptLineBean("第12期",InstallmentPromptLineBean.StepStatus.GENERAL, 0, false, ""));

        twoData.add(new InstallmentPromptLineBean("机构放款",InstallmentPromptLineBean.StepStatus.FAILED, 200, false, ""));
        twoData.add(new InstallmentPromptLineBean("第1期",InstallmentPromptLineBean.StepStatus.GENERAL, 600, false, ""));
        twoData.add(new InstallmentPromptLineBean("第12期",InstallmentPromptLineBean.StepStatus.GENERAL, 0, false, ""));
    }

    private void setData() {
        mOne.setData(oneData);
        mTwo.setData(twoData);

    }
}

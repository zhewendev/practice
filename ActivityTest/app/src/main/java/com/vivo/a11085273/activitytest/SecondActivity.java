package com.vivo.a11085273.activitytest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.putExtra("data_return","Hello FirstActivity");
//                setResult(RESULT_OK, intent);
//                finish();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory("com.vivo.a11085273.activitytest.CATEGORY");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
//                intent.setClassName("com.vivo.a11085273.activitytest","com.vivo.a11085273.activitytest.ThirdActivity");
                startActivity(intent);
            }
        });
    }

    public static void actionStart(Context context, String data1, String data2) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
//        context.startActivities(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data_return","Hello FirstActivity");
        setResult(RESULT_OK, intent);
        finish();
    }
}

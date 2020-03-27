package com.baiheng.lowdialogfragment;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.jump_text);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doJump();
                finish();
                Toast.makeText(MainActivity.this,"this is the MainActivity",Toast.LENGTH_LONG).show();
            }
        });
//        PayDialog payDialog = new PayDialog(this,"");
//        payDialog.pay();
        mTextView.setText("this is the MainActivity");
    }

    private void doJump() {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.baiheng.lowdialogfragment","com.baiheng.lowdialogfragment.Main2Activity");
        intent.setComponent(componentName);
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
//                    mTextView.setText("JUMP from Main2Activity");
                }
                break;
                default:
                    break;
        }
    }
}

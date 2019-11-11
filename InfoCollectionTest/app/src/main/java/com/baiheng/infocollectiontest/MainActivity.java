package com.baiheng.infocollectiontest;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity implements ClearEditText.InputListener,View.OnClickListener {

//    public static Pattern p =
//            Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    public static Pattern p = Pattern.compile("^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");
    public static Pattern pan = Pattern.compile("[a-zA-Z]{5}[0-9]{4}[a-zA-z0-9]");
    private static final String TYPE = "name";
    private static final String TYPE1 = "email";

    private ClearEditText mUserName;
    private ImageView mClearName;
    private View mNameSubline;
    private ClearEditText mUserEmail;
    private ImageView mClearEmail;
    private View mEmailSubline;
    private EditText mUserPan;
    private Button mInfoSubmit;
    private Button mShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserName = (ClearEditText) findViewById(R.id.edit_name);
        mClearName = (ImageView) findViewById(R.id.clear_name);
        mNameSubline = (View) findViewById(R.id.edit_name_subline);
        mUserEmail = (ClearEditText) findViewById(R.id.edit_email);
        mClearEmail = (ImageView) findViewById(R.id.clear_email);
        mEmailSubline = (View) findViewById(R.id.edit_email_subline);
        mShow = (Button) findViewById(R.id.edit_show);
        mUserName.setInputListener(this);
        mUserName.setType(TYPE);
        mUserName.setOnFocusChangeListener(mUserName);
        mUserName.requestFocus();
        mClearName.setOnClickListener(this);
        mUserEmail.setInputListener(this);
        mUserEmail.setType(TYPE1);
        mUserEmail.setOnFocusChangeListener(mUserEmail);
        mShow.setOnClickListener(this);
    }

    @Override
    public void onTextChange(String type, boolean show, int length) {
        switch (type) {
            case TYPE:
                if (show) {
                    mNameSubline.setBackgroundColor(ContextCompat.getColor(this,R.color.colorAccent));
                    if (length > 0) {
                        mClearName.setVisibility(View.VISIBLE);
                    }
                } else {
                    mClearName.setVisibility(View.GONE);
                    mNameSubline.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
                }
                break;
            case TYPE1:
                if (show) {
                    mEmailSubline.setBackgroundColor(ContextCompat.getColor(this,R.color.colorAccent));
                    if (length > 0) {
                        mClearEmail.setVisibility(View.VISIBLE);
                    }
                } else {
                    mClearEmail.setVisibility(View.GONE);
                    mEmailSubline.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
                }
            default:
                break;
        }
        if (!TextUtils.isEmpty(mUserName.getText()) || !TextUtils.isEmpty(mUserEmail.getText())) {
            mShow.setText("可以点击了");
        } else {
            mShow.setText("不可点击了");
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.clear_name:
                mUserName.setText("");
                break;
            case R.id.edit_show:
                validate();
            default:
                break;
        }
    }

    public void validate() {
//        String data = mUserEmail.getText().toString().trim();
//        if (validateEmail(data)) {
//            Toast.makeText(this,"邮箱格式正确",Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this,"邮箱格式错误",Toast.LENGTH_LONG).show();
//        }
        String pan = mUserName.getText().toString().trim();
        if (validatePan(pan)) {
            Toast.makeText(this,"PAN格式正确",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"PAN格式错误",Toast.LENGTH_LONG).show();
        }
    }

    public boolean validateEmail(String data) {
        if (data == null || "".equals(data)) {
            return false;
        }
        Matcher m = p.matcher(data);
        return m.matches();
    }

    public boolean validatePan(String data) {
        if (data == null || "".equals(data) || data.length() != 10) {
            return false;
        }
        Matcher m = pan.matcher(data);
        return m.matches();
    }
}

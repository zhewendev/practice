package com.vivo.a11085273.helloworldtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private View mHeadArea;
    private TextView mHeadTitle;
    private ImageView mHeadPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mHeadArea = findViewById(R.id.game_category_special_head_item_re);
//        mHeadTitle = (TextView) findViewById(R.id.game_category_special_text);
//        mHeadPicture = (ImageView) findViewById(R.id.game_category_special_iv);
//        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.game_category_special_re1);
//        relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "layout",Toast.LENGTH_SHORT).show();
//            }
//        });
//        View view = findViewById(R.id.game_category_special_re2);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "layout2",Toast.LENGTH_SHORT).show();
//            }
//        });
        ViewGroup parent = findViewById(R.id.id_container);
        View view = findViewById(R.id.tv_test);
        View decorview = ((Activity) view.getContext()).getWindow().getDecorView();
        if (view == parent) {
            Log.d("MainActivity","view = parent");
        }
        if (decorview == view) {
            Log.d("MainActivity","decorview = view");
        }
        view =(View) view.getParent();
        if (view == parent) {
            Log.d("MainActivity","graview = parent");
        }
        if (decorview == view) {
            Log.d("MainActivity","decorview = graview");
        }
        Log.d("MainActivity","test");
        ViewGroup test = (ViewGroup) parent.getParent();
        if (test == parent) {
            Log.d("MainActivity","test = parent");
        }

    }
}

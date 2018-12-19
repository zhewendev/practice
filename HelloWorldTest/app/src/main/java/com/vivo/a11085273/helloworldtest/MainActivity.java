package com.vivo.a11085273.helloworldtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private View mHeadArea;
    private TextView mHeadTitle;
    private ImageView mHeadPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_category_special_head);
        mHeadArea = findViewById(R.id.game_category_special_head_item_re);
        mHeadTitle = (TextView) findViewById(R.id.game_category_special_text);
        mHeadPicture = (ImageView) findViewById(R.id.game_category_special_iv);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.game_category_special_re1);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "layout",Toast.LENGTH_SHORT).show();
            }
        });
        View view = findViewById(R.id.game_category_special_re2);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "layout2",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

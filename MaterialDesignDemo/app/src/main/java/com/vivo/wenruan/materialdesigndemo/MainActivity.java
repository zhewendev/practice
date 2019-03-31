package com.vivo.wenruan.materialdesigndemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActionMenuView mMenuView;
    private ActionMenuView mMenuViewTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToobar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToobar);
        mMenuView = (ActionMenuView) findViewById(R.id.amv_calendar);
        mMenuViewTwo = (ActionMenuView) findViewById(R.id.amv_calendar_two);
        mMenuView.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onOptionsItemSelected(item);
            }
        });
        getMenuInflater().inflate(R.menu.other_menu, mMenuView.getMenu());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,mMenuView.getMenu());
//        MenuItem item = menu.findItem(R.id.calendar);
//        item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.calendar:
                Toast.makeText(this,"mainActivity",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

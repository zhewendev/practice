package com.vivo.a11085273.secondactivitytest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class FirstActivity extends AppCompatActivity {

//    private boolean flag_enable = true;
    private TextView tv_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this, "this is the button1", Toast.LENGTH_SHORT).show();

            }
        });
        /*********************************************/

        tv_context = (TextView) findViewById(R.id.tv_context);
        registerForContextMenu(tv_context);
        /********************************************/
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflator = new MenuInflater(this);
        inflator.inflate(R.menu.menu_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.blue:
                tv_context.setTextColor(Color.BLUE);
                break;
            case R.id.green:
                tv_context.setTextColor(Color.GREEN);
                break;
        }
        return true;
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//        menu.add(0, 110, 1, "Start");
//        menu.add(0, 111, 2, "Over");
//        return true;
//    }
//
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        menu.findItem(R.id.add_item).setEnabled(flag_enable);
//        menu.findItem(R.id.remove_item).setEnabled(!flag_enable);
//        menu.findItem(110).setEnabled(flag_enable);
//        menu.findItem(111).setEnabled(false);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.add_item:
//                flag_enable = !flag_enable;
//                invalidateOptionsMenu();
//                Toast.makeText(this, "you click add", Toast.LENGTH_SHORT ).show();
//                break;
//            case 110:
//                flag_enable = !flag_enable;
//                invalidateOptionsMenu();
//                Toast.makeText(this, "you click Start", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                flag_enable = !flag_enable;
//                invalidateOptionsMenu();
//                Toast.makeText(this, "you click other Button", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
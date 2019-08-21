package com.vivo.a11085273.activitytest;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FirstActivity", this.toString());
        setContentView(R.layout.first_layout);
        if (savedInstanceState != null) {
            String tempData = savedInstanceState.getString("data_key");
            Log.d("FirstActivity", tempData);
        }
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivityForResult(intent,1);
            }
        });

        Button btnRegister =(Button) findViewById(R.id.button_reg_con);
        registerForContextMenu(btnRegister);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this,"You clicked Add", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.remove_item:
//                Toast.makeText(this,"You clikced Remove", Toast.LENGTH_SHORT).show();
//                break;
            default:
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something you just typed";
        outState.putString("data_key", tempData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this,"You clicked Add", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.remove_item:
//                Toast.makeText(this,"You clikced Remove", Toast.LENGTH_SHORT).show();
//                break;
            default:
        }
        return true;
    }

    public void showMenu(MenuItem Menu){
        switch (Menu.getItemId()) {
            case R.id.remove_item:
                Toast.makeText(this,"You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        switch(requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("FirstActivity",returnedData);
                }
                break;
            default:
        }
    }
}
//public class FirstActivity extends AppCompatActivity {
//
//    // 线程变量
//    MyTask mTask;
//
//    // 主布局中的UI组件
//    Button button,cancel; // 加载、取消按钮
//    TextView text; // 更新的UI组件
//    ProgressBar progressBar; // 进度条
//
//    /**
//     * 步骤1：创建AsyncTask子类
//     * 注：
//     *   a. 继承AsyncTask类
//     *   b. 为3个泛型参数指定类型；若不使用，可用java.lang.Void类型代替
//     *      此处指定为：输入参数 = String类型、执行进度 = Integer类型、执行结果 = String类型
//     *   c. 根据需求，在AsyncTask子类内实现核心方法
//     */
//    private class MyTask extends AsyncTask<String, Integer, String> {
//
//        // 方法1：onPreExecute（）
//        // 作用：执行 线程任务前的操作
//        @Override
//        protected void onPreExecute() {
//            text.setText("加载中");
//            // 执行前显示提示
//        }
//
//
//        // 方法2：doInBackground（）
//        // 作用：接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果
//        // 此处通过计算从而模拟“加载进度”的情况
//        @Override
//        protected String doInBackground(String... params) {
//
//            try {
//                int count = 0;
//                int length = 1;
//                while (count<99) {
//
//                    count += length;
//                    // 可调用publishProgress（）显示进度, 之后将执行onProgressUpdate（）
//                    publishProgress(count);
//                    // 模拟耗时任务
//                    Thread.sleep(50);
//                }
//            }catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        // 方法3：onProgressUpdate（）
//        // 作用：在主线程 显示线程任务执行的进度
//        @Override
//        protected void onProgressUpdate(Integer... progresses) {
//
//            progressBar.setProgress(progresses[0]);
//            text.setText("loading..." + progresses[0] + "%");
//
//        }
//
//        // 方法4：onPostExecute（）
//        // 作用：接收线程任务执行结果、将执行结果显示到UI组件
//        @Override
//        protected void onPostExecute(String result) {
//            // 执行完毕后，则更新UI
//            text.setText("加载完毕");
//        }
//
//        // 方法5：onCancelled()
//        // 作用：将异步任务设置为：取消状态
//        @Override
//        protected void onCancelled() {
//
//            text.setText("已取消");
//            progressBar.setProgress(0);
//
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // 绑定UI组件
//        setContentView(R.layout.first_layout);
//
//        button = (Button) findViewById(R.id.button);
//        cancel = (Button) findViewById(R.id.cancel);
//        text = (TextView) findViewById(R.id.text);
//        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
//        /**
//         * 步骤2：创建AsyncTask子类的实例对象（即 任务实例）
//         * 注：AsyncTask子类的实例必须在UI线程中创建
//         */
//        mTask = new MyTask();
//
//        // 加载按钮按按下时，则启动AsyncTask
//        // 任务完成后更新TextView的文本
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                /**
//                 * 步骤3：手动调用execute(Params... params) 从而执行异步线程任务
//                 * 注：
//                 *    a. 必须在UI线程中调用
//                 *    b. 同一个AsyncTask实例对象只能执行1次，若执行第2次将会抛出异常
//                 *    c. 执行任务中，系统会自动调用AsyncTask的一系列方法：onPreExecute() 、doInBackground()、onProgressUpdate() 、onPostExecute()
//                 *    d. 不能手动调用上述方法
//                 */
//                mTask.execute();
//            }
//        });
//
//        cancel = (Button) findViewById(R.id.cancel);
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 取消一个正在执行的任务,onCancelled方法将会被调用
//                mTask.cancel(true);
//            }
//        });
//
//    }
//}

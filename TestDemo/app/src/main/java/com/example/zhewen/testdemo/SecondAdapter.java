package com.example.zhewen.testdemo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> secondList;
    private int index = -1;//标记当前选择的选项
    private int lastIndex = -1; //标记上次的选项
    private NotifyRefreshListener mNotifyRefreshListener;
    private RadioButton radioButton;

    public SecondAdapter(Context context, ArrayList<String> secondList) {
        this.context = context;
        this.secondList = secondList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_radio, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv_question_item.setText(secondList.get(position));
        holder.rb_question_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(context, "你选择的选项是" + secondList.get(position), Toast.LENGTH_SHORT).show();
                    lastIndex = index;
                    index = position;
                    if (lastIndex != -1){
                        notifyItemRangeChanged(lastIndex,1);
                    }
//                    try {
//                        Thread.currentThread().sleep(20000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    notifyDataSetChanged();
                }
            }
        });
        if (lastIndex == position){
            radioButton = holder.rb_question_item;
//            mNotifyRefreshListener.refresh(holder.rb_question_item);
//            try {
//                Thread.currentThread().sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
//        if (lastIndex > position){
//            radioButton.setChecked(false);
//        }
//        holder.rb_question_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                index = position;
//                lastIndex = index;
////                notifyDataSetChanged();
//            }
//        });
//        if (lastIndex == position) {
//            holder.rb_question_item.setChecked(false);
//        }
//        if (index == position) {
//            try {
//                Thread.currentThread().sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            holder.rb_question_item.setChecked(true);
//        } else if (lastIndex == position) {
//            try {
//                Thread.currentThread().sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            holder.rb_question_item.setChecked(false);
//        }
    }

    @Override
    public int getItemCount() {
        return secondList.size();
    }

    public SecondAdapter setNotifyRefreshListener(NotifyRefreshListener notifyRefreshListener){
        mNotifyRefreshListener = notifyRefreshListener;
        return this;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RadioButton rb_question_item;
        TextView tv_question_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            rb_question_item = itemView.findViewById(R.id.rb_question_item);
            tv_question_item = itemView.findViewById(R.id.tv_question_item);
        }
    }

    public interface NotifyRefreshListener{
        void refresh(View view);
    }

}


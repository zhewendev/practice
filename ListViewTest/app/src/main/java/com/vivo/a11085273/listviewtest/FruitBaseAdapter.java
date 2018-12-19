package com.vivo.a11085273.listviewtest;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitBaseAdapter extends BaseAdapter {

//    private Context context;
    private List<Fruit> list;//数据源
    private LayoutInflater inflater; //初始化ListView每个item的布局文件

    public FruitBaseAdapter(Context context, List<Fruit> objects) {
        this.list = objects;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit =(Fruit) getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            //解析布局
            convertView = inflater.inflate(R.layout.fruit_item, null);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) convertView.findViewById(R.id.fruit_image);
            viewHolder.fruitName = (TextView) convertView.findViewById(R.id.fruit_name);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return convertView;

    }

    //保存当前所有控件
     private class ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
    }
}

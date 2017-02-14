package com.example.quanla.pomodoro.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.databases.models.Color;

import java.util.ArrayList;

/**
 * Created by QuanLA on 2/13/2017.
 */

public class MyColorAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Color> mColor;

    public MyColorAdapter(Context c,ArrayList<Color> colorID){
        mContext=c;
        mColor=colorID;
    }
    @Override
    public int getCount() {
        return mColor.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_color,parent,false);
            holder=new ViewHolder();
            holder.iv2=(ImageView)convertView.findViewById(R.id.ib_check);
            holder.iv1=(ImageView) convertView.findViewById(R.id.imColor);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }
        if(mColor.get(position).isCheck())
        {
            holder.iv2.setVisibility(View.VISIBLE);
        }
        else {
            holder.iv2.setVisibility(View.GONE);
        }
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(mContext.getResources().getColor(mColor.get(position).getColor()),PorterDuff.Mode.MULTIPLY);

        holder.iv1.getDrawable().setColorFilter(porterDuffColorFilter);


        return convertView;
    }
    public class ViewHolder{
        private ImageView iv1;
        private ImageView iv2;
    }
}

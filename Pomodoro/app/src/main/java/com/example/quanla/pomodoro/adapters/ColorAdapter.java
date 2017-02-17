package com.example.quanla.pomodoro.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.adapters.viewholders.TaskColorViewHolder;
import com.example.quanla.pomodoro.databases.models.Color;

/**
 * Created by QuanLA on 2/17/2017.
 */

public class ColorAdapter extends RecyclerView.Adapter<TaskColorViewHolder> {

    private int selectedPosition;

    private static String TAG = "Task Color Adapter";

    @Override
    public TaskColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_color, parent, false);
        return new TaskColorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskColorViewHolder holder, final int position) {
        String color = Color.COLORS[position];
        holder.bind(color);
        if(selectedPosition==position){
            holder.setCheck(true);
        }
        else {
            holder.setCheck(false);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    public String getSelectedColor(){
        return Color.COLORS[selectedPosition];
    }

    public void setSelectedColor(String color){
        for(int colorIndex = 0; colorIndex < Color.COLORS.length; colorIndex++){
            if(Color.COLORS[colorIndex].equalsIgnoreCase(color)){
                selectedPosition = colorIndex;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return Color.COLORS.length;
    }
}

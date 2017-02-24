package com.example.quanla.pomodoro.adapters.viewholders;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QuanLA on 2/17/2017.
 */

public class TaskColorViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.v_task_color)
    View vTaskColor;

    @BindView(R.id.iv_check)
    ImageView ivCheck;


    public TaskColorViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(String color) {
        Utils.setSolidColor(vTaskColor, color);
    }


    public void setCheck(boolean check){
        if(check){
            ivCheck.setVisibility(View.VISIBLE);
        }
        else {
            ivCheck.setVisibility(View.GONE);
        }
    }
}

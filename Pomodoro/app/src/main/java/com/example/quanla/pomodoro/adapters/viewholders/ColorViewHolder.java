package com.example.quanla.pomodoro.adapters.viewholders;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.databases.DbContextColor;
import com.example.quanla.pomodoro.databases.models.Color;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QuanLA on 2/11/2017.
 */

public class ColorViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.rl_color)
    RelativeLayout rlColor;


    public ColorViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Color color){
        GradientDrawable drawable = (GradientDrawable) rlColor.getBackground();
        drawable.setColor(android.graphics.Color.parseColor(color.getColor()));


    }
}

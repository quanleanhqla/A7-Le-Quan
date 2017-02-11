package com.example.quanla.pomodoro.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.adapters.viewholders.ColorViewHolder;
import com.example.quanla.pomodoro.databases.DbContextColor;
import com.example.quanla.pomodoro.databases.models.Color;

/**
 * Created by QuanLA on 2/11/2017.
 */

public class ColorAdapter extends RecyclerView.Adapter<ColorViewHolder> {
    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_color, parent, false);

        ColorViewHolder colorViewHolder = new ColorViewHolder(itemView);
        return colorViewHolder;
    }

    @Override
    public void onBindViewHolder(ColorViewHolder holder, int position) {
        Color color = DbContextColor.instance.allColor().get(position);

        holder.bind(color);
    }

    @Override
    public int getItemCount() {
        return DbContextColor.instance.allColor().size();
    }
}

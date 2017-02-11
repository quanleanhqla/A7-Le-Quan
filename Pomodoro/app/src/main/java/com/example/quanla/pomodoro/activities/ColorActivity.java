package com.example.quanla.pomodoro.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.adapters.ColorAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorActivity extends AppCompatActivity {

    @BindView(R.id.rv_color)
    RecyclerView rvColor;

    private ColorAdapter colorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        setupUI();

    }

    private void setupUI() {
        ButterKnife.bind(this);
        colorAdapter = new ColorAdapter();
        rvColor.setAdapter(colorAdapter);
        rvColor.setLayoutManager(new GridLayoutManager(this, 4));
    }
}

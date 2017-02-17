package com.example.quanla.pomodoro.fragments.optionItemStrategies;

import android.view.MenuItem;

import com.example.quanla.pomodoro.databases.models.Task;

/**
 * Created by QuanLA on 2/17/2017.
 */

public interface Strategy {
    void doOptionItem(Task task, String taskName, float payment, String color);
}

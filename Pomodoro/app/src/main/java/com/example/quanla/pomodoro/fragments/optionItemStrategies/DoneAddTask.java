package com.example.quanla.pomodoro.fragments.optionItemStrategies;

import android.view.MenuItem;

import com.example.quanla.pomodoro.databases.DbContext;
import com.example.quanla.pomodoro.databases.models.Task;

/**
 * Created by QuanLA on 2/17/2017.
 */

public class DoneAddTask implements Strategy {

    public DoneAddTask() {
    }

    @Override
    public void doOptionItem(Task task, String taskName, float payment, String color) {
        task = new Task(taskName, color, payment);

        //addToDataBase
        DbContext.instance.addTask(task);
    }
}

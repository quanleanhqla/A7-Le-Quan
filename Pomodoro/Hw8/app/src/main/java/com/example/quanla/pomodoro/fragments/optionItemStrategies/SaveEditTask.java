package com.example.quanla.pomodoro.fragments.optionItemStrategies;

import com.example.quanla.pomodoro.databases.models.Task;

/**
 * Created by QuanLA on 2/18/2017.
 */

public class SaveEditTask implements Strategy {
    public SaveEditTask() {
    }

    @Override
    public void doOptionItem(Task task, String taskName, float payment, String color) {
        task.setName(taskName);
        task.setPayment(payment);
        task.setColor(color);
    }
}

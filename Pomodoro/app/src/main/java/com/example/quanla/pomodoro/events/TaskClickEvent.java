package com.example.quanla.pomodoro.events;

import com.example.quanla.pomodoro.databases.models.Task;

/**
 * Created by QuanLA on 3/7/2017.
 */

public class TaskClickEvent {
    private TaskClick taskClick;

    private Task task;

    public TaskClickEvent(TaskClick taskClick) {

        this.taskClick = taskClick;
    }

    public TaskClickEvent(Task task, TaskClick taskClick) {
        this.task = task;
        this.taskClick = taskClick;
    }

    public TaskClick getTaskClick() {
        return taskClick;
    }

    public void setTaskClick(TaskClick taskClick) {
        this.taskClick = taskClick;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}

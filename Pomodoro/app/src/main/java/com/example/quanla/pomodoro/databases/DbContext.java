package com.example.quanla.pomodoro.databases;

import com.example.quanla.pomodoro.databases.models.Task;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by QuanLA on 2/8/2017.
 */

public class DbContext {
    public final static DbContext instance = new DbContext();

    public List<Task> allTasks(){
        //1: Create arry list
        ArrayList<Task> tasks = new ArrayList<>();

        //2: add some tasks and return
        tasks.add(new Task("Study recycle view", "#C62828"));
        tasks.add(new Task("Practice recycle view", "#AD1457"));
        tasks.add(new Task("Study recycle view", "#6A1B9A"));
        tasks.add(new Task("Study recycle view", "#BBDEFB"));
        tasks.add(new Task("Study recycle view", "#7C4DFF"));

        return tasks;
    }
}

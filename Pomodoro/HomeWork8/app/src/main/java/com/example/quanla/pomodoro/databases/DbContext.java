package com.example.quanla.pomodoro.databases;

import com.example.quanla.pomodoro.databases.models.Color;
import com.example.quanla.pomodoro.databases.models.Task;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by QuanLA on 2/8/2017.
 */

public class DbContext {
    public final static DbContext instance = new DbContext();

    private ArrayList<Task> tasks;

    public List<Task> allTasks(){
        //1: Create arry list
        if(tasks==null) {
            tasks = new ArrayList<>();

            //2: add some tasks and return
        }

        return tasks;
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }
}

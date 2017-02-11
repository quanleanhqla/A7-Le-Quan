package com.example.quanla.pomodoro.databases;

import com.example.quanla.pomodoro.databases.models.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QuanLA on 2/11/2017.
 */

public class DbContextColor {
    public final static DbContextColor instance = new DbContextColor();

    public List<Color> allColor(){
        //1: Create arry list
        ArrayList<Color> tasks = new ArrayList<>();

        //2: add some tasks and return
        tasks.add(new Color("#C62828"));
        tasks.add(new Color("#AD1457"));
        tasks.add(new Color("#6A1B9A"));
        tasks.add(new Color("#BBDEFB"));
        tasks.add(new Color("#7C4DFF"));
        tasks.add(new Color("#757575"));
        tasks.add(new Color("#FF3D00"));
        tasks.add(new Color("#FDD835"));


        return tasks;
    }
}

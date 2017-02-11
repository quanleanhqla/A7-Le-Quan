package com.example.quanla.pomodoro.databases.models;

/**
 * Created by QuanLA on 2/8/2017.
 */

public class Task {
    private String name;
    private String color;

    public Task(String name, String color) {
        this.color = color;
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Task{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

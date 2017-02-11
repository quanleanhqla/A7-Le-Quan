package com.example.quanla.pomodoro.databases.models;

/**
 * Created by QuanLA on 2/11/2017.
 */

public class Color {
    private String color;

    public Color(String color) {
        this.color = color;

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Color{" +
                "color='" + color + '\'' +
                '}';
    }
}

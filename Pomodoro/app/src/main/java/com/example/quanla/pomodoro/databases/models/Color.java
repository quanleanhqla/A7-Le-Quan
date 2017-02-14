package com.example.quanla.pomodoro.databases.models;

/**
 * Created by QuanLA on 2/11/2017.
 */

public class Color {
    private int color;
    private boolean isCheck;

    public Color(int color, boolean isCheck) {
        this.color = color;
        this.isCheck = isCheck;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    @Override
    public String toString() {
        return "Color{" +
                "color='" + color + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }
}

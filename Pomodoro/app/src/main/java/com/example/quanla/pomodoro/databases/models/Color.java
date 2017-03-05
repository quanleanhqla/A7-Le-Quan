package com.example.quanla.pomodoro.databases.models;

/**
 * Created by QuanLA on 2/11/2017.
 */

public class Color {
    private String color;
    private boolean isCheck;

    public Color(String color, boolean isCheck) {
        this.color = color;
        this.isCheck = isCheck;
    }

    public String getColorString() {
        return color;
    }

    public void setColorString(String color) {
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

    public static String[] COLORS = new String[]{
            "#0091EA",
            "#00C853",
            "#FFD600",
            "#DD2C00",
            "#AA00FF",
            "#757575",
            "#F50057"
    };
}

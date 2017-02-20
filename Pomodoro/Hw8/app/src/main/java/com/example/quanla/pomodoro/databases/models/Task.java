package com.example.quanla.pomodoro.databases.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by QuanLA on 2/8/2017.
 */

public class Task {

    @SerializedName("name")
    private String name;

    @SerializedName("color")
    private String color;

    @SerializedName("payment_per_hour")
    private float payment;


    public Task(String name, String color, float payment) {
        this.color = color;
        this.name = name;
        this.payment = payment;
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

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Task{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", payment=" + payment +
                '}';
    }
}

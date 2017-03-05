package com.example.quanla.pomodoro.databases.models;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by QuanLA on 2/8/2017.
 */

public class Task extends RealmObject {
    @SerializedName("local_id")
    @PrimaryKey
    private String local_id;

    @SerializedName("name")
    private String name;

    @SerializedName("color")
    private String color;

    @SerializedName("payment_per_hour")
    private float payment;


    public Task(){
        this.name = null;
        this.color = null;
        this.payment = -100f;
    }

    public Task(String name, String color, float payment) {
        this.local_id = UUID.randomUUID().toString();
        this.color = color;
        this.name = name;
        this.payment = payment;
    }

    public Task(String name, String color, float payment, String local_id){
        this.name = name;
        this.color = color;
        this.payment = payment;
        this.local_id = local_id;
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

    public String getIdLocal() {
        return local_id;
    }

    public void setIdLocal(String idLocal) {
        this.local_id = idLocal;
    }

    @Override
    public String toString() {
        return "Task{" +
                "color='" + color + '\'' +
                ", idLocal='" + local_id + '\'' +
                ", name='" + name + '\'' +
                ", payment=" + payment +
                '}';
    }
}

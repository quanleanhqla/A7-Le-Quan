package com.example.quanla.pomodoro.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by QuanLA on 2/21/2017.
 */

public class AddTaskBodyJson {
    @SerializedName("local_id")
    private String idLocal;

    @SerializedName("name")
    private String name;

    @SerializedName("payment_per_hour")
    private float payment;

    @SerializedName("due_date")
    private String dueDate;

    @SerializedName("done")
    private boolean done;

    @SerializedName("id")
    SerializedName id;

    @SerializedName("color")
    private String color;

    public AddTaskBodyJson(String color, boolean done, String dueDate, SerializedName id, String idLocal, String name, float payment) {
        this.color = color;
        this.done = done;
        this.dueDate = dueDate;
        this.id = id;
        this.idLocal = idLocal;
        this.name = name;
        this.payment = payment;
    }

    public String getColor() {
        return color;
    }

    public boolean isDone() {
        return done;
    }

    public String getDueDate() {
        return dueDate;
    }

    public SerializedName getId() {
        return id;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public String getName() {
        return name;
    }

    public float getPayment() {
        return payment;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setId(SerializedName id) {
        this.id = id;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "AddTaskBodyJson{" +
                "color='" + color + '\'' +
                ", idLocal='" + idLocal + '\'' +
                ", name='" + name + '\'' +
                ", payment=" + payment +
                ", dueDate='" + dueDate + '\'' +
                ", done=" + done +
                ", id='" + id + '\'' +
                '}';
    }
}

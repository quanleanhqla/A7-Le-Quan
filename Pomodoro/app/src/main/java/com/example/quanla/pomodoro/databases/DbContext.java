package com.example.quanla.pomodoro.databases;

import android.content.Context;

import com.example.quanla.pomodoro.databases.models.Color;
import com.example.quanla.pomodoro.databases.models.Task;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;


/**
 * Created by QuanLA on 2/8/2017.
 */

public class DbContext {
    public static final DbContext instance = new DbContext();

    private Realm realm;

    private DbContext() {

    }

    public void InitialRealm(Context context){
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    public void add(Task task){
        realm.beginTransaction();
        realm.copyToRealm(task);
        realm.commitTransaction();

    }

    public void addOrUpdate(Task task){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(task);
        realm.commitTransaction();
    }

    public void update(Task task, String name, String color, float payment){
        realm.beginTransaction();
        task.setName(name);
        task.setColor(color);
        task.setPayment(payment);
        realm.commitTransaction();
    }

    public void delete(Task task){
        realm.beginTransaction();
        realm.where(Task.class).equalTo("local_id", task.getIdLocal()).findFirst().deleteFromRealm();
        realm.commitTransaction();
    }

    public void clearAll(){
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    public List<Task> allTasks(){
        return realm.where(Task.class).findAll();
    }
}

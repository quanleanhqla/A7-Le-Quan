package com.example.quanla.pomodoro;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.quanla.pomodoro.activities.MainActivity;
import com.example.quanla.pomodoro.databases.DbContext;
import com.example.quanla.pomodoro.databases.models.Task;
import com.example.quanla.pomodoro.networks.NetContext;
import com.example.quanla.pomodoro.networks.services.TaskService;
import com.example.quanla.pomodoro.settings.SharedPrefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by QuanLA on 1/14/2017.
 */

public class PomodoroApplication extends Application {
    private static final String TAG = PomodoroApplication.class.toString();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        SharedPrefs.init(this);

        DbContext.instance.InitialRealm(this);

        if(isOnline()) DbContext.instance.clearAll();

        getAllTask();


//        for(Task task : DbContext.instance.allTasks()){
//            Log.d(TAG, String.format("onCreate: %s", task.toString()));
//        }
    }

    private void getAllTask(){


        TaskService getTaskService = NetContext.instance.create(TaskService.class);

        getTaskService.getAllTask().enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, retrofit2.Response<List<Task>> response) {

                List<Task> tasks = response.body();
                if(tasks.toString().equals("[]")) {
                    DbContext.instance.clearAll();
                    Log.d(TAG, String.format("fail %s", response.body()));
                }
                else if(tasks==null){
                    DbContext.instance.clearAll();
                    Log.d(TAG, "Shit");
                }
                else {
                    for (Task task : tasks) {
                        Log.d(TAG, String.format("onResponse: %s", task));
                        if(task.getIdLocal()!=null) DbContext.instance.addOrUpdate(new Task(task.getName(), task.getColor(), task.getPayment(), task.getIdLocal()));

                    }

                }

            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {

            }
        });
    }

    public Boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni != null && ni.isConnected()) {
            return true;
        }
        return false;
    }

}

package com.example.quanla.pomodoro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.quanla.pomodoro.activities.MainActivity;
import com.example.quanla.pomodoro.databases.DbContext;
import com.example.quanla.pomodoro.databases.models.Task;
import com.example.quanla.pomodoro.networks.NetContext;
import com.example.quanla.pomodoro.networks.services.TaskService;
import com.example.quanla.pomodoro.services.PomodoroService;
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
        Intent intent = new Intent(this, PomodoroService.class);
        startService(intent);
        SharedPrefs.init(this);


        DbContext.instance.InitialRealm(this);



//        for(Task task : DbContext.instance.allTasks()){
//            Log.d(TAG, String.format("onCreate: %s", task.toString()));
//        }
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

package com.example.quanla.pomodoro;

import android.app.Application;
import android.util.Log;

import com.example.quanla.pomodoro.settings.SharedPrefs;

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
    }
}

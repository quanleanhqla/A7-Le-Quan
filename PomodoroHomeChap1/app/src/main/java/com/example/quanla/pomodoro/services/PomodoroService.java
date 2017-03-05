package com.example.quanla.pomodoro.services;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.quanla.pomodoro.events.TickEvent;
import com.example.quanla.pomodoro.events.TimerCommand;
import com.example.quanla.pomodoro.events.TimerCommandEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by QuanLA on 3/4/2017.
 */

public class PomodoroService extends Service {
    private long total = 10000;
    private long resumeMilis;
    private CountDownTimer countDownTimer;
    private static final String TAG = "abc";
    private TickEvent event;
    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onCommand(TimerCommandEvent event){
        if(event.getCommand()==TimerCommand.START_TIMER) {
            startTimer();
        }
        else if(event.getCommand()==TimerCommand.PAUSE_TIMER){
            pauseTimer();
        }
        else {
            Log.d(TAG, "resume");
            resumeTimer();
        }


    }


    private void startTimer(){
        countDownTimer = new CountDownTimer(total, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                resumeMilis = millisUntilFinished;
                Log.d(TAG, String.format("CountDownTimer %s", millisUntilFinished));
                event = new TickEvent(millisUntilFinished);
                EventBus.getDefault().post(event);

            }

            @Override
            public void onFinish() {
                event.setMili(0);
                EventBus.getDefault().post(event);
            }

        }.start();
    }

    private void pauseTimer(){
        countDownTimer.cancel();
    }

    private void resumeTimer(){
        countDownTimer = new CountDownTimer(resumeMilis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                event = new TickEvent(millisUntilFinished);
                EventBus.getDefault().post(event);
                resumeMilis = millisUntilFinished;
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

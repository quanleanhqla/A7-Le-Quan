package com.example.quanla.pomodoro.events;

/**
 * Created by QuanLA on 3/4/2017.
 */

public class TickEvent {
    private long mili;

    public TickEvent(long mili) {
        this.mili = mili;
    }

    public long getMili() {
        return mili;
    }

    public void setMili(long mili) {
        this.mili = mili;
    }
}

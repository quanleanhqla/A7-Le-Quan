package com.example.quanla.pomodoro.settings;

/**
 * Created by QuanLA on 1/15/2017.
 */

public class SettingDetail {
    private int worktime;
    private int timebreak;
    private int timelongbreak;
    private int position;

    public SettingDetail(int worktime, int timebreak, int timelongbreak, int position) {
        this.timebreak = timebreak;
        this.timelongbreak = timelongbreak;
        this.worktime = worktime;
        this.position = position;
    }


    public int getTimebreak() {
        return timebreak;
    }

    public int getTimelongbreak() {
        return timelongbreak;
    }

    public int getWorktime() {
        return worktime;
    }

    public int getPosition() { return position; }

    @Override
    public String toString() {
        return "SettingDetail{" +
                "position=" + position +
                ", worktime=" + worktime +
                ", timebreak=" + timebreak +
                ", timelongbreak=" + timelongbreak +
                '}';
    }
}

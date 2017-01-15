package com.example.quanla.pomodoro.settings;

/**
 * Created by QuanLA on 1/15/2017.
 */

public class SettingDetail {
    private int worktime;
    private int timebreak;
    private int timelongbreak;

    public SettingDetail(int worktime, int timebreak, int timelongbreak) {
        this.timebreak = timebreak;
        this.timelongbreak = timelongbreak;
        this.worktime = worktime;
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

    @Override
    public String toString() {
        return "SettingDetail{" +
                "timebreak=" + timebreak +
                ", worktime=" + worktime +
                ", timelongbreak=" + timelongbreak +
                '}';
    }
}

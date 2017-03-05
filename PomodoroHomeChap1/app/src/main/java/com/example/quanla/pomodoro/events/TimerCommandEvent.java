package com.example.quanla.pomodoro.events;

/**
 * Created by QuanLA on 3/4/2017.
 */



public class TimerCommandEvent {
    private TimerCommand command;

    public TimerCommandEvent(TimerCommand command) {
        this.command = command;
    }


    public TimerCommand getCommand() {
        return command;
    }

    public void setCommand(TimerCommand command) {
        this.command = command;
    }
}

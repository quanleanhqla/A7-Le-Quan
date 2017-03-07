package com.example.quanla.pomodoro.fragments;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.events.TickEvent;
import com.example.quanla.pomodoro.events.TimerCommand;
import com.example.quanla.pomodoro.events.TimerCommandEvent;
import com.github.lzyzsd.circleprogress.DonutProgress;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment {

    @BindView(R.id.btn_resume)
    Button btnResume;

    @BindView(R.id.btn_pause)
    Button btnPause;

    @BindView(R.id.dp_timer)
    DonutProgress dpTimer;






    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        EventBus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        setupUI(view);
        dpTimer.setMax(10);

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimerCommandEvent timerCommandEvent = new TimerCommandEvent(TimerCommand.PAUSE_TIMER);
                EventBus.getDefault().post(timerCommandEvent);
                btnPause.setVisibility(View.GONE);
                btnResume.setVisibility(View.VISIBLE);
            }
        });

        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimerCommandEvent timerCommandEvent = new TimerCommandEvent(TimerCommand.RESUME_TIMER);
                EventBus.getDefault().post(timerCommandEvent);
                btnResume.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    public void setupUI(View view) {
        ButterKnife.bind(this, view);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onCountDown(TickEvent tickEvent){
        dpTimer.setText("" + (tickEvent.getMili()/1000-1));
        dpTimer.setProgress((tickEvent.getMili()/1000-1));
        if(tickEvent.getMili()/1000 == 1){
            dpTimer.setText("Done");
            dpTimer.setProgress(0);
        }
    }


}

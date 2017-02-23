package com.example.quanla.pomodoro.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.quanla.pomodoro.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment {
    @BindView(R.id.tv_timer)
    TextView tvTimer;

    @BindView(R.id.btn_stop)
    Button btnStop;

    @BindView(R.id.btn_start)
    Button btnStart;

    private long startTime = 0L;



    private Handler customHandler = new Handler();



    long timeInMilliseconds = 0L;

    long timeSwapBuff = 0L;

    long updatedTime = 0L;


    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        setupUI(view);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
                btnStart.setVisibility(View.GONE);
                btnStop.setVisibility(View.VISIBLE);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
                btnStop.setVisibility(View.GONE);
                btnStart.setText("Resume");
                btnStart.setVisibility((View.VISIBLE));
            }
        });

        return view;
    }

    public void setupUI(View view){
        ButterKnife.bind(this, view);

    }

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updatedTime / 960);
            int mins = secs / 60;
            //secs = secs % 60;
            tvTimer.setText("" + mins + ":"

                    + String.format("%02d", secs));


            customHandler.postDelayed(this, 0);

        }
    };

}

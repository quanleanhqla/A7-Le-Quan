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



//    private int mTime = 360;
//    private DemNguocRunnable mDemnguocRun;
//    private Handler mDemnguocHandler;


    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        setupUI(view);




//        btnStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                btnStop.setVisibility(View.GONE);
//                btnStart.setText("Resume");
//                btnStart.setVisibility((View.VISIBLE));
//            }
//        });

        return view;
    }

    public void setupUI(View view){
        ButterKnife.bind(this, view);
        tvTimer.setText("I'm maintaining this function !!!");
//        mDemnguocHandler = new Handler();
//        mDemnguocRun = new DemNguocRunnable();

    }
//
//    private void showDemNguoc() {
//        mTime=360;
//        mDemnguocHandler.removeCallbacks(mDemnguocRun);
//        mDemnguocHandler.postDelayed(mDemnguocRun, 1000);
//    }
//
//    private class DemNguocRunnable implements Runnable {
//
//        @Override
//        public void run() {
//            // TODO Auto-generated method stub
//            handleDemnguoc();
//        }
//
//    }
//
//    private void handleDemnguoc() {
//        mTime--;
//        if (mTime == 0)
//            mTime = 360;
//        tvTimer.setText(mTime + "");
//        mDemnguocHandler.postDelayed(mDemnguocRun, 1000);
//    }
//
//    @Override
//    public void onDestroy() {
//
//        super.onDestroy();
//        mDemnguocHandler.removeCallbacks(mDemnguocRun);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_start:
//                showDemNguoc();
//                break;
//
//            default:
//                break;
//        }
//    }

}

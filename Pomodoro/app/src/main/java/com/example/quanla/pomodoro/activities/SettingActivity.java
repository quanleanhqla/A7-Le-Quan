package com.example.quanla.pomodoro.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.settings.SettingDetail;
import com.example.quanla.pomodoro.settings.SharedPrefs;

public class SettingActivity extends AppCompatActivity {
    private static final int DEFAULT_WORKTIME = 20;
    private static final int DEFAULT_TIMEBREAK = 5;
    private static final int DEFAULT_TIMELONGBREAK = 30;

    private SeekBar sbWorktime;
    private SeekBar sbTimebreak;
    private SeekBar sbTimelongbreak;

    private TextView txtWorktime;
    private TextView txtTimebreak;
    private TextView txtTimelongbreak;

    private Button btnDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getReference();
        addListener();
        setUI();
    }



    public void getReference(){
        sbWorktime = (SeekBar) this.findViewById(R.id.sb_worktime);
        sbTimebreak = (SeekBar) this.findViewById(R.id.sb_timebreak);
        sbTimelongbreak = (SeekBar) this.findViewById(R.id.sb_timelongbreak);

        txtWorktime = (TextView) this.findViewById(R.id.txt_worktime);
        txtTimebreak = (TextView) this.findViewById(R.id.txt_timebreak);
        txtTimelongbreak = (TextView) this.findViewById(R.id.txt_timelongbreak);


        btnDefault = (Button) this.findViewById(R.id.btn_default);
    }

    public void addListener(){
        sbWorktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtWorktime.setText(String.format("Work time %s mins", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPrefs.getInstance().putSetting(new SettingDetail(sbWorktime.getProgress(), sbTimebreak.getProgress(), sbTimelongbreak.getProgress()));
            }
        });

        sbTimebreak.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtTimebreak.setText(String.format("Break %s mins", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPrefs.getInstance().putSetting(new SettingDetail(sbWorktime.getProgress(), sbTimebreak.getProgress(), sbTimelongbreak.getProgress()));
            }
        });

        sbTimelongbreak.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtTimelongbreak.setText(String.format("Long break %s mins", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPrefs.getInstance().putSetting(new SettingDetail(sbWorktime.getProgress(), sbTimebreak.getProgress(), sbTimelongbreak.getProgress()));
            }
        });

        btnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefs.getInstance().putSetting(null);
                txtWorktime.setText("Work time " + DEFAULT_WORKTIME + " mins");
                sbWorktime.setProgress(DEFAULT_WORKTIME);
                txtTimebreak.setText("Break " + DEFAULT_TIMEBREAK + " mins");
                sbTimebreak.setProgress(DEFAULT_TIMEBREAK);
                txtTimelongbreak.setText("Long break " + DEFAULT_TIMELONGBREAK + " mins");
                sbTimelongbreak.setProgress(DEFAULT_TIMELONGBREAK);
            }
        });
    }

    public void setUI(){
        if(SharedPrefs.getInstance().getSettingDetail() == null){
            txtWorktime.setText("Work time " + DEFAULT_WORKTIME + " mins");
            txtTimebreak.setText("Break " + DEFAULT_TIMEBREAK + " mins");
            txtTimelongbreak.setText("Long break " + DEFAULT_TIMELONGBREAK + " mins");
        }
        else{
            txtWorktime.setText("Work time " + SharedPrefs.getInstance().getSettingDetail().getWorktime()+" mins");
            sbWorktime.setProgress(SharedPrefs.getInstance().getSettingDetail().getWorktime());
            txtTimebreak.setText("Break " + SharedPrefs.getInstance().getSettingDetail().getTimebreak()+" mins");
            sbTimebreak.setProgress(SharedPrefs.getInstance().getSettingDetail().getTimebreak());
            txtTimelongbreak.setText("Long break " + SharedPrefs.getInstance().getSettingDetail().getTimelongbreak() + " mins");
            sbTimelongbreak.setProgress(SharedPrefs.getInstance().getSettingDetail().getTimelongbreak());
        }

    }
}

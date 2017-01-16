package com.example.quanla.pomodoro.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.settings.SettingDetail;
import com.example.quanla.pomodoro.settings.SharedPrefs;

import java.util.Vector;

public class SettingActivity extends AppCompatActivity {
    private static final int DEFAULT_WORKTIME = 25;
    private static final int DEFAULT_TIMEBREAK = 5;
    private static final int DEFAULT_TIMELONGBREAK = 10;

    private SeekBar sbWorktime;
    private SeekBar sbTimebreak;
    private SeekBar sbTimelongbreak;

    private TextView txtWorktime;
    private TextView txtTimebreak;
    private TextView txtTimelongbreak;

    private Button btnDefault;

    private Spinner spBreak;

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

        spBreak = (Spinner) this.findViewById(R.id.sp_break);
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
                SharedPrefs.getInstance().putSetting(new SettingDetail(sbWorktime.getProgress(), sbTimebreak.getProgress(), sbTimelongbreak.getProgress(), spBreak.getSelectedItemPosition()));
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
                SharedPrefs.getInstance().putSetting(new SettingDetail(sbWorktime.getProgress(), sbTimebreak.getProgress(), sbTimelongbreak.getProgress(), spBreak.getSelectedItemPosition()));
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
                SharedPrefs.getInstance().putSetting(new SettingDetail(sbWorktime.getProgress(), sbTimebreak.getProgress(), sbTimelongbreak.getProgress(), spBreak.getSelectedItemPosition()));
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
                spBreak.post(new Runnable() {
                    @Override
                    public void run() {
                        spBreak.setSelection(1);
                    }
                });

            }
        });

        spBreak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPrefs.getInstance().putSetting(new SettingDetail(sbWorktime.getProgress(), sbTimebreak.getProgress(), sbTimelongbreak.getProgress(), position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setUI(){
        Vector<Integer> vector = new Vector<>();

        for(int i = 2; i<=7; i++){
            vector.add(i-2, i);
        }

        ArrayAdapter<Integer> numBreak = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, vector);

        spBreak.setAdapter(numBreak);

        if(SharedPrefs.getInstance().getSettingDetail() == null){
            txtWorktime.setText("Work time " + DEFAULT_WORKTIME + " mins");
            txtTimebreak.setText("Break " + DEFAULT_TIMEBREAK + " mins");
            txtTimelongbreak.setText("Long break " + DEFAULT_TIMELONGBREAK + " mins");
            spBreak.post(new Runnable() {
                @Override
                public void run() {
                    spBreak.setSelection(1);
                }
            });
        }
        else{
            txtWorktime.setText("Work time " + SharedPrefs.getInstance().getSettingDetail().getWorktime()+" mins");
            sbWorktime.setProgress(SharedPrefs.getInstance().getSettingDetail().getWorktime());
            txtTimebreak.setText("Break " + SharedPrefs.getInstance().getSettingDetail().getTimebreak()+" mins");
            sbTimebreak.setProgress(SharedPrefs.getInstance().getSettingDetail().getTimebreak());
            txtTimelongbreak.setText("Long break " + SharedPrefs.getInstance().getSettingDetail().getTimelongbreak() + " mins");
            sbTimelongbreak.setProgress(SharedPrefs.getInstance().getSettingDetail().getTimelongbreak());
            spBreak.setSelection(SharedPrefs.getInstance().getSettingDetail().getPosition());

        }




    }
}

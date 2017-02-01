package com.example.quanla.samplelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout rlRobot;
    private EditText edtDraw;
    private Button btnDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setReference();
        addListener();
    }

    private void addListener() {
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strDraw = edtDraw.getText().toString();
                if(strDraw.equals("robot")) rlRobot.setVisibility(RelativeLayout.VISIBLE);
            }
        });
    }

    private void setReference() {
        edtDraw = (EditText) this.findViewById(R.id.edt_draw);
        rlRobot = (RelativeLayout) this.findViewById(R.id.rl_robot);
        btnDraw = (Button) this.findViewById(R.id.btn_draw);
    }
}

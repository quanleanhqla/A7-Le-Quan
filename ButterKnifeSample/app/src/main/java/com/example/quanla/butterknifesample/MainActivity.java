package com.example.quanla.butterknifesample;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_ok)
    Button btnOK;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.img_teemo)
    ImageView imgTeemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        addListener();
    }

    private void addListener() {
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtName.getText().toString().equals("teemo")){
                    imgTeemo.setVisibility(ImageView.VISIBLE);
                }
            }
        });
    }
}

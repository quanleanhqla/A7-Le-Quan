package com.example.quanla.pomodoro.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.settings.LoginCredentials;
import com.example.quanla.pomodoro.settings.SharedPrefs;



public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.toString() ;
    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;
    private Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        skipLoginIfPossible();
        setContentView(R.layout.activity_login);

        etUsername = (EditText) this.findViewById(R.id.et_username);
        etPassword = (EditText) this.findViewById(R.id.et_password);
        btLogin = (Button) this.findViewById(R.id.bt_login);
        btRegister = (Button) this.findViewById(R.id.bt_register);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempLogin();
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryRegister();
            }
        });


        SharedPrefs sharedPrefs = new SharedPrefs(this);
        sharedPrefs.put(new LoginCredentials("hieu", "xxx"));
        Log.d(TAG, String.format("onCreate: %s", sharedPrefs.getLoginCredentials().toString()));
    }

    private void skipLoginIfPossible() {
        if(SharedPrefs.getInstance().getLoginCredentials() != null){
            gotoMainActivity();
        }
    }


    private void tryRegister() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if(username.equals("admin")){
            Toast.makeText(this, R.string.username_exist, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT).show();
        }
    }

    private void attempLogin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if(username.equals("admin") && password.equals("admin")){
            SharedPrefs.getInstance().put(new LoginCredentials(username, password));
            Toast.makeText(this, R.string.loginsuccess, Toast.LENGTH_SHORT).show();
            gotoMainActivity();
        }
        else Toast.makeText(this, R.string.loginfail, Toast.LENGTH_SHORT).show();
    }


    private void gotoMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

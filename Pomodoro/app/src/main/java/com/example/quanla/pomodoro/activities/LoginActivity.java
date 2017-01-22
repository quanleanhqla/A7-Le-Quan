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
import com.example.quanla.pomodoro.networks.jsonmodels.LoginJsonBody;
import com.example.quanla.pomodoro.networks.jsonmodels.LoginResponseJson;
import com.example.quanla.pomodoro.networks.jsonmodels.RegisterJsonBody;
import com.example.quanla.pomodoro.networks.jsonmodels.RegisterResponseJson;
import com.example.quanla.pomodoro.networks.services.LoginService;
import com.example.quanla.pomodoro.networks.services.RegisterService;
import com.example.quanla.pomodoro.settings.LoginCredentials;
import com.example.quanla.pomodoro.settings.SharedPrefs;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.toString() ;
    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;
    private Button btRegister;
    private Retrofit retrofit;

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

    private void sendRegister(String username, String password){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterService registerService = retrofit.create(RegisterService.class);

        //data & format
        //format =>Mediatype
        //date => json

        MediaType jsonType = MediaType.parse("application/json");
        String loginJson = (new Gson()).toJson(new RegisterJsonBody(username, password));
        RequestBody registerBody = RequestBody.create(jsonType, loginJson);

        registerService.register(registerBody)
                .enqueue(new Callback<RegisterResponseJson>() {
                    @Override
                    public void onResponse(Call<RegisterResponseJson> call, Response<RegisterResponseJson> response) {
                        RegisterResponseJson registerResponseJson = response.body();
                        if(registerResponseJson==null){
                            Log.d(TAG, "onResponse: Could not parse body");
                        }
                        else Log.d(TAG, String.format("onResponse: oh yeah %s", registerResponseJson));
                        if(response.code()==200){
                            Toast.makeText(LoginActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponseJson> call, Throwable t) {
                        Log.d(TAG, String.format("onFailure: %s", t));
                    }
                });
    }

    private void sendLogin(String username, String password){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginService loginService = retrofit.create(LoginService.class);

        //data & format
        //format =>Mediatype
        //date => json

        MediaType jsonType = MediaType.parse("application/json");
        String loginJson = (new Gson()).toJson(new LoginJsonBody(username, password));
        RequestBody loginBody = RequestBody.create(jsonType, loginJson);

        loginService.login(loginBody)
                .enqueue(new Callback<LoginResponseJson>() {
                    @Override
                    public void onResponse(Call<LoginResponseJson> call, Response<LoginResponseJson> response) {
                        LoginResponseJson loginResponseJson = response.body();
                        if(loginResponseJson==null){
                            Log.d(TAG, "onResponse: Could not parse body");
                        }
                        else Log.d(TAG, String.format("onResponse: oh yeah %s", loginResponseJson));
                        if(response.code()==200){
                            onLoginSuccess();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponseJson> call, Throwable t) {
                        Log.d(TAG, "onFailure: %s", t);
                    }
                });
    }

    private void skipLoginIfPossible() {
        if(SharedPrefs.getInstance().getLoginCredentials() != null){
            gotoMainActivity();
        }
    }

    private void onLoginSuccess(){
        SharedPrefs.getInstance().put(new LoginCredentials(username, password));
        Toast.makeText(this, R.string.loginsuccess, Toast.LENGTH_SHORT).show();
        gotoMainActivity();
    }

    private String username;
    private String password;
    private void tryRegister() {
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        sendRegister(username, password);
    }

    private void attempLogin() {
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        sendLogin(username, password);
    }


    private void gotoMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

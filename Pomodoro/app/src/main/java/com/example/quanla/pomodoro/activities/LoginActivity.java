package com.example.quanla.pomodoro.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanla.pomodoro.R;
import com.example.quanla.pomodoro.databases.DbContext;
import com.example.quanla.pomodoro.databases.models.Task;
import com.example.quanla.pomodoro.networks.NetContext;
import com.example.quanla.pomodoro.networks.jsonmodels.LoginJsonBody;
import com.example.quanla.pomodoro.networks.jsonmodels.LoginResponseJson;
import com.example.quanla.pomodoro.networks.jsonmodels.RegisterJsonBody;
import com.example.quanla.pomodoro.networks.jsonmodels.RegisterResponseJson;
import com.example.quanla.pomodoro.networks.services.TaskService;
import com.example.quanla.pomodoro.settings.LoginCredentials;
import com.example.quanla.pomodoro.settings.SharedPrefs;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.toString() ;
    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;
    private Button btRegister;
    private Retrofit retrofit;

    private ProgressDialog progressDialog;

    private TextInputLayout tilUsername;
    private TextInputLayout tilPassword;

    private String username;
    private String password;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        skipLoginIfPossible();
        setContentView(R.layout.activity_login);

        etUsername = (EditText) this.findViewById(R.id.et_username);
        etPassword = (EditText) this.findViewById(R.id.et_password);
        btLogin = (Button) this.findViewById(R.id.bt_login);
        btRegister = (Button) this.findViewById(R.id.bt_register);

        tilUsername = (TextInputLayout) this.findViewById(R.id.text_input_username);
        tilPassword = (TextInputLayout) this.findViewById(R.id.text_input_password);

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


//        SharedPrefs sharedPrefs = new SharedPrefs(this);
//        sharedPrefs.put(new LoginCredentials("hieu", "xxx", token));
//        Log.d(TAG, String.format("onCreate: %s", sharedPrefs.getLoginCredentials().toString()));

        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_DONE){
                    attempLogin();
                    return false;
                }
                return false;
            }
        });


    }

    private void sendRegister(String username, String password){
        TaskService registerService = NetContext.instance.create(TaskService.class);
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
                        if(response.code()==307) Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        else Toast.makeText(LoginActivity.this, "Usernam existed", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<RegisterResponseJson> call, Throwable t) {
                        Log.d(TAG, String.format("onFailure: %s", t));
                    }
                });
    }

    private void sendLogin(String username, String password){
        //1 create retrofit


        //2 create service
        TaskService loginService = NetContext.instance.create(TaskService.class);

        //data & format
        //format =>Mediatype
        //date => json

        MediaType jsonType = MediaType.parse("application/json");
        String loginJson = (new Gson()).toJson(new LoginJsonBody(username, password));
        RequestBody loginBody = RequestBody.create(jsonType, loginJson);

        //3: create call
        Call<LoginResponseJson> loginCall = loginService.login(loginBody);

        loginCall.enqueue(new Callback<LoginResponseJson>() {
                    @Override
                    public void onResponse(Call<LoginResponseJson> call, Response<LoginResponseJson> response) {
                        LoginResponseJson loginResponseJson = response.body();
                        if(response.code()==200){
                            token = loginResponseJson.getAccess_token();
                            onLoginSuccess();
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponseJson> call, Throwable t) {
                        Log.d(TAG, "onFailure: %s", t);
                        Toast.makeText(LoginActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
    }

    private void skipLoginIfPossible() {
        if(SharedPrefs.getInstance().getAccessToken() != null){
            gotoMainActivity();
        }
    }

    private void onLoginSuccess(){
        SharedPrefs.getInstance().put(new LoginCredentials(username, password, token));
        Toast.makeText(this, R.string.loginsuccess, Toast.LENGTH_SHORT).show();
        gotoMainActivity();
        progressDialog.dismiss();
    }


    private void tryRegister() {
        int checkun = 0;

        int checkpw = 0;
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        if(username.length()<5) checkun = 1;

        for(int i=0; i<username.length(); i++){
            char temp = username.charAt(i);
            if(temp<'0'||(temp>'9'&&temp<'A')||(temp>'Z'&&temp<'a')||temp>'z'){
                checkun = 2;
            }
        }
        if(password.length()<5){
            checkpw = 1;
        }
        for(int i = 0; i< password.length(); i++){
            char temp = password.charAt(i);
            if(temp<'0'||(temp>'9'&&temp<'A')||(temp>'Z'&&temp<'a')||temp>'z'){
                checkpw = 2;
            }
        }

        if(checkpw==0 && checkun==0) sendRegister(username, password);
        else{
            if(checkun==1) tilUsername.setError("Username must have at least 5 characters");
            else tilUsername.setError("Letters and numbers are permitted");
            if(checkpw==1) tilPassword.setError("Password must have at least 5 characters");
            else tilPassword.setError("Letters and numbers are permitted");
        }
    }

    private void attempLogin() {
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        sendLogin(username, password);
        progressDialog = ProgressDialog.show(this, null, "Authenticating...", true);
    }


    private void gotoMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }




}

package com.example.quanla.pomodoro.settings;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by QuanLA on 1/14/2017.
 */

public class SharedPrefs {
    private static final String LOGIN_KEY = "login";
    private static final String SHARED_PREFS_NAME = "SP";

    private static final String SETTING_KEY = "setting";

    private static SharedPrefs instance;
    private SharedPreferences sharedPreferences;


    public static SharedPrefs getInstance() {
        return instance;
    }

    public static void init(Context context){
        instance = new SharedPrefs(context);
    }

    public SharedPrefs(Context context){
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void put(LoginCredentials loginCredentials){
        // logincredentials => json string
        String loginJSON = (new Gson()).toJson(loginCredentials);

        this.sharedPreferences.edit().putString(LOGIN_KEY, loginJSON).commit();
    }

    public void putSetting(SettingDetail settingDetail){
        String settinJSON = (new Gson()).toJson(settingDetail);

        this.sharedPreferences.edit().putString(SETTING_KEY, settinJSON).commit();
    }

    public SettingDetail getSettingDetail(){
        String settingJSON = this.sharedPreferences.getString(SETTING_KEY, null);

        if(settingJSON==null) return null;
        SettingDetail settingDetail = (new Gson()).fromJson(settingJSON, SettingDetail.class);
        return settingDetail;
    }


    public LoginCredentials getLoginCredentials(){
        String loginJSON = this.sharedPreferences.getString(LOGIN_KEY, null);

        if(loginJSON==null) return null;
        LoginCredentials loginCredentials = (new Gson()).fromJson(loginJSON, LoginCredentials.class);
        return loginCredentials;
    }


}

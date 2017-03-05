package com.example.quanla.pomodoro.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by QuanLA on 1/22/2017.
 */

public class RegisterResponseJson {
    @SerializedName("access_token")
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }
}

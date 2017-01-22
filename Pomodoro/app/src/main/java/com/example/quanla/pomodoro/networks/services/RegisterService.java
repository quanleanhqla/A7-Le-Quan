package com.example.quanla.pomodoro.networks.services;

import com.example.quanla.pomodoro.networks.jsonmodels.RegisterResponseJson;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by QuanLA on 1/22/2017.
 */

public interface RegisterService {
    @POST("register")
    Call<RegisterResponseJson> register(@Body RequestBody body);
}

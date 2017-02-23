package com.example.quanla.pomodoro.networks.services;

import com.example.quanla.pomodoro.databases.models.Task;
import com.example.quanla.pomodoro.settings.SharedPrefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

/**
 * Created by QuanLA on 2/20/2017.
 */

public interface GetTaskService {
    @GET("task")
    Call<List<Task>> getAllTask();
}
